Project demo that utilizes Hadoop and Hive in Java with Spring Boot, implementing read/write operations with Hadoop and data manipulation with Hive.

# SETUP
1. install java 8+
2. install hadoop
3. install hive


### push file to hadoop
```agsl
-- push file to hadoop
hadoop fs -copyFromLocal organizations-100.csv /user/hadoop/organizations-100.csv
```

### create table & database in Hive
```agsl

-- create database

-- create database (if not found)
CREATE DATABASE IF NOT EXISTS hive_demo;

USE hive_demo;
Index,Organization Id,Name,Website,Country,Description,Founded,Industry,Number of employees

-- create table (if not found)
CREATE TABLE organizations (
    index INT,
    id STRING,
    name STRING,
    website STRING,
    country STRING,
    description STRING,
    founded STRING,
    industry STRING,
    number_of_employees INT
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
STORED AS TEXTFILE
LOCATION '/user/hadoop/your_data.csv';

```

### Config project
```agsl
# Hadoop Configuration
hadoop.fs.defaultFS=hdfs://localhost:9871
hadoop.home.path=/path/to/hadoop/home

# Hive Configuration
hive.datasource.url=jdbc:hive2://localhost:3777/hive_demo
hive.datasource.username=hungnm
hive.datasource.password=123456aA@
```

### run project
```agsl
run file HiveHadoopDemoApplication with IDE
or
build jar and run

mvn clean install
java jar target/*.jar 
```

### END POINT API
hive query
```agsl
GET /organizations // find all organizations
GET /organizations/{id} //find by id
```
hadoop
```agsl
POST /storage/upload //update file
GET /storage/download/{file-name} //download file
```
