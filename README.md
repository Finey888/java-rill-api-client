java-api-client
===============

Client library for Rillate API

To generate curl strings executing either oauth 1 legged or signed requests, use
mvn -Pcommand-line-client clean package
cd target
java -jar rill-rest-client-1.0-SNAPSHOT.jar -type oauth -oauth_consumer_key ak-feda9011f9d01a9dd92739967269514f -secret sk-07ab0a1e85c17553b667ad96c5c4ac0dd15b828f085f5c7a14c8df88f12d99b2 -oauth_nonce 1234 oauth_version "1.0" -url https://localhost:8080/api/mqs/v1/query -firstname John -lastname Doe -dob 1998-09-09 school USCS
or
java -jar rill-rest-client-1.0-SNAPSHOT.jar -type sig -apikey ak-feda9011f9d01a9dd92739967269514f -secret sk-07ab0a1e85c17553b667ad96c5c4ac0dd15b828f085f5c7a14c8df88f12d99b2 -url http://localhost:8080/api/mqs/v1/query -firstname John -lastname Doe -dob 1998-09-09 school USCS