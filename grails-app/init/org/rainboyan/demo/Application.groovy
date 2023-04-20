package org.rainboyan.demo

import io.micronaut.spring.context.MicronautApplicationContext
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration

import grails.boot.Grails
import grails.boot.GrailsBuilder
import groovy.transform.CompileStatic

@CompileStatic
//@SpringBootApplication(exclude = [DataSourceTransactionManagerAutoConfiguration])
class Application {

    static void main(String[] args) {
		MicronautApplicationContext context = new MicronautApplicationContext()
		context.start()

		GrailsBuilder builder = new GrailsBuilder()
		builder.parent(context)
		builder.sources(Application.class)
		Grails grails = builder.build()
        grails.run()
    }

}
