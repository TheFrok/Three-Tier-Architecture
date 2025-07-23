# Feature: Exit Application

As a user, I want to be able to exit the application.

## Scenario: Exit the Application

Given the application is running
When the user clicks on the "File" menu and selects the "Exit" menu item
Then a confirmation dialog should be displayed
And if the user confirms, the application should close.

## Legacy Code Snippets

### `application/MainGUI.java`

```java
// ...
exit.setOnAction(new EventHandler<ActionEvent>() {

    @Override
    public void handle(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("");
        alert.setContentText("Are you sure you wan to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            AppData.getAppData().closeConnection(); //close connection
            System.exit(0);
        }

    }
});

CancelButton.setOnAction(new EventHandler<ActionEvent>() {

    @Override
    public void handle(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("");
        alert.setContentText("Are you sure you wan to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            AppData.getAppData().closeConnection(); //close connection
            System.exit(0);
        }

    }
});

mainDialog.setOnCloseRequest(event -> {
    AppData.getAppData().closeConnection(); //close connection
    System.exit(0);
});
// ...
```
