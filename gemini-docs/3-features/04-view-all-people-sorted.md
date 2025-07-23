# Feature: View All People (Sorted)

As a user, I want to be able to view all the people stored in the database, sorted by name.

## Scenario: View All People Sorted

Given the user is on the main application screen
When the user clicks on the "View" menu and selects the "Sorted Database" menu item
Then a table should be displayed containing all the people from the database, sorted alphabetically by first and last name.

## Legacy Code Snippets

### `application/MainGUI.java`

```java
// ...
sortedData.setOnAction(new EventHandler<ActionEvent>() {

    @Override
    public void handle(ActionEvent event) {
        ArrayList<Person> datafromDatabase = new ArrayList<Person>();
        try {
            datafromDatabase.addAll(AppData.getAppData().findAllPeople());
            Collections.sort(datafromDatabase);
            createTableAndView(datafromDatabase);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
});
// ...
```

### `businesslayer/Person.java`

```java
// ...
@Override
public int compareTo(Person obj) {
    return (firstName + lastName).compareTo(obj.getFirstName() + obj.getLastName());
}
// ...
```
