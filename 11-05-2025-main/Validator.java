import java.text.SimpleDateFormat;
import java.util.Date;

public class Validator {
    public static boolean isFutureDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d = sdf.parse(dateStr);
            return d.after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidPhone(String phone) {
        return phone.matches("\\d{10}");
    }
}
