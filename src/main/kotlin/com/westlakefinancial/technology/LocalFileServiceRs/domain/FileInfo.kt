package com.westlakefinancial.technology.LocalFileServiceRs.domain

data class FileInfo(val fileName: String, val filePath: String, val isExists: Boolean, val isDir: Boolean, val length: Long, val lastModified: Long)