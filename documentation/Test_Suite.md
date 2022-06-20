# **Test Suite**

## **User Stories**

### Req No.1: As a user, I am able to login to my account

- `TC_01`: Tests if database `dao` will return valid login & user_info obj
- `TC_02`: Tests verification of login details (Behave)
- `TC_03`: Tests if routing will take to correct html page upon success/failure
- `TC_04`: Tests if user tries to directly access page via URL while/without logging in.

### Req No.2: As a user, I am able to create a new reimbursement request

- `TC_05`: Tests if database `dao` will create and return valid req id
- `TC_06`: Tests verifications of request details
- `TC_07`: Tests html element interaction as intended
  
### Req No.3: As a user, I am able to see ongoing and previous requests

- `TC_08`: Tests if database `dao` will return valid requests obj's
- `TC_09`: Tests if scripts populates html elements correctly
- `TC_04`: Tests if user tries to directly access page via URL while/without logging in.

### Req No.4: As a user, I am able to cancel ongoing requests

- `TC_05`: Tests if database `dao` will delete requests correctly
- `TC_09`: Tests if scripts populates html elements correctly

### Req No.5: As a user, I am able to see total spent on requests

- `TC_09`: Tests if scripts populates html elements correctly

### Req No.6: As a manager, I am able to approve/reject ongoing requests that aren't mine

- `TC_05`: Tests if database `dao` will update requests correctly.
- `TC_10`: Tests if scripts populates html elements correctly
- Tests if routing will take to correct html page upon html interaction
- `TC_04`: Tests if user tries to directly access page via URL while/without logging in.
- Tests if user is valid account before accessing manager page.

### Req No.7: As a user, I am able to use the navbar to traverse my account

- `TC_11`: Tests navbar navigation
  - Home: sends to Login page if not signed in, User Page otherwise
  - Manage: sends to Manage page if user is Manager, isn't visible otherwise
  - Log Out: sends to Login Page and removes user details from session, isn't visible if not signed in.

## **Behaviorial Tests**

- `TC_12`: user logs in with credentials
  - valid, invalid
- `TC_13`:user creating requests
  - valid input, invalid input
- `TC_14`:manager manages requests
  - accept, reject
- `TC_15`:user cancels pending request
  - existing

## **Logging**

- `TC_16`: logging activity
