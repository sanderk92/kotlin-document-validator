package com.example.validations

import com.example.Document
import com.validator.Failed
import com.validator.Passed
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

class ValidationExampleBasicsTest {

    @Test
    fun `Valid documents pass`() {
        val document = Document(
            owner = UUID.randomUUID().toString(),
            content = emptyList(),
        )

        val result = ValidationExampleBasics().validate(document)

        Assertions.assertThat(result).isExactlyInstanceOf(Passed::class.java)
    }

    @Test
    fun `Owners with non-UUID conforming values are not allowed`() {
        val document = Document(
            owner = "notUuid",
            content = emptyList(),
        )

        val result = ValidationExampleBasics().validate(document)

        Assertions.assertThat(result).isExactlyInstanceOf(Failed::class.java)
        Assertions.assertThat((result as Failed).errors).containsExactlyInAnyOrder(
            "The owner field must be a valid UUID"
        )
    }

    @Test
    fun `Owners which are empty are not allowed`() {
        val document = Document(
            owner = "",
            content = emptyList(),
        )

        val result = ValidationExampleBasics().validate(document)

        Assertions.assertThat(result).isExactlyInstanceOf(Failed::class.java)
        Assertions.assertThat((result as Failed).errors).containsExactlyInAnyOrder(
            "The owner field may not be empty",
            "The owner field must be a valid UUID",
        )
    }
}