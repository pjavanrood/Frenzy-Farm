import controller.GameProcessor;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import controller.userprocessor.User;
import controller.userprocessor.UserFile;

import java.io.IOException;

public class LoginMenu {

    public static Stage window;
    public static Scene loginScene;
    public TextField usernameinput;
    public PasswordField passwordinput;
    public ImageView loginbutton;
    public ImageView signupbutton;

    public void handleLogin() throws IOException {
        UserFile.readUsersFromFile();
        User user = new User(usernameinput.getText(), passwordinput.getText());
        if (user.findUser(user) == 0) {
            OutputProcessor.invalidUsername();
        } else if (user.findUser(user) == 2) {
            GameProcessor.user = GameProcessor.giveUser(usernameinput.getText(), passwordinput.getText());
            OutputProcessor.loginSuccessful();
            setupLevelPageScene();
            System.out.println(GameProcessor.user.getName());
            System.out.println(GameProcessor.user.getLevelpassed());
        }
    }

    public void handleSignup() throws IOException {
        UserFile.readUsersFromFile();
        User user = new User(usernameinput.getText(), passwordinput.getText());
        if (user.findUser(user) != 0) {
            OutputProcessor.repititiousUsername();
        } else {
            GameProcessor.user = GameProcessor.giveUser(usernameinput.getText(), passwordinput.getText());
            GameProcessor.users.add(user);
            UserFile.writeUsersInFile(GameProcessor.users);
            OutputProcessor.signupSuccessful();
            setupLevelPageScene();
        }
    }

    public void setupLevelPageScene() throws IOException {
        Parent levelsPage = FXMLLoader.load(getClass().getResource("LevelsPage.fxml"));
        Main.levelsPageScene = new Scene(levelsPage, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        Main.window.setScene(Main.levelsPageScene);
    }

    private void closeRegistration() {
        ExitBox.display();
    }

    private void setupExitScene() throws IOException{
        Parent exitBox = FXMLLoader.load(getClass().getResource("exitBox.fxml"));
        Main.exitBoxScene = new Scene(exitBox, 377, 146);
    }

    private void setupAlertBoxScene() throws IOException {
        Parent alertBox = FXMLLoader.load(getClass().getResource("alertBox.fxml"));
        Main.alertBoxScene = new Scene(alertBox, 478, 226);
    }

    public void glowButton(javafx.scene.input.MouseEvent mouseEvent) {
        Glow glow = new Glow();
        glow.setLevel(0.5);
        ((ImageView) mouseEvent.getSource()).setEffect(glow);

    }

    public void unGlowButton(javafx.scene.input.MouseEvent mouseEvent) {
        Glow glow = new Glow();
        glow.setLevel(0);
        ((ImageView) mouseEvent.getSource()).setEffect(glow);
    }
}
