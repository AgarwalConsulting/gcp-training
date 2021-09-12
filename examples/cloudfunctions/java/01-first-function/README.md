# First cloud function

We can develop the first function locally using either Maven or Gradle.

## Using Maven

### Step 1: Starting with `maven-archetype-quickstart`

You can change the value of `groupId` from `com.algogrit.app` to something else.

```bash
mvn archetype:generate -DarchetypeGroupId=io.github.spenceuk \
    -DarchetypeArtifactId=java11-basic-archetype \
    -DarchetypeVersion=1.1 \
    -DgroupId=com.algogrit.app \
    -DartifactId=first-function \
    -DinteractiveMode=false
```

### Step 2: [Specifying dependencies](https://cloud.google.com/functions/docs/writing/specifying-dependencies-java#maven)

Edit the generated `pom.xml`, with the following dependencies (within the `<dependencies>...</dependencies>` block):

```xml
<dependency>
  <groupId>com.google.cloud.functions</groupId>
  <artifactId>functions-framework-api</artifactId>
  <version>1.0.4</version>
  <scope>provided</scope>
</dependency>
```

And the following build plugin (within the `<build><plugins>...</plugins></build>` block):

```xml
<plugin>
  <!--
    Google Cloud Functions Framework Maven plugin

    This plugin allows you to run Cloud Functions Java code
    locally. Use the following terminal command to run a
    given function locally:

    mvn function:run -Drun.functionTarget=your.package.yourFunction
  -->
  <groupId>com.google.cloud.functions</groupId>
  <artifactId>function-maven-plugin</artifactId>
  <version>0.9.7</version>
  <configuration>
    <functionTarget>com.algogrit.app.HelloWorld</functionTarget>
  </configuration>
</plugin>
```

We will edit the `functionTarget` later.

Other dependencies:

- [Gson](https://mvnrepository.com/artifact/com.google.code.gson/gson/2.8.8)

```xml
<dependency>
  <groupId>com.google.code.gson</groupId>
  <artifactId>gson</artifactId>
  <version>2.8.8</version>
</dependency>
```

### Step 3: Defining the first HTTP function

Create a file `HelloHttp.java` under `src/main/java/com/algogrit/app/`. Do note the path depends on `groupId` which you set earlier.

```java
package com.algogrit.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class HelloHttp implements HttpFunction {
  private static final Logger logger = Logger.getLogger(HelloHttp.class.getName());

  private static final Gson gson = new Gson();

  @Override
  public void service(HttpRequest request, HttpResponse response)
      throws IOException {
    // Check URL parameters for "name" field
    // "world" is the default value
    String name = request.getFirstQueryParameter("name").orElse("world");

    // Parse JSON request and check for "name" field
    try {
      JsonElement requestParsed = gson.fromJson(request.getReader(), JsonElement.class);
      JsonObject requestJson = null;

      if (requestParsed != null && requestParsed.isJsonObject()) {
        requestJson = requestParsed.getAsJsonObject();
      }

      if (requestJson != null && requestJson.has("name")) {
        name = requestJson.get("name").getAsString();
      }
    } catch (JsonParseException e) {
      logger.severe("Error parsing JSON: " + e.getMessage());
    }

    var writer = new PrintWriter(response.getWriter());
    writer.printf("Hello %s!", name);
  }
}
```

### Step 4: Deploy

Using `gcloud`, replace `<your-name>` with your username to uniquely identify the deployed function:

```bash
gcloud functions deploy `<your-name>`-first-function \
  --entry-point com.algogrit.app.HelloHttp \
  --runtime java11 \
  --trigger-http \
  --allow-unauthenticated
```

Alternatively, running locally:

```bash
mvn function:run -Drun.functionTarget=com.algogrit.app.HelloHttp
```

---

## Using Gradle

### Step 1: Creating a simple gradle project

```bash
gradle init --type java-application
```

For the following prompts, choose:

- `build script DSL` = `groovy`
- `test framework` = `JUnit Jupiter`
- `project name` = `first-function`
- `Source Package` = `com.algogrit.app`

### Step 2: Edit the `build.gradle` file

```groovy
plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    id 'application'
}

repositories {
    jcenter()

    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

configurations {
    invoker
}

dependencies {
    // Every function needs this dependency to get the Functions Framework API.
    compileOnly 'com.google.cloud.functions:functions-framework-api:1.0.4'

    compileOnly 'com.google.cloud.functions:functions-framework-api:1.0.4'

    implementation 'com.google.code.gson:gson:2.8.8'

    // To run function locally using Functions Framework's local invoker
    invoker 'com.google.cloud.functions.invoker:java-function-invoker:1.0.2'
    // implementation 'com.google.cloud.functions.invoker:java-function-invoker:1.0.2'

    // Use JUnit Jupiter for testing.
    testImplementation 'org.junit.jupiter:junit-jupiter:5.7.2'
    // These dependencies are only used by the tests.
    testImplementation 'com.google.cloud.functions:functions-framework-api:1.0.4'
    // testImplementation 'junit:junit:4.13.2'
    testImplementation 'com.google.truth:truth:1.1.3'
    testImplementation 'org.mockito:mockito-core:3.4.0'

    // This dependency is used by the application.
    implementation 'com.google.guava:guava:30.1.1-jre'
}

tasks.named('test') {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

// Register a "runFunction" task to run the function locally
tasks.register("runFunction", JavaExec) {
    main = 'com.google.cloud.functions.invoker.runner.Invoker'
    classpath(configurations.invoker)
    inputs.files(configurations.runtimeClasspath, sourceSets.main.output)
    args(
        '--target', project.findProperty('run.functionTarget') ?: '',
        '--port', project.findProperty('run.port') ?: 8080
    )
    doFirst {
        args('--classpath', files(configurations.runtimeClasspath, sourceSets.main.output).asPath)
    }
}
```

### Step 3: Same as Maven!

### Step 4: Running locally

```bash
./gradlew runFunction -Prun.functionTarget=com.algogrit.app.HelloHttp
```
