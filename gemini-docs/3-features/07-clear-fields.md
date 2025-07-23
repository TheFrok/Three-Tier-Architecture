# Feature: Clear Fields

As a user, I want to be able to clear all the fields in the "add person" form.

## Scenario: Clear All Fields

Given the user is on the "New" person screen
And has entered some information into the fields
When the user clicks the "Clear" button
Then all the input fields in the form should be cleared.

## Legacy Code Snippets

### `application/MainGUI.java`

```java
// ...
ClearButton.setOnAction(new EventHandler<ActionEvent>() {

    @Override
    public void handle(ActionEvent event) {
        firstNamefield.clear();
        lastNamefield.clear();
        emailField.clear();
        ageField.clear();
        phoneField.clear();
        address1Field.clear();
        address2Field.clear();
        cityField.clear();
        zipField.clear();

    }
});
// ...
```
