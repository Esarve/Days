package bd.diu.sourav.days.Realm;

import io.realm.RealmResults;

public class RealmRepository {
    private static RealmRepository instance;
    private static RealmEngine realmEngine;

    public RealmRepository getInstance() {
        if (instance == null) {
            instance = new RealmRepository();
            realmEngine = new RealmEngine();
        }
        return instance;
    }

    public void addData(final String body, final long timestamp, final String date, final String time) {
        realmEngine.addData(body, timestamp, date, time);
    }

    public RealmResults<DaysModel> getAllData() {
        return realmEngine.getAllData();
    }

    public RealmResults<DaysModel> getSpecific(int id) {
        return realmEngine.getSpecific(id);
    }

    public void deleteData(int id) {
        realmEngine.deleteData(id);
    }


}
