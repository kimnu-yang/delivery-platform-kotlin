package org.delivery.api.config.web

import org.apache.catalina.connector.Connector
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.server.WebServerFactoryCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TomcatWebConfig : WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    override fun customize(factory: TomcatServletWebServerFactory?) {
        factory!!.addConnectorCustomizers(TomcatConnectorCustomizer { connector: Connector ->
            connector.setProperty("relaxedQueryChars", "<>[\\]^`{|}")
        })
    }
}