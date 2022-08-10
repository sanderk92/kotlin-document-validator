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

        checkIterableProperty(Document::content) { element ->

            "The content field must contain only positive values" enforcing {
                element > 0
            }
        }
    }

class IterablePropertyAccessorTest {

    @Test
    fun `Valid documents are allowed`() {
        val document = Document(
            owner = "",
            content = listOf(1, 2, 3, 4),
        )

        val result = validate(document)

        assertThat(result).isExactlyInstanceOf(Passed::class.java)
    }

    @Test
    fun `Content with negative values is not allowed`() {
        val document = Document(
            owner = "",
            content = listOf(-1, -2, -3, -4),
        )

        val result = validate(document)

        assertThat(result).isExactlyInstanceOf(Failed::class.java)
        assertThat((result as Failed).errors).containsExactlyInAnyOrder(
            "The content field must contain only positive values",
            "The content field must contain only positive values",
            "The content field must contain only positive values",
            "The content field must contain only positive values",
        )
    }
}
