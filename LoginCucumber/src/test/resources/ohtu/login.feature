Feature: User can log in with valid username/password-combination

    Scenario: user can login with correct password
       Given command login is selected
       When  username "pekka" and password "akkep" are entered
       Then  system will respond with "logged in"

    Scenario: user cannot login with incorrect password
       Given command login is selected
       When  username "pekka" and password "akkepp" are entered
       Then  system will respond with "wrong username or password"

    Scenario: nonexistent user cannot login to
       Given command login is selected
       When  username "pekkap" and password "akkep" are entered
       Then  system will respond with "wrong username or password"