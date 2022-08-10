package com.validator

import com.validator.dto.Document
import com.validator.Failed
import com.validator.Passed
import com.validator.ValidationResult
import com.validator.Validator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private fun validate(document: Document): ValidationResult<List<String>> =

    Validator.checkEagerly(document) {

        checkProperty(Document::owner) {

            checkProperty(String::length) { length ->

                "The owner field must not be longer than 10 characters" enforcing {
                    length <= 10
                }
            }
        }
    }

class NestedPropertyAccessorTest {

    @Test
    fun `Valid documents are allowed`() {
        val document = Document(
            owner = "Jason",
            content = emptyList(),
        )

        val result = validate(document)

        assertThat(result).isExactlyInstanceOf(Passed::class.java)
    }

    @Test
    fun `Owners with too long names are not allowed`() {
        val document = Document(
            owner = "abcdefghijklmnopqrstuvwxyz",
            content = emptyList(),
        )

        val result = validate(document)

        assertThat(result).isExactlyInstanceOf(Failed::class.java)
        assertThat((result as Failed).errors).containsExactlyInAnyOrder(
            "The owner field must not be longer than 10 characters"
        )
    }
}