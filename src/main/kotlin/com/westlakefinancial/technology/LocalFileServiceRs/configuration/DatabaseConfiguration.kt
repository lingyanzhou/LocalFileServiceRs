package com.westlakefinancial.technology.LocalFileServiceRs.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup
import javax.sql.DataSource


@Configuration
class DatabaseConfiguration {
/*    @Value("\${datasource.url}")
    var url: String? = null

    @Value("\${datasource.username}")
    var username: String? = null

    @Value("\${datasource.password}")
    var password: String? = null

    @Value("\${datasource.driver}")
    var driver: String? = null

    @Bean
    fun dataSource(): DataSource {
        val ds = DriverManagerDataSource(url,
                username,
                password)
        ds.setDriverClassName(driver)
        return ds
    }*/
}
