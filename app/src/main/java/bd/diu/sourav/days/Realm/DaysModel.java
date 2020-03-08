package bd.diu.sourav.days.Realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class DaysModel extends RealmObject {
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_BODY = "body";
    public static final String PROPERTY_TIMEDATE = "timedate";

    @PrimaryKey
    public int id;
    @Required
    public String body;
    public String date;
    public String time;
    public long datetime;


    public static String getPropertyId() {
        return PROPERTY_ID;
    }

    public static String getPropertyBody() {
        return PROPERTY_BODY;
    }

    public static String getPropertyTimedate() {
        return PROPERTY_TIMEDATE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }
}
