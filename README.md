# Climate Microservice

## LOCAL.env Settings

    content follows...

## Makefile

Enter ```make help``` to show the available make-usage-options.

## How to Start Microservice in Debug-Mode

In development mode, Quarkus starts by default with debug mode enabled, listening to port 5005 without suspending the JVM.

Start in dev-mode:
```
mvn compile quarkus:dev
```

Debug-Settings in IntelliJ:
   1. Add Configuration
   2. Click "+"
   3. Add new "Remote" Configuration
   4. Enter hostname and debug-port(=5005)


## climate project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .


## Packaging and running the application

The application is packageable using `./mvnw package`.
It produces the executable `climate-1.0-SNAPSHOT-runner.jar` file in `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/climate-1.0-SNAPSHOT-runner.jar`.
