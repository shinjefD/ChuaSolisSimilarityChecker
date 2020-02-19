import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class programSimilarityChecker {
    private String currentLine="";
    private HashMap<String, ArrayList<String>> storage = new HashMap<>();
    private String fileName;

    private ArrayList<String> toArrayList(File file) throws IOException {
        ArrayList<String> tmpStorage = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((currentLine = br.readLine()) != null) {
            currentLine = currentLine.replaceAll("\\s+", "");
            if (!tmpStorage.contains(currentLine)) {
                tmpStorage.add(currentLine);
            }
        }
        br.close();
        return tmpStorage;
    }
    public programSimilarityChecker(String pathName) throws IOException {
        File folder = new File(pathName);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            fileName = file.getName();
            if(file.isDirectory()) {
                try(Stream<Path> fileStream = Files.walk(file.toPath())){
                    ArrayList<String> majorLines = new ArrayList<>();
                    fileStream
                            .filter(Files::isRegularFile)
                            .forEach(path -> {
                                String innerFileName = path.toString();
                                        if (innerFileName.endsWith(".c") || innerFileName.endsWith(".TXT") || innerFileName.endsWith(".h") || innerFileName.endsWith(".cpp") || innerFileName.endsWith(".txt") || innerFileName.endsWith(".java")) {
                                            try {
//                                                System.out.println(path);
                                                ArrayList<String> lines = toArrayList(path.toFile());
//                                                System.out.println(lines);
                                                majorLines.addAll(lines);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });

                    storage.put(fileName, majorLines);
                }
            }
            else if (file.isFile()) {
                if (fileName.endsWith(".c") || fileName.endsWith(".TXT") || fileName.endsWith(".h") || fileName.endsWith(".cpp") || fileName.endsWith(".txt") || fileName.endsWith(".java")) {
                    storage.put(fileName, toArrayList(file));
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
        if(project1 == project2) return 100;
        List<String> listWithoutDuplicates = project1.stream().distinct().collect(Collectors.toList());
        List<String> listWithoutDuplicates1 = project2.stream().distinct().collect(Collectors.toList());
        System.out.println(project1);
        System.out.println(listWithoutDuplicates);
        for (String temp1 : listWithoutDuplicates) {
            for (String temp2 : listWithoutDuplicates1) {
                if (temp1.equals(temp2)) {
                    countComparison++;
                }
            }
        }
        return (countComparison/Math.max(project1.size(), project2.size())) * 100;
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
