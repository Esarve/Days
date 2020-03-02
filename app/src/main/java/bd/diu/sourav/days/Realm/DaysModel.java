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
    public String datetime;


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

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
