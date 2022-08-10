package com.validator

import com.validator.dto.Document
import com.validator.Failed
import com.validator.Passed
import com.validator.ValidationResult
import com.validator.Validator
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

private fun validate(document: Document): ValidationResult<List<String>> =

    Validator.checkEagerly(document) {

        checkSubject { (owner, _) ->

            "The owner field must not be empty or blank" enforcing {
                owner.isNotBlank()
            }
        }
    }

class SubjectPropertyAccessorTest {

    @Test
    fun `Valid documents are allowed`() {
        val document = Document(
            owner = "Jason",
            content = emptyList(),
        )

        val result = validate(document)

        Assertions.assertThat(result).isExactlyInstanceOf(Passed::class.java)
    }

    @Test
    fun `Owners with empty names are not allowed`() {
        val document = Document(
            owner = "",
            content = listOf(1),
        )

        val result = validate(document)

        Assertions.assertThat(result).isExactlyInstanceOf(Failed::class.java)
        Assertions.assertThat((result as Failed).errors).containsExactlyInAnyOrder(
            "The owner field must not be empty or blank"
        )
    }
}