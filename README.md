## init
```shell script
%m2%\mvn archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4
```
git init .
git remote add origin https://github.com/Fuzessy/kr-app.git
git push -u origin master

## üzleti funkciók
mindmaster TDD implementálása

## spring boot felvétele
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

