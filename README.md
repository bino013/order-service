# Getting Started

### Pre-requisite
To use this application, you must install the following:
* Java 13
* Docker
* Maven

The application is build on Java 13 but it is compatible with Java 8, just change the java version to 1.8 in `pom.xml`

To run and stop the application, a `start.sh` and `stop.sh` is already prepared on the root directory

### Secret Key
To use your own secret key for the Google Map API, kindly edit `start.sh` and replace the value for `DISTANCE_SERVICE_KEY`

