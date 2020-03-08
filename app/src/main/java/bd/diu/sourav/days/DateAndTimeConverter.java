package bd.diu.sourav.days;

public class DateAndTimeConverter {

    public int mergeDateAndTime(String date, String time) {
        try {
            return Integer.getInteger(date + time);
        } catch (NullPointerException e) {
            return 0;
        }

    }

    public String splitDate(long stamp) {
        String stampStr = String.valueOf(stamp);
        return stampStr.substring(0, 5);
    }

    public String splitTime(long stamp) {
        String stampStr = String.valueOf(stamp);
        return stampStr.substring(6);
    }

}
