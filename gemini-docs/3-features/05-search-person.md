# Feature: Search Person

As a user, I want to be able to search for a specific person by their first and last name.

## Scenario: Search for an Existing Person

Given the user is on the main application screen
When the user clicks on the "File" menu and selects the "Open File.." menu item
And the user enters the first and last name of an existing person
And clicks the "Search" button
Then the details of that person should be displayed.

## Scenario: Search for a Non-Existing Person

Given the user is on the main application screen
When the user clicks on the "File" menu and selects the "Open File.." menu item
And the user enters the first and last name of a person who does not exist in the database
And clicks the "Search" button
Then an error message should be displayed indicating that the person does not exist.

## Legacy Code Snippets

### `application/MainGUI.java`

```java
// ...
openFile.setOnAction(new EventHandler<ActionEvent>() {

    @Override
    public void handle(ActionEvent event) {
        searchDialogScene();

    }
});

public static void searchDialogScene() {
    GridPane gpane = new GridPane();

    Label firstName = new Label("First Name*:");
    firstName.setMaxSize(80, 80);
    final TextField firstNamefield = new TextField();
    firstNamefield.setPrefSize(100, 10);
    Label lastName = new Label("Last Name*:");
    final TextField lastNamefield = new TextField();

    final Text deleteConfirmation = new Text();
    gpane.add(deleteConfirmation, 2, 2);

    Button searchButton = new Button("Search");
    searchButton.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            try {
                String output = AppData.getAppData().getPerson(firstNamefield.getText(), lastNamefield.getText())
                        .toString();
                if (output != null) {
                    deleteConfirmation.setFill(Color.FIREBRICK);
                    deleteConfirmation.setText(output);
                }

            } catch (Exception e) {
                deleteConfirmation.setFill(Color.FIREBRICK);
                deleteConfirmation.setText(firstNamefield.getText() + " " + lastNamefield.getText()
                        + " does not exist in the database.");
            }

        }
    });
    HBox hbox = new HBox(10);
    hbox.setAlignment(Pos.CENTER);
    hbox.getChildren().addAll(searchButton, homeButton);
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
public Person getPerson(String firstName, String lastName) throws SQLException {
    Statement stmt = null;
    ResultSet result = null;
    Person person = null;
    try {
        stmt = conn.createStatement();
        String searchQuery = "SELECT * FROM PERSON WHERE (FIRSTNAME = '" + firstName + "') and (LASTNAME = '"
                + lastName + "');";
        result = stmt.executeQuery(searchQuery);
        while (result.next()) {
            person = new Person(result.getString("FIRSTNAME"), result.getString("LASTNAME"),
                    result.getString("DATEOFBIRTH"), result.getString("AGE"), result.getString("EMAIL"),
                    result.getString("PHONENUMBER"), result.getString("ADDRESS1"), result.getString("ADDRESS2"),
                    result.getString("CITY"), result.getString("ZIP"), result.getString("STATE"),
                    result.getString("COUNTRY"));
        }
        result.close();
        stmt.close();
    } catch (Exception e) {
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
    return person;
}
// ...
```
