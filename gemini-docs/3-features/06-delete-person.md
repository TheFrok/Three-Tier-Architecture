# Feature: Delete Person

As a user, I want to be able to delete a person's information from the database.

## Scenario: Delete an Existing Person

Given the user is on the main application screen
When the user clicks on the "Edit" menu and selects the "Delete" menu item
And the user enters the first and last name of an existing person
And clicks the "Delete" button
Then the person's information should be deleted from the database
And a confirmation message should be displayed.

## Scenario: Attempt to Delete a Non-Existing Person

Given the user is on the main application screen
When the user clicks on the "Edit" menu and selects the "Delete" menu item
And the user enters the first and last name of a person who does not exist in the database
And clicks the "Delete" button
Then an error message should be displayed indicating that the person does not exist.

## Legacy Code Snippets

### `application/MainGUI.java`

```java
// ...
delete.setOnAction(new EventHandler<ActionEvent>() {

    @Override
    public void handle(ActionEvent event) {
        deletePerson();
    }
});

public static void deletePerson() {

    GridPane gpane = new GridPane();

    Label firstName = new Label("First Name*:");
    firstName.setMaxSize(80, 80);
    final TextField firstNamefield = new TextField();
    firstNamefield.setPrefSize(100, 10);
    Label lastName = new Label("Last Name*:");
    final TextField lastNamefield = new TextField();

    final Text searchConfirmation = new Text();
    gpane.add(searchConfirmation, 2, 2);

    Button deleteButton = new Button("Delete");
    deleteButton.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            try {
                AppData.getAppData().deletePerson(firstNamefield.getText(), lastNamefield.getText());
                searchConfirmation.setFill(Color.FIREBRICK);
                searchConfirmation.setText(firstNamefield.getText() + " information has been deleted from the database.");

            } catch (SQLException e) {
                searchConfirmation.setFill(Color.FIREBRICK);
                searchConfirmation.setText("Person you entered does not exist.");
            }

        }
    });
    HBox hbox = new HBox(10);
    hbox.setAlignment(Pos.CENTER);
    hbox.getChildren().addAll(deleteButton, homeButton);
    VBox vbox = new VBox();
    vbox.setSpacing(10);
    vbox.setPadding(new Insets(10, 0, 0, 10));
    vbox.setAlignment(Pos.CENTER);
    vbox.getChildren().addAll(firstName, firstNamefield, lastName, lastNamefield, hbox);

    gpane.add(vbox, 1, 1);
    Scene deleteScene = new Scene(gpane, 1200, 550);
    mainDialog.setScene(deleteScene);
    mainDialog.show();
}
// ...
```

### `businesslayer/AppData.java`

```java
// ...
public void deletePerson(String firstName, String lastName) throws SQLException {
    Statement stmt = null;
    try {
        stmt = conn.createStatement();
        String deleteQuery = "DELETE FROM PERSON WHERE (FIRSTNAME = '" + firstName + "') and (LASTNAME = '"
                + lastName + "');";
        stmt.executeUpdate(deleteQuery);
        stmt.close();
    } catch (Exception e) {
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
}
// ...
```
