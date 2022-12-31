package com.validator

import com.validator.Validator.Companion.validate
import com.validator.dto.Document
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*
import com.validator.ValidationResult.Failed
import com.validator.ValidationResult.Passed

private fun validate(document: Document): ValidationResult<List<String>, Document> =

    document validate  { subject ->

        "The owner field may not be empty" enforcing {
            subject.owner.isNotEmpty()
        }

        "The owner field must be a valid UUID" trying {
            UUID.fromString(subject.owner)
        }

        "The content field may not be empty" ignoring {
            subject.content.isEmpty()
        }
    }

class ConstraintTest {

    @Test
    fun `Valid documents pass`() {
        val document = Document(
            owner = UUID.randomUUID().toString(),
            content = emptyList(),
        )

        val result = validate(document)

        assertThat(result).isExactlyInstanceOf(Passed::class.java)
    }

    @Test
    fun `Owners with non-UUID conforming values are not allowed`() {
        val document = Document(
            owner = "notUuid",
            content = emptyList(),
        )

        val result = validate(document)

        assertThat(result).isExactlyInstanceOf(Failed::class.java)
        assertThat((result as Failed).errors).containsExactlyInAnyOrder(
            "The owner field must be a valid UUID"
        )
    }

    @Test
    fun `Owners which are empty are not allowed`() {
        val document = Document(
            owner = "",
            content = emptyList(),
        )

        val result = validate(document)

        assertThat(result).isExactlyInstanceOf(Failed::class.java)
        assertThat((result as Failed).errors).containsExactlyInAnyOrder(
            "The owner field may not be empty",
            "The owner field must be a valid UUID",
        )
    }
}