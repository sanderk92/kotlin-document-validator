package com.example.validations

import com.example.Document
import com.validator.ValidationResult
import com.validator.Validator.Companion.checkEagerly

class ValidationExampleLogging {

    fun validate(subject: Document): ValidationResult<List<String>> =

        checkEagerly(subject) { document ->

            "The content field may not be empty" ignoring {
                document.content.isNotEmpty()
            } onPass {
                println("The content field was not empty!")
            } onFail {
                println("The content field was empty")
            }
        }
}
