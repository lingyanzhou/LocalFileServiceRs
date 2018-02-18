package com.westlakefinancial.technology.LocalFileServiceRs.service.impl

import com.westlakefinancial.technology.LocalFileServiceRs.domain.FileInfo
import com.westlakefinancial.technology.LocalFileServiceRs.service.FileService
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils
import org.apache.commons.io.filefilter.FalseFileFilter
import org.apache.commons.io.filefilter.IOFileFilter
import org.apache.commons.io.filefilter.TrueFileFilter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File
import java.io.InputStream
import java.util.*

class LocalFileService(val localRepo: String) : FileService {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(LocalFileService::class.java)
    }

    private fun makeRealPath(path: String): String {
        if (FilenameUtils.getPrefixLength(path) > 0) {
            throw Exception("Path ${path} is not valid")
        }

        return FilenameUtils.concat(localRepo, path)
    }

    override fun subDirectories(path: String): List<FileInfo> {
        val realPath = makeRealPath(path)
        val coll = File(realPath).listFiles(File::isDirectory)
        val rawList =  coll.toList()
        return rawList.map {
            FileInfo(
                    fileName = it.name,
                    filePath = File(it.toRelativeString(File(localRepo))).invariantSeparatorsPath,
                    isExists = it.exists(),
                    isDir = it.isDirectory,
                    length = it.length(),
                    lastModified = it.lastModified()
            )
        }
    }

    override fun info(path: String): FileInfo {
        val realPath = makeRealPath(path)
        val f = File(realPath)
        return FileInfo(
                fileName = f.name,
                filePath = File(f.toRelativeString(File(localRepo))).invariantSeparatorsPath,
                isExists = f.exists(),
                isDir = f.isDirectory,
                length = f.length(),
                lastModified = f.lastModified()
        )
    }

    override fun stream(path: String): InputStream {
        val realPath = makeRealPath(path)
        val f = File(realPath)
        return FileUtils.openInputStream(f)
    }

    override fun plainFiles(path: String): List<FileInfo> {
        val realPath = makeRealPath(path)
        val coll = FileUtils.listFiles(File(realPath), TrueFileFilter.INSTANCE, null)
        val rawList = ArrayList(coll)
        return rawList.map {
            FileInfo(
                    fileName = it.name,
                    filePath = File(it.toRelativeString(File(localRepo))).invariantSeparatorsPath,
                    isExists = it.exists(),
                    isDir = it.isDirectory,
                    length = it.length(),
                    lastModified = it.lastModified()
            )
        }
    }

}