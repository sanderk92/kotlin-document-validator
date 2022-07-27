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

#### All properties

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/4ea6af0c4d21e597e42be3cec1ad60f8715f1ea9/src/main/kotlin/com/example/validations/ValidationExampleWithSubject.kt#L9-L23

#### Single property

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/4ea6af0c4d21e597e42be3cec1ad60f8715f1ea9/src/main/kotlin/com/example/validations/ValidationExampleWithSingleProperty.kt#L9-L23

#### Iterable properties

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/4ea6af0c4d21e597e42be3cec1ad60f8715f1ea9/src/main/kotlin/com/example/validations/ValidationExampleWithIterableProperty.kt#L9-L19

#### Nested properties

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/4ea6af0c4d21e597e42be3cec1ad60f8715f1ea9/src/main/kotlin/com/example/validations/ValidationExampleWithNestedProperties.kt#L9-L26

## Custom constraint types

Property checks can be given additional failure context:

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/4ea6af0c4d21e597e42be3cec1ad60f8715f1ea9/src/main/kotlin/com/example/ExampleConstraint.kt#L3-L11

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/4ea6af0c4d21e597e42be3cec1ad60f8715f1ea9/src/main/kotlin/com/example/validations/ValidationExampleWithCustomConstraintType.kt#L11-L21

## Try (parse)

Properties can be checked for whether an operation, i.e. a parse, will result in an exception:

https://github.com/sanderk92/kotlin-dsl-document-validator/blob/4ea6af0c4d21e597e42be3cec1ad60f8715f1ea9/src/main/kotlin/com/example/validations/ValidationExampleWithTrying.kt#L12-L20
