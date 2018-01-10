# README #

This repository contains a demonstrator of the use of CompletableFuture to block API calls until a queue worker has completed a certain task.

### Running locally ###

* Build the application.
```sh
$ mvn install
```
* Start the queue locally.
```sh
$ ./dev-tools/local-queue/start_local_activemq.sh
```
* Run the application.
```sh
$ java -jar api/target/demo-api-0.0.1-SNAPSHOT.jar
$ java -jar worker/target/demo-worker-0.0.1-SNAPSHOT.jar
```

* Use with curl
```sh
$ curl -H "Content-Type: appc":false}' http://localhost:8080/v1/tasks  # call will return after the task is finished (Thread.sleep of 10 sec).
$ curl -H "Content-Type: appc":true}' http://localhost:8080/v1/tasks # call will return immediately, before the work is done.
```