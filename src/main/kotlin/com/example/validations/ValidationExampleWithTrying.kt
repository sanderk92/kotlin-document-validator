package com.example.validations

import com.example.Document
import com.validator.ValidationResult
import com.validator.Validator.Companion.checkEagerly
import java.util.*

class ValidationExampleWithTrying {

    fun validate(document: Document): ValidationResult<List<String>> =

        checkEagerly(document) {

            checkProperty(Document::owner) { owner ->

                "The owner field must be a valid UUID" trying {
                    UUID.fromString(owner)
                }
            }
        }
}
