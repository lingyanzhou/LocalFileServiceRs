package com.westlakefinancial.technology.LocalFileServiceRs.configuration

import com.westlakefinancial.technology.LocalFileServiceRs.service.FileService
import com.westlakefinancial.technology.LocalFileServiceRs.service.impl.LocalFileService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LocalFileServiceRsConfiguration {
    @Value("\${localRepo.path}")
    var localRepo: String? = null

    @Bean
    fun fileService(): FileService = LocalFileService(localRepo!!)
}