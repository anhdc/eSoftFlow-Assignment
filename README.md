
## Install and run the project 
1. download/clone the project 
2. prepare the database
  * import in MySQL the self-contained file that comes with the project - [eSoftFlow-Assignment / src / main / resources / input_data / InputData.sql]
  * username/password - `rest_demo`/`rest_demo`
3. change to the root folder of the project and excute the following maven command 
  * `mvn clean install jetty:run  -Djetty.port=8888 -DskipTests=true`
  * now the REST api is up and running with Jetty on `localhost:8888` 
 

> **Note:** after you `mvn install` the application, you can deploy the generated __.war__ file in any web container like Tomcat for example. 

## Testing the project 
### SoapUI (recommended)
- [download and install SoapUI](http://sourceforge.net/projects/soapui/files/)
- import the REST project in SoapUI - [eSoftFlow-Assignment / src / test / resources / soapui / eSoftFlow-Assignment-Testsuite.xml]
