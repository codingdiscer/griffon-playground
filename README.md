Griffon Playground
-----------------------------------

This project exists as a practice pit for writing griffon components


Simply add your source files to `src/main/groovy`, your test cases to
`src/test/groovy` and then you will be able to build your project with

    gradle build
    gradle test
    gradle run

Don't forget to add any extra JAR dependencies to `build.gradle`!



## Building the project

Used this to create the application template:

```
$ lazybones create https://dl.bintray.com/griffon/griffon-lazybones-templates/griffon-javafx-groovy-template-1.12.0.zip griffon-playground
```

To create a new MVC-group:

```
$ lazybones generate artifact
```
