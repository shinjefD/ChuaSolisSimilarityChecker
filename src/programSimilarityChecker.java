import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class programSimilarityChecker {
    private String currentLine="";
    private String path= "./Source Code";
    private HashMap<String, ArrayList<String>> storage = new HashMap<>();
    private String fileName;
    File folder = new File(path);
    File[] listOfFiles = folder.listFiles();

    public programSimilarityChecker(String pathName) throws IOException {
        for (File file : listOfFiles) {
//            if(file.isDirectory()) {
//                for (File file1 : listOfFiles) {
//                    fileName = file1.getName();
//                    if (fileName.endsWith(".txt") || fileName.endsWith(".TXT") || fileName.endsWith(".txt") || fileName.endsWith(".cpp") || fileName.endsWith(".txt") || fileName.endsWith(".java")) {
//                        ArrayList<String> tmpStorage = new ArrayList<>();
//                        BufferedReader br = new BufferedReader(new FileReader(file));
//                        while ((currentLine = br.readLine()) != null) {
//                            currentLine = currentLine.replaceAll("\\s+", "");
//                            if (!tmpStorage.contains(currentLine)) {
//                                tmpStorage.add(currentLine);
//                            }
//                        }
//                        br.close();
//                        storage.put(fileName, tmpStorage);
//                    }
//                }
//            }
            if (file.isFile()) {
                fileName = file.getName();
                if (fileName.endsWith(".txt") || fileName.endsWith(".TXT") || fileName.endsWith(".txt") || fileName.endsWith(".cpp") || fileName.endsWith(".txt") || fileName.endsWith(".java")) {
                    ArrayList<String> tmpStorage = new ArrayList<>();
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    while ((currentLine = br.readLine()) != null) {
                        currentLine = currentLine.replaceAll("\\s+", "");
                        if (!tmpStorage.contains(currentLine)) {
                            tmpStorage.add(currentLine);
                        }
                    }
                    br.close();
                    storage.put(fileName, tmpStorage);
                }
            }
        }
    }
    HashMap<String, ArrayList<Double>> table = crossCompare(storage);

    public void printCorrelationMatrix() {
        for (Map.Entry<String, ArrayList<Double>> entry : table.entrySet()) {
            String name = entry.getKey();
            ArrayList<Double> scores = entry.getValue();
            System.out.printf("%15s\t", name);
            for (Double score : scores) {
                System.out.printf("%.2f\t", score);
            }
            System.out.println();
        }
    }
    public double compare(ArrayList<String> project1, ArrayList<String> project2){
        double countComparison = 0;
        for (String temp1 : project1) {
            for (String temp2 : project2) {
                if (temp1.equals(temp2)) {
                    countComparison++;
                }
            }
        }
        return (countComparison/Math.max(project1.size(), project2.size()) ) * 100;
    }

    public HashMap<String, ArrayList<Double>> crossCompare(){
        return crossCompare(storage);
    }

    private HashMap<String, ArrayList<Double>> crossCompare(HashMap<String, ArrayList<String>> projects){
        HashMap<String, ArrayList<Double>> comparisons = new HashMap<>();
        for (Map.Entry<String, ArrayList<String>> entry : projects.entrySet()){
            String name = entry.getKey();
            ArrayList<String> lines = entry.getValue();
            ArrayList<Double> results = new ArrayList<>();
            for (ArrayList<String> other : projects.values()){
                results.add(compare(lines, other));
            }
            comparisons.putIfAbsent(name, results);
        }
        return comparisons;
    }
}
