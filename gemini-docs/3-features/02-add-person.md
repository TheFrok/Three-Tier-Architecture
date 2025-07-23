# Feature: Add Person

As a user, I want to be able to add a new person's information to the database.

## Scenario: Successfully Add a New Person

Given the user is on the "New" person screen
When the user fills in all the required fields with valid information
And checks the "I agree to the Term and Conditions" box
And clicks the "Save" button
Then the new person's information should be saved to the database
And a confirmation message "Your record has been saved succesfully!" should be displayed.

## Scenario: Attempt to Add a New Person with Missing Fields

Given the user is on the "New" person screen
When the user leaves one or more required fields empty
And clicks the "Save" button
Then an error message "One or more fields are empty." should be displayed.

## Scenario: Attempt to Add a New Person with Invalid Data

Given the user is on the "New" person screen
When the user enters invalid data in one or more fields (e.g., invalid email format)
And clicks the "Save" button
Then an appropriate error message should be displayed (e.g., "Please enter a valid email.").

## Legacy Code Snippets

### `application/MainGUI.java`

```java
// ...
saveButton.setOnAction(new EventHandler<ActionEvent>() {

    @Override
    public void handle(ActionEvent event) {

        if (emailField.getText().isEmpty() || firstNamefield.getText().isEmpty()
                || lastNamefield.getText().isEmpty() || ageField.getText().isEmpty()
                || cityField.getText().isEmpty() || address1Field.getText().isEmpty()
                || zipField.getText().isEmpty()) {

            agreementCheck.setFill(Color.RED);
            agreementCheck.setText("One or more fields are empty.");
        } else if (!(ageField.getText().matches("^[0-9]+”))) {
            agreementCheck.setFill(Color.RED);
            agreementCheck.setText("Please check the age feild.");

        } else if (!(emailField.getText().matches("^[\\_A-Za-z0-9-\\+]+(\\.[\\_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))) {
            /**
             * Email Pattern: https://examples.javacodegeeks.com/core-java/util/regex/
             * matcher/validate-email-address-with-java-regular-expression- example/
             **/
            agreementCheck.setFill(Color.RED);
            agreementCheck.setText("Please enter a valid email.");

        } else if (!(phoneField.getText().matches("\\d{10}"))) {
            agreementCheck.setFill(Color.RED);
            agreementCheck.setText("Please enter a valid 10 digit phone number in this format:1234567890");
        } else if (!(zipField.getText().matches("^[0-9]{5}(?:-[0-9]{4})?$"))) {
            agreementCheck.setFill(Color.RED);
            agreementCheck.setText("Invalid zip code");
        } else if (agreementBox.isSelected()) {
            String dateString = date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Person person = new Person(firstNamefield.getText(), lastNamefield.getText(), dateString,
                    ageField.getText(), emailField.getText(), phoneField.getText(), address1Field.getText(),
                    address2Field.getText(), cityField.getText(), zipField.getText(), stateField.getText(),
                    cBox.getValue().toString());
            try {
                AppData.getAppData().addPerson(person);
            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Your record has been saved succesfully!");
            alert.showAndWait();
        } else {
            agreementCheck.setFill(Color.RED);
            agreementCheck.setText("You must agree the terms and conditions.");
        }

    }

});
// ...
```

### `businesslayer/AppData.java`

```java
// ...
public void addPerson(Person person) throws SQLException {
    Statement stmt = null;
    try {
        stmt = conn.createStatement();
        String info = "INSERT INTO PERSON (FIRSTNAME,LASTNAME,DATEOFBIRTH,AGE,EMAIL,PHONENUMBER,ADDRESS1,ADDRESS2,CITY,ZIP,STATE,COUNTRY) "
                + "VALUES ('" + person.getFirstName().toString() + "','" + person.getLastName().toString() + "','"
                + person.getDateOfBirth().toString() + "','" + person.getAge().toString() + "','"
                + person.getEmail().toString() + "','" + person.getPhoneNumber().toString() + "','"
                + person.getAddress1().toString() + "','" + person.getAddress2().toString() + "','"
                + person.getCity().toString() + "','" + person.getZipCode().toString() + "','"
                + person.getState().toString() + "','" + person.getCountry().toString() + "')";
        stmt.executeUpdate(info);
        stmt.close();
    } catch (Exception e) {
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
}
// ...
```
