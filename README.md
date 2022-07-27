# Kotlin Validator DSL
An easy to use DSL for validating the content of any document.

## Instructions

First we define an arbitrary Document class:

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/4ea6af0c4d21e597e42be3cec1ad60f8715f1ea9/src/main/kotlin/com/example/ExampleDocument.kt#L3-L6

## Instantiate a validator

Then we must instantiate a validator:

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/4ea6af0c4d21e597e42be3cec1ad60f8715f1ea9/src/main/kotlin/com/example/validations/Instantiation.kt#L9-L13

<sup>*Returns all errors that occurred, including duplicates<sup>

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/4ea6af0c4d21e597e42be3cec1ad60f8715f1ea9/src/main/kotlin/com/example/validations/Instantiation.kt#L15-L19

<sup>*Returns the first error that occurred and skips evaluation of remaining predicates<sup>

## Validation

### Enforcing

Properties can be checked for whether they fulfill a predicate:

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/2c95c104611da2fa3f058ed5629301031b7366ff/src/main/kotlin/com/example/validations/ValidationExampleBasics.kt#L16-L18

### Trying

Properties can be checked for whether an operation on them, i.e. a parse, will result in an exception:

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/2c95c104611da2fa3f058ed5629301031b7366ff/src/main/kotlin/com/example/validations/ValidationExampleBasics.kt#L20-L22
## Property accessors

#### All properties

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/4ea6af0c4d21e597e42be3cec1ad60f8715f1ea9/src/main/kotlin/com/example/validations/ValidationExampleWithSubject.kt#L9-L20

#### Single property

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/4ea6af0c4d21e597e42be3cec1ad60f8715f1ea9/src/main/kotlin/com/example/validations/ValidationExampleWithSingleProperty.kt#L9-L20

#### Iterable properties

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/4ea6af0c4d21e597e42be3cec1ad60f8715f1ea9/src/main/kotlin/com/example/validations/ValidationExampleWithIterableProperty.kt#L9-L19

#### Nested properties

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/4ea6af0c4d21e597e42be3cec1ad60f8715f1ea9/src/main/kotlin/com/example/validations/ValidationExampleWithNestedProperties.kt#L9-L23

## Custom constraint types

Property checks can be given additional failure context:

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/4ea6af0c4d21e597e42be3cec1ad60f8715f1ea9/src/main/kotlin/com/example/validations/ValidationExampleWithCustomConstraintType.kt#L12-L22

## Validation result

A ValidationResult can be used in pattern matching and contains a few helper functions in case multiple results are returned:

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/dd8a4039423a07b6ba83467ded131045064c6b08/src/main/kotlin/com/example/validations/ValidationResultUsage.kt#L8-L18