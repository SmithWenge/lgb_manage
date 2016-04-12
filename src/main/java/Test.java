import com.lgb.arc.utils.PasswordUtils;
import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.Date;


public class Test {
    public static void main(String[] args) {
        System.out.println(DateTime.now());
        System.out.println(getWeekOfDate(DateTime.now().toDate()));

    }

    public static int getWeekOfDate(Date dt) {
//        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;

        return w;
    }
}
