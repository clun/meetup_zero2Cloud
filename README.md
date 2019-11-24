# Meetup from Zero to Cloud

## 2. Introduction to Data Modelling

a. Clone the repository 

```
git clone https://github.com/clun/meetup_zero2Cloud.git
```

b. Access the folder `notebooks` and import the `Meetup_01 - Introduction to Data Modelling`

## 3. Cassandra Land

c. Access the folder `notebooks` and import the `Meetup_02 – Cassandra-Land PART 1`

d. Access the folder `notebooks` and import the `Meetup_03 – Cassandra-Land PART 2`

## 4. Application Development

a. In Apollo, in the home page, in the `action` button select `Download your connection details`. This will download a bundle as a zip file, save it somewhere we will need it to connect the application. Let's call this file the bundle.

<img src="https://github.com/DataStax-Academy/sample-webapp-apollo/blob/master/images/10.png" /> 

<img src="https://github.com/DataStax-Academy/sample-webapp-apollo/blob/master/images/11.png" /> 


b. Access the folder where you cloned the repository

```
cd sample-webapp-apollo
```

c. Locate the file `application.yaml` in the folder `src/main/resources` and edit the connection information to connect to your instance

```
# ----------------------------------------------------------
# Connectivity to Constellation Apollo
# ----------------------------------------------------------
constellation:
  apollo:
    username: cedrick
    password: cedrick
    keyspace: stockholm
    zipfile: src/main/resources/connection-apollo-demo.zip
```

As you can see I copied my zip file in `src/main/resources` but you can also reference a complete path on the disk.

d. You start the application by executing the following command at root level of your project 

```
mvn clean spring-boot:run
```

Open your browser on `http://localhost:8080`:

- First Screen will list dome details about your cluster. As you can see this a 3 nodes cluster

<img src="https://github.com/DataStax-Academy/sample-webapp-apollo/blob/master/images/12.png" /> 

- Second screen will list your keyspaces (single one here)

<img src="https://github.com/DataStax-Academy/sample-webapp-apollo/blob/master/images/13.png" /> 

- Select the keyspace by clicking, This will list the tables available in the keyspace

<img src="https://github.com/DataStax-Academy/sample-webapp-apollo/blob/master/images/14.png" /> 

- Chose `videos` for instance (should be created by the cell in DataStax studio)

<img src="https://github.com/DataStax-Academy/sample-webapp-apollo/blob/master/images/15.png" /> 

- You have some text to execute Queries. Run query with `EXECUTE` button

<img src="https://github.com/DataStax-Academy/sample-webapp-apollo/blob/master/images/16.png" /> 


## Et VOILA !












