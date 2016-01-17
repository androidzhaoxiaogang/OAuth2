# OAuth2
Spring boot with OAuth2 server, and android OAuth2 client showcase

Server side : 
This is a simple standalone Jhipster application (https://jhipster.github.io/).
Before start prepare Mysql database with configuration (rc/main/resources/config/application-dev.yml) :

```
datasource:
        driver-class-name: com.mysql.jdbc.jdbc2.optional.MysqlDataSource
        url: jdbc:mysql://localhost:3306/oauth2?useUnicode=true&characterEncoding=utf8
        name:
        username: oauth2
        password: oauth2
```
Then start server: 
```
mvn spring-boot:run
```
For android client change configuration in file (app/src/main/res/values/confilg.xml):
```
<string name="oauth2_client_id">oauth2app2</string>
    <string name="oauth2_client_secret">mySecretOAuthSecret2</string>
    <string name="oauth2_endpoints">http://192.168.1.199:8080/</string>
    <string-array name="oauth2_scopes">
        <item>read</item>
        <item>write</item>
    </string-array> 

```

