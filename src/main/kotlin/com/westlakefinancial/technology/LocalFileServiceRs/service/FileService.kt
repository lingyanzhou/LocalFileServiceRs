package com.westlakefinancial.technology.LocalFileServiceRs.service

import com.westlakefinancial.technology.LocalFileServiceRs.domain.FileInfo
import java.io.InputStream
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType


interface FileService {
    fun plainFiles(path: String): List<FileInfo>
    fun subDirectories(path: String): List<FileInfo>
    fun info(path: String): FileInfo
    fun stream(path: String): InputStream
}