import org.joda.time.DateTime;


public class Test {
    public static void main(String[] args) {
        String dateString = new DateTime().toString("MMddyyyyHHmmssSSS");
        System.out.println(dateString);

    }

}
