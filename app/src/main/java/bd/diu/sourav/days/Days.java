package bd.diu.sourav.days;

public class Days {
    private String date, text, time;

    public Days(){

    }

    public Days(String date, String text, String time) {
        this.date = date;
        this.text = text;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
