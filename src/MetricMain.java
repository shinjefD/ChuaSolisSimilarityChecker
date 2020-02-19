
import java.io.IOException;
import java.util.Scanner;

public class MetricMain {
    public static void main(String[] args) throws IOException {
        Scanner user_input = new Scanner( System.in );
        System.out.print("Enter Directory Name: ");
        String pathDirectory = user_input.nextLine( );
        user_input.close();

        HalsteadMetrics hal = Metricator.getMetrics(pathDirectory);
        System.out.println("Vocabulary= "+ hal.getVocabulary());
        System.out.println("Program Length= "+ hal.getProglen());
        System.out.println("Volume= "+ hal.getVolume());
        System.out.println("Effort= "+ hal.getEffort());
        System.out.println("Number of delivered bugs= "+ hal.getTimeDelBugs());

    }
}
