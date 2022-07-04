# **Test Suite**

## **User Stories**

### Req No.1: As a user, I am able to login to my account

- `TC_01`: Tests if database `dao` will return valid login & user_info obj
- `TC_02`: Tests verification of login details
- `TC_03`: Tests if routing will take to correct html page upon success/failure
- `TC_04`: Tests if user tries to directly access page via URL while/without logging in.
  
### Req No.2: As a user, I am able to logout of my account
- `TC_03`: Tests if routing will take to correct html page upon success/failure
- `TC_04`: Tests if user tries to directly access page via URL while/without logging in.

### Req No.3: As a user, I am able to create a group listing

- `TC_05`: Tests if database `dao` will create and return valid list id
- `TC_06`: Tests verifications of list details
- `TC_07`: Tests if scripts populates html elements correctly
- `TC_08`: Tests html element interaction as intended
  
### Req No.4: As a user, I can remove a group listing

- `TC_09`: Tests if database `dao` will remove correctly
- `TC_07`: Tests if scripts populate html elements correctly
- `TC_04`: Tests if user tries to directly access page via URL while/without logging in.

### Req No.5: As a user, I can apply to join a listing

- `TC_10`: Tests if database `dao` updates listing
- `TC_07`: Tests if scripts populate html elements correctly

### Req No.6: As a user, I can leave a listing I've already joined

- `TC_11`: Tests if database `dao` will remove user from listing
- `TC_07`: Tests if scripts populates html elements correctly

### Req No.7: As a moderator, I am able to remove/edit any listings
- `TC_12`: Tests if database `dao` are modified and updated correctly
- `TC_07`: Tests if scripts populate html elements correctly

### Req No.8: As a moderator, I am able to ban/suspend malicious accounts
- `TC_12`: Tests if database `dao` are modified and updated correctly
- `TC_13`: Tests if modified users cannot login as intended.

## **Behaviorial Tests**

- `TC_14`: user logs in with credentials
  - valid, invalid
- `TC_15`:user create/remove listings
  - valid input, invalid input
- `TC_16`:user joins/leaves listings
  - valid input, invalid input
- `TC_17`:moderator manipulates listings and accounts 
  - add/remove users from a listing
  - edit/remove listings
  - freezing/unfreezing accounts
- `TC_18`: Happy paths Postman
- `TC_19`: Alternate paths Postman
