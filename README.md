# Product Applications Project

This project is composed of two main parts: the user interface (UI) and the application programming interface (API). These two components are separated into their own folders ui and api.

## User Interface

The user interface is a stand alone application that is created with Angular. This provides the primary way for users to interact with the system.

### Getting Started with the User Interface

To get started using the user interface application you will need to navigate to the folder on the command line then run the following commands to install all the dependencies:

```bash
npm install --force
```

### Running the User Interface

After the dependencies for the User Interface have been install the application can be started by running the following command:

```bash
ng serve
```

This will compile that application and run the application on port 4200. Once started you can open your web browser and load the web address: http://localhost:4200. Alternatively you can run the following command to run the application and automatically open the page in your browser:

```bash
ng serve -o 
```

Once the application is running it will automatically refresh the browser each time you make a change to the code.


## API

The API is a stand alone application that is created with Spring. 

### Running the API

The API will automatically install the dependencies on the first run of the application. The API can be started by running the following command from the api folder:

```bash
gradlew bootRun
```

There is a built in development profile for the API which will create an in-memory database and set up a sample set of data to use for testing. To run the API in the development profiles the following command can be executed: 

```bash
gradlew bootRun --args="--sprng.profiles.active=dev"
```

This same syntax can be used to run that API with any custom profile by changing the 'dev' parameter to the name of the desired profile.

