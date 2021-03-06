package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class UsersWindow extends BaseWindow
{
//	LinkedList<Button> removeUserButtons;
	UsersList usersList;
	TextField newUserName;
	TextField newUserID;
	TextField newUserUsername;
	PasswordField newUserPassword;

	public UsersWindow(UsersList inputUserList)
	{
		super(false);
		usersList = inputUserList;
		buildStage(false);
		stage.setTitle("Users");
	}

	private void addUser()
    {
		if ((newUserName != null) && (!newUserName.getText().equals("")) &&
        	    (newUserID != null) && (!newUserID.getText().equals("")) &&
        	    (newUserUsername != null) && (!newUserUsername.getText().equals("")) &&
        	    (newUserPassword != null) && (!newUserPassword.getText().equals("")))
    	{

        	usersList.user.add(new User(newUserName.getText(), newUserUsername.getText(), newUserPassword.getText(), newUserID.getText()));
        	usersList.writeFile();
        	mainLayout.setCenter(createCenterPane());
        	stage.sizeToScene();
    	}
    }

	private EventHandler<ActionEvent> removeUser(int userIndex)
    {
        return new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
            	usersList.user.remove(userIndex);
            	usersList.writeFile();
            	mainLayout.setCenter(createCenterPane());
            	stage.sizeToScene();
            }
        };
    }

	@Override
	protected VBox createTopPane() {return null;}

	@Override
	protected GridPane createLeftPane() {return null;}

	@Override
	protected GridPane createCenterPane()
	{
		GridPane myGridPane = new GridPane();
		int verticalIndex = 0;
		int horizontalIndex = 0;

		Label nameLabel = new Label("Name");
		nameLabel.setStyle("-fx-font-weight: bold");
		GridPane.setConstraints(nameLabel, horizontalIndex++, verticalIndex);
		GridPane.setMargin(nameLabel, new Insets(5, 5, 5, 5));
		myGridPane.getChildren().add(nameLabel);

		Label idLabel = new Label("ID");
		idLabel.setStyle("-fx-font-weight: bold");
		GridPane.setConstraints(idLabel, horizontalIndex++, verticalIndex);
		GridPane.setMargin(idLabel, new Insets(5, 5, 5, 5));
		myGridPane.getChildren().add(idLabel);

		Label usernameLabel = new Label("Username");
		usernameLabel.setStyle("-fx-font-weight: bold");
		GridPane.setConstraints(usernameLabel, horizontalIndex++, verticalIndex);
		GridPane.setMargin(usernameLabel, new Insets(5, 5, 5, 5));
		myGridPane.getChildren().add(usernameLabel);

		Label passwordLabel = new Label("Password");
		passwordLabel.setStyle("-fx-font-weight: bold");
		GridPane.setConstraints(passwordLabel, horizontalIndex++, verticalIndex);
		GridPane.setMargin(passwordLabel, new Insets(5, 5, 5, 5));
		myGridPane.getChildren().add(passwordLabel);

		//admin user
		verticalIndex++;
		horizontalIndex = 0;
		nameLabel = new Label(usersList.admin.name);
		nameLabel.setFont(Font.font("Verdana", FontPosture.ITALIC, 12));
		GridPane.setConstraints(nameLabel, horizontalIndex++, verticalIndex);
		GridPane.setMargin(nameLabel, new Insets(5, 5, 5, 5));
		myGridPane.getChildren().add(nameLabel);

		idLabel = new Label(usersList.admin.id);
		idLabel.setFont(Font.font("Verdana", FontPosture.ITALIC, 12));
		GridPane.setConstraints(idLabel, horizontalIndex++, verticalIndex);
		GridPane.setMargin(idLabel, new Insets(5, 5, 5, 5));
		myGridPane.getChildren().add(idLabel);

		usernameLabel = new Label(usersList.admin.username);
		usernameLabel.setFont(Font.font("Verdana", FontPosture.ITALIC, 12));
		GridPane.setConstraints(usernameLabel, horizontalIndex++, verticalIndex);
		GridPane.setMargin(usernameLabel, new Insets(5, 5, 5, 5));
		myGridPane.getChildren().add(usernameLabel);

		passwordLabel = new Label("****");
		passwordLabel.setFont(Font.font("Verdana", FontPosture.ITALIC, 12));
		GridPane.setConstraints(passwordLabel, horizontalIndex++, verticalIndex);
		GridPane.setMargin(passwordLabel, new Insets(5, 5, 5, 5));
		myGridPane.getChildren().add(passwordLabel);
		// end of admin user

//		removeUserButtons = new LinkedList<>();
		EventHandler<ActionEvent> action;

		for (int i = 0; i < usersList.user.size(); i++)
		{
			horizontalIndex = 0;
			verticalIndex++;

			nameLabel = new Label(usersList.user.get(i).name);
			GridPane.setConstraints(nameLabel, horizontalIndex++, verticalIndex);
			GridPane.setMargin(nameLabel, new Insets(5, 5, 5, 5));
			myGridPane.getChildren().add(nameLabel);

			idLabel = new Label(usersList.user.get(i).id);
			GridPane.setConstraints(idLabel, horizontalIndex++, verticalIndex);
			GridPane.setMargin(idLabel, new Insets(5, 5, 5, 5));
			myGridPane.getChildren().add(idLabel);

			usernameLabel = new Label(usersList.user.get(i).username);
			GridPane.setConstraints(usernameLabel, horizontalIndex++, verticalIndex);
			GridPane.setMargin(usernameLabel, new Insets(5, 5, 5, 5));
			myGridPane.getChildren().add(usernameLabel);

			passwordLabel = new Label("****");
			GridPane.setConstraints(passwordLabel, horizontalIndex++, verticalIndex);
			GridPane.setMargin(passwordLabel, new Insets(5, 5, 5, 5));
			myGridPane.getChildren().add(passwordLabel);

			action = removeUser(i);
			Button removeUser = new Button("Remove User");
			removeUser.setOnAction(action);
			GridPane.setConstraints(removeUser, horizontalIndex++, verticalIndex);
			GridPane.setMargin(removeUser, new Insets(5, 5, 5, 5));
			myGridPane.getChildren().add(removeUser);
//			removeUserButtons.add(removeUser);
		}

		horizontalIndex = 0;
		verticalIndex++;

		newUserName = new TextField();
		newUserName.setPromptText("New User's Name");
		GridPane.setConstraints(newUserName, horizontalIndex++, verticalIndex);
		GridPane.setMargin(newUserName, new Insets(5, 5, 5, 5));
		myGridPane.getChildren().add(newUserName);

		newUserID = new TextField();
		newUserID.setPromptText("New User's ID");
		GridPane.setConstraints(newUserID, horizontalIndex++, verticalIndex);
		GridPane.setMargin(newUserID, new Insets(5, 5, 5, 5));
		myGridPane.getChildren().add(newUserID);

		newUserUsername = new TextField();
		newUserUsername.setPromptText("New User's Username");
		GridPane.setConstraints(newUserUsername, horizontalIndex++, verticalIndex);
		GridPane.setMargin(newUserUsername, new Insets(5, 5, 5, 5));
		myGridPane.getChildren().add(newUserUsername);

		newUserPassword = new PasswordField();
		newUserPassword.setPromptText("New User's Password");
		GridPane.setConstraints(newUserPassword, horizontalIndex++, verticalIndex);
		GridPane.setMargin(newUserPassword, new Insets(5, 5, 5, 5));
		myGridPane.getChildren().add(newUserPassword);

		Button addUserButton = new Button("Add User");
		addUserButton.setOnAction(e ->
		{
			addUser();
		});
		GridPane.setConstraints(addUserButton, horizontalIndex++, verticalIndex);
		GridPane.setMargin(addUserButton, new Insets(5, 5, 5, 5));
		myGridPane.getChildren().add(addUserButton);

		return myGridPane;
	}

	@Override
	protected GridPane createRightPane() {return null;}

	@Override
	protected VBox createBottomPane() {return null;}

}
