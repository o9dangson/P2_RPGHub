# **Test Strategy**
## **Scope**

The product, **Banana Guild Woes**, is an application that can serve as an expenditure reimbursement system for any small company or group. This app should allow employees and managers to submit new reimbursement requests and review all requests while also allowing managers to accept or reject pending requests.

This document will be reviewed by all developing team members at regular intervals. This test strategy will be approved by the developers and client before any work is done.

## **Test Approach**

- Each member of the development team will be responsible for participating in all phases of testing. Thus, all members will be designated to all roles as well (unit tester, feature implementation, debugging, etc.)
- A `Requirement Testing Matrix (RTM)` will be used to keep of all testing that has been completed and accepted.
- All defects, whether discovered or preexisting, will be properly documented on the `RTM` along with their severity and priority. Eventual bug fixes will be indicated on the `RTM` as well.
- Acceptance Criteria for a feature will be set at a minimum of `75%` coverage.

### **Unit**

- During this phase, the project will use Test Driven Development as its basis. The technology used for unit testing is `Pytest`. Before a functionality is implemented a unit test will be written with once passing case and several failing cases. Stubbing will be used to support bottom-up testing. `Pytest` parametrize and fixtures will be used to do so.

### **Integration**
- `Postman` will be used for testing happy and bad paths, thus verifying routing functionality as expected features are added. Alternate path testing will be done as well. These tests will be done after features are completed. 

### **System**
- `Selenium/Behave` are used here to verify features meet the requirements of the client and that various code works as intended as a whole. User stories will be used to create scenarios to be run in behave and it will be automated by selenium. 

### **Acceptance**
- The acceptance criteria that will be enforced for testing will be using behave. If all the steps in a feature file pass then the user story implementation will be considered complete.

## **Test Environment**

All coding and testing will be done using `Visual Studio Code` as the IDE. `Github` repositories will be used to stored all relevant code. Google Drive will be used to store relevant testing documentation. This will all done on a Windows OS.

## **Testing Tools**

- Selenium with Google Chrome web driver
- Behave
- Postman
- Pytest
- (Code Coverage tool TBD)
- All tools are open source and have no commercial requirements

## **Release Control**

- Each feature will be worked on in separate `branches` in the Git repo.
- Upon completion of a feature, the members of the development team will co-approve a branch before it can be merged into the `main` branch
- Any bugs must be heavily considered before approval to pushes to the `main` branch.
- All members must be working on the latest `main` branch to avoid unnecessary errors.

## **Risk Analysis**

- Testing in regards to the database may cause the integrity of the database to be compromised.
    - The developers will make sure to practice `ACID` and not do any interactions that are inconsistent with the database integrity.
    - However, in the case of a failure, backup scripts will be put in place to recreate and repopulate the tables.
- Defects that are created during development will pose a risk to the functionality of the project.
    - The developers will document any defects encountered, as soon as they arise. They will then attempt to fix the defect before moving on.
    - In the case of a defect that was missed, the developers will finish the current iteration then fix any defects missed.
- Using selenium web drivers will pose a risk to the automated testing in the case of updated software.
    - The developers will make sure to keep the web driver up-to-date and make sure the browsers are as well.
    - In the case of selenium havng an error, the solution will be to find out what version of the webdriver will be needed then to replace it.

## **Review and Approvals**

- Additions/Completion of features must be co-approved.
- Pushes to `main` branch of repository must be co-approved and with meaningful comments.
- Any activities must be labeled with the corresponding team members ID.
