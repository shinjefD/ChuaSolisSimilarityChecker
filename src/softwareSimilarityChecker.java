import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class softwareSimilarityChecker {
    static double compare(ArrayList<String> project1, ArrayList<String> project2){
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

    static HashMap<String, ArrayList<Double>> crossCompare(HashMap<String, ArrayList<String>> projects){
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

    public static void main(String[] args) throws IOException {

        String currentLine="";
        String path= "./Source Code";
        HashMap<String, ArrayList<String>> storage = new HashMap<>();
        String fileName;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();


        for (File file : listOfFiles) {
            if (file.isFile()) {
                fileName = file.getName();
                if (fileName.endsWith(".txt") || fileName.endsWith(".TXT") || fileName.endsWith(".txt") || fileName.endsWith(".cpp") || fileName.endsWith(".txt") || fileName.endsWith(".java")) {
                    //File textFile = new File(files);
                    ArrayList<String> tmpStorage = new ArrayList<>();
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        while ((currentLine = br.readLine()) != null) {
                            currentLine = currentLine.replaceAll("\\s+", "");
                            if (!tmpStorage.contains(currentLine)) {
                                tmpStorage.add(currentLine);
                            }
                        }
                        br.close();
                        storage.put(fileName, tmpStorage);
                    } catch (FileNotFoundException e) {
                        System.out.println(e.getMessage());
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }

        HashMap<String, ArrayList<Double>> table = crossCompare(storage);

        for (Map.Entry<String, ArrayList<Double>> entry : table.entrySet()){
            String name = entry.getKey();
            ArrayList<Double> scores = entry.getValue();
            System.out.printf("%15s\t", name);
            for (Double score : scores){
                System.out.printf("%.2f\t", score);
            }
            System.out.println();
        }
    }

}
