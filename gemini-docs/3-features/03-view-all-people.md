# Feature: View All People

As a user, I want to be able to view all the people stored in the database.

## Scenario: View All People

Given the user is on the main application screen
When the user clicks on the "View" menu and selects the "Database" menu item
Then a table should be displayed containing all the people from the database.

## Legacy Code Snippets

### `application/MainGUI.java`

```java
// ...
database.setOnAction(new EventHandler<ActionEvent>() {

    @Override
    public void handle(ActionEvent event) {
        ArrayList<Person> datafromDatabase = new ArrayList<Person>();
        try {
            datafromDatabase.addAll(AppData.getAppData().findAllPeople());
            createTableAndView(datafromDatabase);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
});

@SuppressWarnings("unchecked")
public static void createTableAndView(ArrayList<Person> listofPeople) throws SQLException {
    /** Table view mainDialog box layout **/

    final Label label = new Label("Database");
    label.setFont(new Font("Georgia", 20));
    final ObservableList<Person> data = FXCollections.observableArrayList(listofPeople);

    TableView<Person> table = new TableView<Person>();
    table.setItems(data);

    table.setEditable(true);

    TableColumn<Person, String> firstNameCol = new TableColumn<Person, String>("First Name");
    firstNameCol.setPrefWidth(70);
    firstNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));

    // ... other columns

    table.getColumns().addAll(firstNameCol, lastNameCol, emailCol, dobCol, ageCol, phoneCol, address1Col,
            address2Col, cityCol, zipCol, stateCol, countryCol);

    final VBox vboxforTable = new VBox();

    vboxforTable.setSpacing(5);
    vboxforTable.setPadding(new Insets(10, 0, 0, 10));
    vboxforTable.getChildren().addAll(homeButton, label, table);

    Scene tableScene = new Scene(vboxforTable, 1200, 650);
    mainDialog.setScene(tableScene);
    mainDialog.show();

}
// ...
```

### `businesslayer/AppData.java`

```java
// ...
public ArrayList<Person> findAllPeople() throws SQLException {
    Statement stmnt = null;
    ResultSet result = null;
    ArrayList<Person> listofPeople = new ArrayList<Person>();
    try {
        stmnt = conn.createStatement();
        result = stmnt.executeQuery("SELECT * FROM PERSON");
        while (result.next()) {
            listofPeople.add(new Person(result.getString("FIRSTNAME"), result.getString("LASTNAME"),
                    result.getString("DATEOFBIRTH"), result.getString("AGE"), result.getString("EMAIL"),
                    result.getString("PHONENUMBER"), result.getString("ADDRESS1"), result.getString("ADDRESS2"),
                    result.getString("CITY"), result.getString("ZIP"), result.getString("STATE"),
                    result.getString("COUNTRY")));
        }
        result.close();
        stmnt.close();
    } catch (Exception e) {
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
    return listofPeople;
}
// ...
```
