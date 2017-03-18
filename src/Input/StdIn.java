package Input;

import Output.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StdIn implements Input{

    private View view;
    public StdIn (View view) {
        this.view = view;
    }

    @Override
    public String readLine() {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System
                .in));
        String line = null;

        do {
            try {
                line = bf.readLine();
            } catch (IOException io) {
                view.display("Error parsing line. Try again.");
            }
        } while (line == (null));
        return line;
    }
}
