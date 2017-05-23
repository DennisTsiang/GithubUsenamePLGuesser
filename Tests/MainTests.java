import Output.StdOutput;
import Output.View;
import org.junit.Test;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.Assert.assertEquals;

public class MainTests {

    @Test
    public void repeatReturnFalseForN () throws IOException {
        byte[] data = "n".getBytes();
        InputStream input = new ByteArrayInputStream(data);
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        View view = new StdOutput();
        boolean result = Main.askForRepeat(view, br);
        assertEquals(false, result);
    }

    @Test
    public void repeatReturnFalseForY () throws IOException {
        byte[] data = "y".getBytes();
        InputStream input = new ByteArrayInputStream(data);
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        View view = new StdOutput();
        boolean result = Main.askForRepeat(view, br);
        assertEquals(true, result);
    }

    @Test
    public void printMostUseLanguageOutputCheck() {
        StreamOuput view = new StreamOuput();
        Deque<LangCount> repos = new ArrayDeque<>();
        repos.offer(new LangCount("Java"));

        Main.printMostUsedLanguage(repos, view);
        assertEquals("Based on the git repositories this user has created " +
                "I think this user's favourite programming language(s) " +
                "are:"+System.lineSeparator()+"Java (1)"+System.lineSeparator(), view
                .toString());

    }
}
