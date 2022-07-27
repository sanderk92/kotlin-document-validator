package com.example.validations

import com.example.Document
import com.validator.ValidationResult
import com.validator.Validator.Companion.checkEagerly
import java.util.*

class ValidationExampleBasics {

    fun validate(subject: Document): ValidationResult<List<String>> =

        checkEagerly(subject) { document ->

            "The owner field may not be empty" enforcing {
                document.owner.isNotEmpty()
            }

            "The owner field must be a valid UUID" trying {
                UUID.fromString(document.owner)
            }
        }
}
