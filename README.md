# Introduction

Demo application based on [dropwizard-example](https://github.com/dropwizard/dropwizard/tree/master/dropwizard-example)

# Running The Application

To test the example application run the following commands.

* To create the example, package the application using [Apache Maven](https://maven.apache.org/) from the root dropwizard directory.

        cd demo
        ./mvnw package

* To setup the h2 database run.

        java -jar target/demo-1.0.0-SNAPSHOT.jar db migrate example.yml

* To run the server run.

        java -jar target/demo-1.0.0-SNAPSHOT.jar server example.yml

* To hit the Hello World example (hit refresh a few times).

	http://localhost:8080/hello-world

* To post data into the application.

	curl -H "Content-Type: application/json" -X POST -d '{"fullName":"Other Person","jobTitle":"Other Title"}' http://localhost:8080/people
	
	open http://localhost:8080/people
