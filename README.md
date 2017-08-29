#Setup
## Build gradle
`bradle build`

## Copy and set settings
`cp cp src/main/resources/config.properties config.properties`

## Run app
`java -Dws.properties=file:config.properties -jar build/libs/gielerak-service-0.1.0.jar`