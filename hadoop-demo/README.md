# demo word count with hadoop
## setup

```agsl
brew install hadoop
```
```agsl
<plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <configuration>
                    <archive>
                        <manifest>
                            <addClasspath>true</addClasspath>
                            <mainClass>devnguyen.net.WordCount</mainClass>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
```

## run
```agsl
sh ./run.sh
```

### run.sh file
```agsl
mvn clean install

rm -r ./output
hadoop jar target/hadoop-demo-1.0-SNAPSHOT.jar ./input output
```
