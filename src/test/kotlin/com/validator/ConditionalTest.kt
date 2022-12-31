package com.validator

import com.validator.ValidationResult.Failed
import com.validator.ValidationResult.Passed
import com.validator.Validator.Companion.validate
import com.validator.dto.Document
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private fun validate(document: Document): ValidationResult<List<String>, Document> =

    document validate { (owner, content) ->

        content.isEmpty() ifTrue  {

            "Owner may not be empty if content is" enforcing {
                owner.isNotBlank()
            }
        }

        content.isEmpty() ifFalse {

            "Owner Frank may not have content" enforcing {
                owner != "Frank"
            }
        }
    }


class ConditionalTest {

    @Test
    fun `Valid documents are allowed`() {
        val document = Document(
            owner = "Jan",
            content = emptyList(),
        )

        val result = validate(document)

        assertThat(result).isExactlyInstanceOf(Passed::class.java)
    }

    @Test
    fun `Owner may not be empty if content is`() {
        val document = Document(
            owner = "",
            content = emptyList(),
        )

        val result = validate(document)

        assertThat(result).isExactlyInstanceOf(Failed::class.java)
        assertThat((result as Failed).errors).containsExactlyInAnyOrder(
            "Owner may not be empty if content is"
        )
    }

    @Test
    fun `Owner frank may not have content`() {
        val document = Document(
            owner = "Frank",
            content = listOf(1),
        )

        val result = validate(document)

        assertThat(result).isExactlyInstanceOf(Failed::class.java)
        assertThat((result as Failed).errors).containsExactlyInAnyOrder(
            "Owner Frank may not have content"
        )
    }
}