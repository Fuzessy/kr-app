## init
```shell script
%m2%\mvn archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4
```
git init .
git remote add origin https://github.com/Fuzessy/kr-app.git
git push -u origin master

## üzleti funkciók
mindmaster TDD implementálása

## spring boot 
### starter dependency
```xml

  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.0.1.RELEASE</version>
  </parent>
...
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

```

### webapp starter
```java
@SpringBootApplication
public class StartKrApplication {
    public static void main(String[] args){
        SpringApplication.run(StartKrApplication.class);
    }
}
``` 
### spring boot build plugin beállítása
```xml
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
``` 

### Rest controller beállítása
```java
@RestController
@RequestMapping("application/request/path")
public class MyController {

    @GetMapping("other-path")
    public String myRequest(){
        return "hello";
    }
}
```

