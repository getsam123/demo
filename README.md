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
	
# Problem Statement
## Vehicle Rental Service
### Features
- Rental service have multiple branches throughout the city. Assume one city for now.
- Each branch has limited number of different kinds of vehicles.
- Each vehicle can be booked with predefined price. For simplicity, assume fixed pricing.
- Each vehicle can be booked in multiples of 1 hour slot each. (For simplicity, assume slots of single day)
- No past bookings should be allowed.

###Requirements 
- Onboard a new branch with available vehicle
- Onboard new vehicle(s) of existing type to a particular branch
- Rent vehicle for a time slot and a vehicle type(lowest price as the default choice extendable to any other strategy).
- Display available vehicles for a given branch

### Design
- ####Entities: 
    - Branch: branchId, branchName
    - Vehicle: vehicleId, price, typeId, branchId, isFree, timeSlot
    - VehicleType: vehicleTypeId, vehicleTypeName
- ####APIs:
    - GET /branch : get all branches
    - GET /branch/{id}: get branch details
    - POST /branch: add branch
    - PUT /branch/{id}: update branch
    - POST /branch/{id}/vehicle: add vehicle to branch
    - GET /branch/{id}/vehicle : get all vehicles in a branch.
    - GET /branch/{id}/vehicle?isFree={free}&slot={timeSlot}&type={vehicleType}: get available vehicles at a branch
    - GET /vehicle?isFree={free}&slot={timeSlot}&type={vehicleType}: get available vehicles at all branches
    - POST /rent: add a renting of vehicle type and slot. 
