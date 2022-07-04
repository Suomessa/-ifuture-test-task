### AccountService

Start this service with the command
``` mvn spring-boot:run -Dspring-boot.run.arguments=--postgresDbPassword=378254``` where "postgresDbPassword" should be DataBase password.

### Client

Just launch it, specifying required parameters in command line arguments. Command line arguments have precedence.

```
    - 'rCount' - amount of sending 'getAmount'-requests threads
    - 'wCount' - amount of sending 'addAmount'-requests threads
    - 'url' - root url of AccountService
    - 'IDs' - list of ids
    
```