# Garage Management System

Installation and running instructions:

1. Use any IDE (IntelliJ, Eclipse) to import the project.
2. After importing the project to the IDE, use your IDE to run the command maven clean install. It will download all the necessary packages that you need to use for the project.
3. To initiate the SQL scheme, you will need to enter the “application.properties” file located in the Spring project under src/main/resources and enter your DB username and password, it will create the entire scheme automatically when running the project.

Project Description:

The project consists of several layers and entities.

Entities - Vehicle, Wheel.
Enums - EnergyType, VehicleType.

Layers:

* The Repository layer: This layer communicates with the DB by using Hibernate and Spring Data JPA.
* The Service layer: This layer contains the business logic of the project.
* The Controller layer (RESTful API): Constitutes the access point to the server and does not have any BL.

The API also contains swagger documentation, after running the project head to http://localhost:8080/swagger-ui.html to use swagger.

There are 5 endpoints you can use (either using Postman or Swagger):

1. addVehicle - POST method, head to http://localhost:8080/vehicle/addVehicle with the following JSON body format for example:
	{
    	"modelName”:”Mazda 6”,
    	"licenseNumber”:”ABC123”,
    	"availableEnergy”:”15”,
    	"maximumTirePressure":"32",
    	"vehicleType":"CAR",
    	"energyType":"PETROL"
	}

2. getVehicleByLicenseNumber - GET method, head to http://localhost:8080/vehicle/getVehicleByLicenseNumber/ABC123 to get full details about a specific vehicle.

3. getAllVehicles - GET method, head to http://localhost:8080/vehicle/getAllVehicles to view all the vehicles that are available, here you can use sorting by any available property, for example, http://localhost:8080/vehicle/getAllVehicles?sort=modelName will sort all the vehicles by the model name.

4. inflateVehicleTiresByLicenseNumber - PUT method, head to http://localhost:8080/vehicle/inflateVehicleTiresByLicenseNumber/ABC123 to inflate all tires of a specific vehicle.

5. addEnergyByLicenseNumber - PUT method, head to http://localhost:8080/vehicle/addEnergyByLicenseNumber/ABC123/42 to refuel a car by 42 percent.
