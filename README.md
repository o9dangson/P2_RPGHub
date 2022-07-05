# P2_RPGHub
Avid rpg gamers everywhere struggle with many difficulties when trying to complete raids/events. `RPGHub` is a web service that allows gamers anywhere to login into their accounts and streamline the process of setting up raid requests and managing players who wish to participate in various raid requests.



# Technologies
* Maven - Project Managemetn
* TestNG - Unit Testing
* Selenium Java - Automation testing
* Amazon RDS - DB
* Cucumber - BDD testing
* JaCoCo - testing coverage
* Javalin - local hosting for Database

# Install
1. Open git bash, and navigate to the directory you would like to save the program
2. in gitBash, type git clone https://github.com/o9dangson/P2_RPGHub.git
3. once fully cloned, navigate to the cloned repo

## Prerequisites
* For the program to launch, JVM and JRE is required
* To access this program, Javalin must be running  
* Have VSCode installed  

## Steps
1. `code rpghub/.`
    -  Opens vscode in the Java Project Folder
2. `App.java`
    - Runs the program
3. Once Javalin is running ctrl-click the local-ip where the site is hosted


## Testing
Testing was performed with Selenium to automate happy/alternate paths
1.  `App.java`
    - Run the program in one terminal
2.  `mvn verify`
    - Runs and builds all the unit tests and the selenium tests as specified in the `test_suite.xml`
    - `jacoco` report is in `target/site/jacoco/index.html` upon test build
    
# Link to Test Documents

[Test Report](https://docs.google.com/spreadsheets/d/1vrbJxwkwGh9Mivx3jvNStIw6cBCDLl5n84kohAFXwAg/)

[Requirement Traceability Matrix](https://docs.google.com/spreadsheets/d/165YTA7sOHqGW7WqFuZkqycsUHZZUzTAaR4-t2JEHZBU/)

[User Stories](https://trello.com/b/fjMshtac)

[Presentation Slides](https://docs.google.com/presentation/d/11kVvdvnZV9q5z-cfoiwA9d-AvuurWYiLvRk74mEw7Cw)

[All Other Test Documentation](/documentation)
