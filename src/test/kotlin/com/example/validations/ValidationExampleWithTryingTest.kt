package com.example.validations

import com.example.Document
import com.validator.Failed
import com.validator.Passed
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

class ValidationExampleWithTryingTest {

    @Test
    fun `Owners with non-UUID conforming values are not allowed`() {
        val document = Document(
            owner = "notUuid",
            content = emptyList(),
        )

        val result = ValidationExampleWithTrying().validate(document)

        Assertions.assertThat(result).isExactlyInstanceOf(Failed::class.java)
        Assertions.assertThat((result as Failed).errors).containsExactly(
            "The owner field must be a valid UUID"
        )
    }

    @Test
    fun `Owners with UUID conforming values are allowed`() {
        val document = Document(
            owner = UUID.randomUUID().toString(),
            content = emptyList(),
        )

        val result = ValidationExampleWithTrying().validate(document)

        Assertions.assertThat(result).isExactlyInstanceOf(Passed::class.java)
    }

}