# Feature: User Login

As a user, I want to be able to log in to the application to access its features.

## Scenario: Successful Login

Given the application is running
And the user is on the login screen
When the user enters the correct username "user" and password "pass"
And clicks the "Log in" button
Then the user should be taken to the main application screen.

## Scenario: Failed Login

Given the application is running
And the user is on the login screen
When the user enters an incorrect username or password
And clicks the "Log in" button
Then the user should see an error message "Invalid Username or Password".

## Legacy Code Snippets

### `application/MainGUI.java`

```java
// ...
userPassInput.setOnKeyPressed(new EventHandler<KeyEvent>() {

    @Override
    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (userNameInput.getText().equals("user") && userPassInput.getText().equals("pass")) {
                mainDialog.setScene(gridScene);
                mainDialog.show();
            } else {
                logInfail.setFill(Color.FIREBRICK);
                logInfail.setText("Invalid Username or Password");
            }
        }
    }

});

Button loginButton = new Button("Log in");

loginButton.setOnAction(new EventHandler<ActionEvent>() {

    @Override
    public void handle(ActionEvent event) {
        if (userNameInput.getText().equals("Niraj") && userPassInput.getText().equals("pass123")) {
            mainDialog.setScene(gridScene);
            mainDialog.show();
        } else {
            logInfail.setFill(Color.FIREBRICK);
            logInfail.setText("Invalid Username or Password");
        }
    }
});
// ...
```
