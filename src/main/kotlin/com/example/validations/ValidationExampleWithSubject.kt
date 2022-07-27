package com.example.validations

import com.example.Document
import com.validator.ValidationResult
import com.validator.Validator.Companion.checkEagerly

class ValidationExampleWithSubject {

    fun validate(document: Document): ValidationResult<List<String>> =

        checkEagerly(document) {

            checkSubject { (owner, _) ->

                "The owner field must not be empty or blank" enforcing {
                    owner.isNotBlank()
                }
            }
        }
}
