# **Test Plan**

## Product Analysis

**RPGHub** is a product that will be used by gaming users already registered for the service. Users will be able to create and remove raid requests with their ID. In addition, they will be able to approve or reject other users' application to enlist in a raid request. Users can also remove users from a listing that have already been accepted.

Moderator users accounts will also be able to remove or edit any listings. They can add/remove users from any particular listing, and they can freeze other user accounts so that they cannot sign in.

This app will be hosted on the web and will store all data inside a database. The product will require the users to have a web browser.

## Test Strategy

[Link to Test Strategy](Test_Strategy.md)

## Test Objectives
Here are the following objectives to test:

Functionality:
- Login/logout service
- Create/remove group listings
- Browse group listings by category (List filter/pages/?)
- Apply to join a group (as certain role)
- Accept/Reject requests as a Group Leader
- Group Leader can kick members from active
- Moderator ability force remove group listing
- Moderator ability force add/remove from group listing
- Traversing web service

GUI:
- Layout
- HTML/CSS
- Buttons
- Input
- Navbar

Security:
- Only group leader or moderator can delist groups
- Database entries are secure and rollback if any corruption is detected.
- Authorize user access

Database:
- Add entries
- Update entries
- Delete entries
- Query entries


## Test Criteria

- If over 40% of tests fail all other implementations will be suspended until all defects are resolved.
- If over 75% of tests pass the feature will be considered suitable for public use and then the next feature can implemented

## Resource Allocation Plan

**Human Resources:**

| No.   | Member        | Tasks                                           |  
| ----- | ------------- | ----------------------------------------------- |
|  1.   |    Andrew     | Does the role of manager, tester, developer, QA |
|  2.   |    Brian      | Does the role of manager, tester, developer, QA |
|  3.   |    Anthony    | Does the role of manager, tester, developer, QA |

**System Resources:**

| No.   | Resources         | Description                    |  
| ----- | ----------------  | ------------------------------ |
|  1.   |    Server         | Javalin, AWS RDS (PostgreSQL)  |
|  2.   |    Test tools     | TestNG, Selenium, Postman      |
|  3.   |    Network        | Machine capable of hosting a localhost, or if manage to implement into AWS an internet connection |
|  4.   |    Computer       | Any OS which can connect to the network |

## Test Environment

The testers need to have the server running in order to access the web application. Tests will be run on personal computers with access to the internet in order to access the database. 

## Schedule & Estimation

| Task                          | Member                    | Estimate Effort   |  
| ----------------------------- | ------------------------- | ----------------- |
|  Create test specification    |   Andrew, Brian, Anthony  | 2 hours           |
|  Perform test execution       |   Andrew, Brian, Anthony  | 0.5 hours         |
|  Implement feature            |   Andrew, Brian, Anthony  | 3 hours           |
|  Perform test execution       |   Andrew, Brian, Anthony  | 1 hours           |
|  Test report/results          |   Andrew, Brian, Anthony  | 1 hours           |
|  Test debug                   |   Andrew, Brian, Anthony  | 2 hours           |
|  Test delivery                |   Andrew, Brian, Anthony  | 1.5 hours         |
|  Total                        |                           | 11 hours          |

## Test Deliverables
Before Testing:
- Test Plan
- Test Strategy
- Test Suite

During Testing:
- Test Data
- Test Scripts
- Requirement Testing Matrix
- Execution Logs

After Testing:
- Test Report/Results
- Defect Report
- Installation Guide
- Test Procedure Guide




