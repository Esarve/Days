package bd.diu.sourav.days.Realm;

import io.realm.RealmResults;

public class RealmRepository {
    private static RealmEngine realmEngine;

    public RealmRepository() {
        realmEngine = new RealmEngine();
    }

    public void addData(final String body, final long timestamp, final String date, final String time) {
        realmEngine.addData(body, timestamp, date, time);
    }

    public void addData(DaysModel daysModel) {
        realmEngine.addData(daysModel);
    }

    public RealmResults<DaysModel> getAllData() {
        return realmEngine.getAllData();
    }

    public DaysModel getSpecific(int id) {
        return realmEngine.getSpecific(id);
    }

    public void deleteData(int id) {
        realmEngine.deleteData(id);
    }


}
