[![](https://jitpack.io/v/juliofalbo/j-api-filter.svg)](https://jitpack.io/#juliofalbo/j-api-filter)
[![Build Status](https://travis-ci.org/juliofalbo/j-api-filter.svg?branch=master)](https://travis-ci.org/juliofalbo/j-api-filter)

# JApiFilter

The purpose of JApiFilter is a simplification of the use of REST for filters by parameters!

## Requirements

   * Spring Boot 2.x
   * Java 8

## Add in your project
Use [*JitPack*](https://jitpack.io/#juliofalbo/j-api-filter/-SNAPSHOT)

###  Maven

```
<repositories>
  <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
  </repository>
</repositories>

...

<dependency>
    <groupId>com.github.juliofalbo</groupId>
    <artifactId>j-api-filter</artifactId>
    <version>9df2da2cc5</version>
</dependency>
```

### Gradle

```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}

...

dependencies {
      compile 'com.github.juliofalbo:j-api-filter:9df2da2cc5'
}
```
# ExampleMatcher

## How to use?

*This library contains a Builder for facilitate the use of the Interface [ExampleMatcher](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/ExampleMatcher.html).*
```
new ExampleBuilder(YourClass.class).
    .addFilter("yourField", yourObject)
    .build();
````

    Note: If you want to use custom ExampleMatcher, you can send your ExampleMatcher object to build method. 


# Specification

## How to use?

*First it is important to know that JApiFilter is based on the Spring Data Specification.*

### Now let's go!

# Repository
The first thing to do is to implement the **[JpaSpecificationExecutor](https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaSpecificationExecutor.html)** interface in your repository, so you will have access to the **findAll(Specification <T> spec)** method 

# Builder
**Using the JApiSpecificationBuilder class is very simple.**

To create a specification based on some entity, simply use the *createFiltersByEntity* method, as in the example below:

Ex:
```
Specification<YourEntity> specification = JApiSpecificationBuilder.init()
                .createFiltersByEntity(yourEntity, JApiOperationEnum.EQUAL)
                .buildSpec();
```

To create specific filters, the builder provides methods to create filters according to a particular operation:

* withEqualFilter
* withBiggerFilter
* withSmallerFilter
* withLikeFilter
* withInFilter
* withCustomFilter

Ex:
```
Specification<YourEntity> specification = JApiSpecificationBuilder.init()
                .withEqualFilter("yourVarName", yourObject)
                .withBiggerFilter("yourVarName", yourObject)
                .withSmallerFilter("yourVarName", yourObject)
                .withLikeFilter("yourVarName", yourObject)
                .withInFilter(new FieldIn("yourFieldList", "attributeName"), yourObject)
                .withCustomFilter("yourVarName", yourObject, JApiOperationEnum.[yourChoise])
                .buildSpec();
```

# Operations
Currently available operations are:

* EQUAL
* BIGGER
* SMALLER
* LIKE
* IN

## Current Version
0.2

___

##### [Júlio Falbo](http://juliofalbo.tech)
