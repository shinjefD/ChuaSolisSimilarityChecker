import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class mainMenuController {
    @FXML
    public TextField pathDirectory;
    public Button directory;
    @FXML
    public void buttonClicked() throws IOException {
        String dir = pathDirectory.getText();

        Stage insertionSortStage = new Stage();
        Parent insertRoot = FXMLLoader.load(getClass().getResource("similarityChecker.fxml"));
        insertionSortStage.setTitle("Similarity Checker");
        insertionSortStage.setScene(new Scene(insertRoot, 1000, 500));
        insertionSortStage.show();
    }
}
