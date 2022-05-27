package com

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinDocumentValidatorApplication

fun main(args: Array<String>) {
    runApplication<KotlinDocumentValidatorApplication>(*args)
}
