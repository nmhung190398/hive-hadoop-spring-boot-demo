mvn clean install

rm -r ./output
hadoop jar target/hadoop-demo-1.0-SNAPSHOT.jar ./input output
