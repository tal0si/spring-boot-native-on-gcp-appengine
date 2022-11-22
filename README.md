# Spring-Native

The purpose of this project is to build some code natively and to be compliant with GCP AppEngine prerequisites.

Spring Native allows us to wake up an API quicker.

You can check differences between Spring Classic and Spring Native at the bottom of the readme

## Getting started

### To run/build the project locally

### Install Graalvm
Current version :
> https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-22.3.0/graalvm-ce-java17-linux-amd64-22.3.0.tar.gz

Follow the installation guide
>https://www.graalvm.org/downloads/

If you're on Mac make sure to use this command
```
xattr -r -d com.apple.quarantine /Library/Java/JavaVirtualMachines/graalvm-ce-javaV-XX.Y.Z
```

### to build the project with intellij (recommended):
Go to preferences -> Build execution deployment section ->
Build Tools -> maven -> runner

Set your JRE to "Use project JDK"
and set GRAALVM_HOME env variable
Example :
```
GRAALVM_HOME=/Library/Java/JavaVirtualMachines/graalvm-ce-java17-XX.YY.ZZ/Contents/Home
```

Go to file -> project structure project sdk add->SK->JDK...
and select your newly installed graalvm

You're ready to build native with this command :
```
mvn -Pnative -DskipTests clean package
```

##  Deploy on GCP AppEngine

Install gcloud CLI
> https://cloud.google.com/sdk/docs/install

Login by using your private access key by using
```
gcloud auth activate-service-account --key-file
```

#### GCP services to activate :

* AppEngine
* Storage
* Cloud build API

#### Entrypoints in app.yaml :
*For native*
````
entrypoint: ./{YOUR-PATH-TO-YOUR-EXECUTABLE}
````

*Without native*
````
entrypoint: java -Xmx64m -jar ./{YOUR-PATH-TO-JAR-FILE}.jar
````

####  To deploy use :
```
gcloud app deploy --version X-Y-Z {path to your .yaml file}
```

### Build the project using Docker
> /!\ For the moment you cannot build this project with a mac M1 due to incompatibility with AMD emulation

#### Run the container :
*we'll use our existing image on DockerhHub*

*for more information you can go to the DockerHub image page :*

*https://hub.docker.com/repository/docker/talosi/mvn-spring-native/general*

Mac / Linux:
```
docker run -it -v $(pwd):/app talosi/mvn-spring-native bash
```
Windows
```
docker run -it -v %DIR%:/app talosi/mvn-spring-native bash
```

#### To build the project :
With tests :
```
mvn install
mvn -Pnative package (add -e -X to show logs DEBUG)
```
Without tests :
```
mvn install -DskipTests
mvn -DskipTests -Pnative package
```

The output file will be placed in $(pwd)/target file

##  Expected output

If you navigate to {YOUR_URL}/customers
you should see:

```
[{"id":1,"name":"A"},{"id":2,"name":"B"},{"id":3,"name":"C"},{"id":4,"name":"D"}]
```

To test the performance of the application you can go to {YOUR_URL}/fibonacci/{count}
example /fibonacci/10 should show
````
0 1 1 2 3 5 8 13 21 34
````

## Comparison between Classic and Native

### Starting time on AppEngine:
*Runs on AppEngine L1 (250mb RAM, 1CPU)*
* Native: 4s
* Classic: 10s

### Starting time locally
*Done with Mac M1 CPU*
* Native: 8ms
* Classic: 2115ms

### Local tests
*Done with Mac M1 CPU*

*For this part we also tested our code with Quarkus, but it seems that the performance problems we are facing comes from GraalVM*

| Fibonacci  | SpringBoot | SpringBoot Native | Quarkus | Quarkus Native |
|------------|------------|-------------------|---------|----------------|
| 1 000 000  | 77ms       | 125ms             | 103ms   | 110ms          |
| 5 000 000  | 204ms      | 426ms             | 363ms   | 356ms          |
| 10 000 000 | 327ms      | 854ms             | 435ms   | 663ms          |
| 30 000 000 | 894ms      | 2 495ms           | 1 523ms | 1 960ms        |
| 90 000 000 | 3 571ms    | 7 026ms           | 4 464ms | 6 161ms        |
