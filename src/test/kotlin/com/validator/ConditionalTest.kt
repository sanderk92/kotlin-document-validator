package com.validator

import com.validator.Validator.Companion.validateEagerly
import com.validator.dto.Document
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

private fun validate(document: Document): ValidationResult<List<String>> =

    document validateEagerly { (owner, content) ->

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

        Assertions.assertThat(result).isExactlyInstanceOf(Passed::class.java)
    }

    @Test
    fun `Owner may not be empty if content is`() {
        val document = Document(
            owner = "",
            content = emptyList(),
        )

        val result = validate(document)

        Assertions.assertThat(result).isExactlyInstanceOf(Failed::class.java)
        Assertions.assertThat((result as Failed).errors).containsExactlyInAnyOrder(
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

        Assertions.assertThat(result).isExactlyInstanceOf(Failed::class.java)
        Assertions.assertThat((result as Failed).errors).containsExactlyInAnyOrder(
            "Owner Frank may not have content"
        )
    }
}