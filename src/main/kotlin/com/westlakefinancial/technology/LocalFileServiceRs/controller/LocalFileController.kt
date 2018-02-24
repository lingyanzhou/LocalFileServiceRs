package com.westlakefinancial.technology.LocalFileServiceRs.controller

import com.westlakefinancial.technology.LocalFileServiceRs.domain.FileInfo
import com.westlakefinancial.technology.LocalFileServiceRs.service.FileService
import org.apache.commons.io.FilenameUtils
import org.apache.commons.lang3.exception.ExceptionUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.util.StreamUtils
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.HandlerMapping
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("/")
class LocalFileController {
    @Autowired
    var fileService: FileService? = null

    @RequestMapping("/info/**", method = arrayOf(RequestMethod.GET))
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun info(request: HttpServletRequest): FileInfo {
        val reqUrl = request.getAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE) as String
        val path = reqUrl.substring("/info/".length)
        return fileService!!.info(path)
    }

    @RequestMapping("/stream/**", method = arrayOf(RequestMethod.GET))
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun stream(request: HttpServletRequest): HttpEntity<ByteArray> {
        val reqUrl = request.getAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE) as String
        val path = reqUrl.substring("/stream/".length)
        val file = fileService!!.stream(path)
        val document = StreamUtils.copyToByteArray(file)

        val header = HttpHeaders()
        header.contentType = MediaType("application", "octet-stream")
        header.set("Content-Disposition", "inline; filename=" + FilenameUtils.getName(path))
        header.contentLength = document.size.toLong()

        return HttpEntity(document, header)
    }

    @RequestMapping("/plain-files/**", method = arrayOf(RequestMethod.GET))
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun plainFiles(request: HttpServletRequest): List<FileInfo> {
        val reqUrl = request.getAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE) as String
        val path = reqUrl.substring("/plain-files/".length)
        return fileService!!.plainFiles(path)
    }

    @RequestMapping("/sub-directories/**", method = arrayOf(RequestMethod.GET))
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun subDirectories(request: HttpServletRequest): List<FileInfo> {
        val reqUrl = request.getAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE) as String
        val path = reqUrl.substring("/sub-directories/".length)
        return fileService!!.subDirectories(path)
    }

    @ExceptionHandler(Exception::class)
    @ResponseBody
    fun error(request: HttpServletRequest, ex: Exception): Map<String, String?> {
        return mapOf("exception_message" to ex.message, "exception_stack_trace"  to ExceptionUtils.getStackTrace(ex), "url" to request.requestURL.toString())
    }
}
