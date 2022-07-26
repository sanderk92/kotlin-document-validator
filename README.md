# Kotlin Validator DSL
An easy to use DSL for validating the content of any document.

## Instructions

First we define an arbitrary Document class:

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/c8d8d1f77f4741a096684c9d78fff622823cb46e/src/main/kotlin/com/example/ExampleDocument.kt#L3-L6

## Instantiate a validator

Then we must instantiate a validator:

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/c8d8d1f77f4741a096684c9d78fff622823cb46e/src/main/kotlin/com/example/validations/Instantiation.kt#L9-L13

<sup>*Returns all errors that occurred, including duplicates<sup>

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/c8d8d1f77f4741a096684c9d78fff622823cb46e/src/main/kotlin/com/example/validations/Instantiation.kt#L16-L20

<sup>*Returns the first error that occurred and skips evaluation of remaining predicates<sup>

## Validation

#### All properties

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/c8d8d1f77f4741a096684c9d78fff622823cb46e/src/main/kotlin/com/example/validations/ValidationExampleWithSubject.kt#L9-L24

#### Single property

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/c8d8d1f77f4741a096684c9d78fff622823cb46e/src/main/kotlin/com/example/validations/ValidationExampleWithSingleProperty.kt#L9-L24

#### Iterable properties

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/c8d8d1f77f4741a096684c9d78fff622823cb46e/src/main/kotlin/com/example/validations/ValidationExampleWithIterableProperty.kt#L9-L19

#### Nested properties

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/c8d8d1f77f4741a096684c9d78fff622823cb46e/src/main/kotlin/com/example/validations/ValidationExampleWithNestedProperties.kt#L9-L26

## Custom constraint types

Property checks can be given additional failure context:

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/c8d8d1f77f4741a096684c9d78fff622823cb46e/src/main/kotlin/com/example/ExampleConstraint.kt#L3-L11

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/c8d8d1f77f4741a096684c9d78fff622823cb46e/src/main/kotlin/com/example/validations/ValidationExampleWithCustomConstraintType.kt#L11-L21
