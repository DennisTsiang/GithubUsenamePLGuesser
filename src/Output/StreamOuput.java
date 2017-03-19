package Output;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class StreamOuput implements View{

    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Override
    public void display(String buffer) {
        try {
            outputStream.write((buffer + System.lineSeparator()).getBytes());
        } catch (IOException e) {
            System.err.println("Could not write to output stream");
        }
    }

    @Override
    public String toString() {
        return new String (outputStream.toByteArray());
    }
}
