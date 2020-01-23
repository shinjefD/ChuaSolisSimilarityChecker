import java.io.*;
import java.util.ArrayList;

public class programChecker {
    public static void main(String[] args) throws IOException{

        String adder1;
        String adder2;
        double counter = 0;
        double counter1 = 0;
        double finalCounter = 0;
        double countComparison = 0;
        double similarity;
        BufferedReader File1 = new BufferedReader(new FileReader("C:\\Users\\Josef Lyle H Solis\\Downloads\\LBYCP2D\\Solis-Checker-Module0\\src\\SolisCode"));
        BufferedReader File2 = new BufferedReader(new FileReader("C:\\Users\\Josef Lyle H Solis\\Downloads\\LBYCP2D\\Solis-Checker-Module0\\src\\ChuaCode"));

        ArrayList<String> storage1 = new ArrayList<>();
        ArrayList<String> storage2 = new ArrayList<>();
        ArrayList<String> checker = new ArrayList<>();

        while ((adder1 = File1.readLine()) != null) {
            adder1 = adder1.replaceAll("\\s+", "");
            storage1.add(adder1);
            counter++;
        }
        /*while(storage1.contains("")) {
            storage1.remove("");
            counter--;
        }
        while(storage1.contains("}")) {
            storage1.remove("}");
            counter--;
        }*/
        while ((adder2 = File2.readLine()) != null) {
            adder2 = adder2.replaceAll("\\s+","");
            storage2.add(adder2);
            counter1++;
        }
        /*while(storage2.contains("")) {
            storage2.remove("");
            counter1--;
        }
        while(storage2.contains("}")) {
            storage2.remove("}");
            counter1--;
        }*/

        File1.close();
        File2.close();

        for (String temp1 : storage1) {
            for (String temp2 : storage2) {
                if (temp1.equals(temp2)) {
                    for(String temp3 : checker){
                        if (temp3.equals(temp1)) {
                            checker.remove(temp2);
                            countComparison--;
                            break;
                        }
                    }
                    checker.add(temp2);
                    countComparison++;
                }
            }
        }
        if(counter1 < counter) {
            finalCounter = counter;
        }
        else {
            finalCounter = counter1;
        }
        System.out.println(counter);
        System.out.println(counter1);
        System.out.println(finalCounter);
        similarity = (countComparison/finalCounter ) * 100;
        System.out.println("The similarity of the two files is "  + (String.format ("%,.2f", similarity)  + "%"));
    }
}

