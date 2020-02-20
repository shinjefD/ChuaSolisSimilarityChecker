import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class programSimilarityCheckerTest {
    private static programSimilarityChecker checker;

    static {
        try {
            checker = new programSimilarityChecker("./Source Code");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void compare() {
        ArrayList<String> lines1 = new ArrayList<>();
        ArrayList<String> lines2 = new ArrayList<>();
        lines1.add("AAAAA");
        lines2.add("VVVVV");
        lines1.add("VVVVV");
        lines2.add("emilio aguinaldo");
        assert(0 <= checker.compare(lines1, lines2) && checker.compare(lines1, lines2) <= 100);
    }

    @org.junit.Test
    public void crossCompare() {
    }
}