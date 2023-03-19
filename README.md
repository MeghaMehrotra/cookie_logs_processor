# COOKIE LOGS PROCESSOR

This project will help you find the most active
cookie for a specific day.

### Prerequisites

* JDK 8
* Apache Maven
* Git

###Clone the project on your local

Use the command _`git clone https://github.com/MeghaMehrotra/cookie_logs_processor`_
to have the code base on your local

### How to build & test

For Building the project:
Go to the project path ./cookie_logs_processor/ and type the below command
```bash
mvn clean install
```

For testing:
Go to the project path ./cookie_logs_processor/ and type the below command

```bash
mvn test
```

## How to run locally

* Open the project on command line with the path ./cookie_logs_processor/ 

* You should run the command  _`java -jar target/cookie_logs_processor-0.0.1-SNAPSHOT.jar -f <your_file_name> -d <yyyy-MM-dd>`_

For example:- If your fileName which contains cookie logs is cookie_logs.csv and you need the most active cookie on for date 9th Dec 2018.
then your command would be as follows
` java -jar target/cookie_logs_processor-0.0.1-SNAPSHOT.jar -f cookie_log.csv -d 2018-12-09`

#### NOTE :- Your file Name or path should be relative to directory of the project~~~~

## Output

The output would contain all the cookies with most count for the date specified

