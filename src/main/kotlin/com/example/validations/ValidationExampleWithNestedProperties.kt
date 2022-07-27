package com.example.validations

import com.example.Document
import com.validator.ValidationResult
import com.validator.Validator.Companion.checkEagerly

class ValidationExampleWithNestedProperties {

    fun validate(document: Document): ValidationResult<List<String>> =

        checkEagerly(document) {

            checkProperty(Document::owner) { owner ->

                checkProperty(String::length) { length ->

                    "The owner field must not be longer than 10 characters" enforcing {
                        length <= 10
                    }
                }
            }
        }
}
