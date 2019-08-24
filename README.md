# bets-api

Bets-api is a system made to help clients register their bets on Sport games 

- Swagger local : (http://localhost:8080/swagger-ui.html)
- Swagger PROD : (https://pure-dawn-26163.herokuapp.com/swagger-ui.html)
- Swagger PROD EC2 : (http://ec2-34-218-226-215.us-west-2.compute.amazonaws.com:8080/swagger-ui.html)
- Logs PROD EC2 : (http://ec2-34-218-226-215.us-west-2.compute.amazonaws.com:8080/actuator/logfile)


## Connect EC2 instance
chmod 400 mehdi-macbook.pem
ssh -i "mehdi-macbook.pem" ec2-user@ec2-34-218-226-215.us-west-2.compute.amazonaws.com
nohup java -jar bet-api.jar &