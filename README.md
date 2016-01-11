# fnv-text-profile-signature
This project contains 2 items:

[![](https://jitpack.io/v/casetext/fnv-text-profile-signature.svg)](https://jitpack.io/#casetext/fnv-text-profile-signature)

1. A 128-bit FNV-1a implementation (which really ought to exist in the java standard lib, but I digress...)
2. An adaptation of the TextProfileSignature class used in [solr](http://lucene.apache.org/solr/), adapted from [Apache Nutch](http://nutch.apache.org/) that does 2 things differently:
    * Uses a LinkedHashMap in place of a regular HashMap, so that the sorting order is predictable
    * Uses an FNV-1a hash for the final result, instead of MD5, so that it can be functionally compatible with in-browser javascript implementations.

This project is built with gradle.  It uses gradle's [wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html) functionality to guarantee that all users are building with the same version of the build tools without having to jump through hoops to install it.  To build, type `./gradlew build`; to run the tests type `./gradlew test`.

To use this project as a dependency in a gradle project, you should add the following line to your dependencies section:

`compile 'com.github.casetext:fnv-text-profile-signature:+'`

and it will be loaded in as any other maven dependency.
