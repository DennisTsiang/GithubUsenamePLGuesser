package Output;

public class StdOutput implements View {

    @Override
    public void display(String buffer) {
        System.out.println(buffer);
    }
}
