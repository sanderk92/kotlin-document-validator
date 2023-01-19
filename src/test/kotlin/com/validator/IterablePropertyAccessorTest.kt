package com.validator

import com.validator.Validator.Companion.validate
import com.validator.dto.Document
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import com.validator.ValidationResult.Failed
import com.validator.ValidationResult.Passed

private fun validate(document: Document): ValidationResult<List<String>, Document> =

    document validate  {

        Document::content onEach { element ->

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
