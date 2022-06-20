# **Test Plan**

## Product Analysis

**Banana Guild Woes** is a product that will be used by members and guild masters of the Banana Guild. Adventurers can request reimbursements for expenses they had during their job. Guild masters then can accept or reject the request for compensation. 

This app will be hosted on the web and will store all data inside a database. The product will require the users to have a web browser.

## Test Strategy

[Link to Test Strategy](Test_Strategy.md)

## Test Objectives
Here are the following objectives to test:

Functionality:
- Login
- Making a request
- Canceling a pending request
- Updating a pending request (optional implementation)
- Viewing all requests
- Accepting requests as a Guild Master
- Rejecting requests as a Guild Master
- Traversing web service

GUI:
- Layout
- HTML/CSS
- Buttons
- Input
- Navbar

Security:
- Only access manage page as Guild Master
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

| No.   | Member        | Tasks |  
| ----- | ------------- | --------- |
|  1.   |    Andrew     | Does the role of manager, tester, developer, QA |
|  2.   |    Sarah      | Does the role of manager, tester, developer, QA |

**System Resources:**

| No.   | Resources         | Description |  
| ----- | ----------------  | --------- |
|  1.   |    Server         | Flask, AWS RDS (PostgreSQL) |
|  2.   |     Test tools    | Pytest, Selenium, Postman |
|  3.   |    Network        | Machine capable of hosting a localhost, or if manage to implement into AWS an internet connection |
|  4.   |     Computer      | Any OS which can connect to the network |

## Test Environment

The testers need to have the server running in order to access the web application. Tests will be run on personal computers with access to the internet in order to access the database. 

## Schedule & Estimation

| Task                          | Member                | Estimate Effort   |  
| ----------------------------- | --------------------- | ----------------- |
|  Create test specification    |     Andrew, Sarah     | 2 hours           |
|  Perform test execution       |     Andrew, Sarah     | 0.5 hours         |
|  Implement feature            |   Andrew, Sarah       | 3 hours           |
|  Perform test execution       |     Andrew, Sarah     | 1 hours           |
|  Test report/results          |    Andrew, Sarah      | 1 hours           |
|  Test debug                   |    Andrew, Sarah      | 2 hours           |
|  Test delivery                |     Andrew, Sarah     | 1.5 hours         |
|  Total                        |                       | 11 hours          |

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




