import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class controller {
    @FXML
    public GridPane correlationMatrix;

    @FXML
   public void initialize() throws IOException {
       this.printTable();
    }

    public void printTable() throws IOException {
        int row = 0;
        programSimilarityChecker test =  new programSimilarityChecker("./Source Code");
        ArrayList<Text> names = new ArrayList<>();
        names.add(new Text(""));
        for (Map.Entry<String, ArrayList<Double>> entry : test.crossCompare().entrySet()) {
            String name = entry.getKey();
            name = name.substring(0, name.lastIndexOf('.'));
            names.add(new Text(name));
        }
        Text[] nameList = new Text[names.size()];
        names.toArray(nameList);
        correlationMatrix.addRow(row++, nameList);
        for (Map.Entry<String, ArrayList<Double>> entry1 : test.crossCompare().entrySet()) {
            String name = entry1.getKey();
            name = name.substring(0, name.lastIndexOf('.'));
            ArrayList<Double> scores = entry1.getValue();
            ArrayList<Text> textScore = new ArrayList<>();
            textScore.add(new Text(name));
            scores.forEach((value) -> textScore.add(new Text(String.format("%.2f", value))));
            Text[] texts = new Text[textScore.size()];
            textScore.toArray(texts);
            correlationMatrix.addRow(row++, texts);
        }
    }
}
