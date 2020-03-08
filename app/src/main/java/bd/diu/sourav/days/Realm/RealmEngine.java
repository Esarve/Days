package bd.diu.sourav.days.Realm;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class RealmEngine {
    private Realm realm;

    RealmEngine() {
        realm = Realm.getInstance(getConfiguration());
    }

    private RealmConfiguration getConfiguration() {
        return new RealmConfiguration.Builder()
                .name("days.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
    }

    public void addData(final String body, final long datetime, final String date, final String time) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Number maxID = realm.where(DaysModel.class).max(DaysModel.PROPERTY_ID);
                int nextID = (maxID == null) ? 1 : maxID.intValue() + 1;
                realm.beginTransaction();
                DaysModel daysModel = realm.createObject(DaysModel.class);
                daysModel.setId(nextID);
                daysModel.setBody(body);
                daysModel.setDatetime(datetime);
                daysModel.setDate(date);
                daysModel.setTime(time);
                realm.commitTransaction();
            }
        });
    }

    public RealmResults<DaysModel> getAllData() {
        return realm.where(DaysModel.class).findAll();
    }

    public RealmResults<DaysModel> getSpecific(int id) {
        return realm.where(DaysModel.class).equalTo("id", id).findAll();
    }

    public void deleteData(int id) {
        getSpecific(id).deleteAllFromRealm();
    }
}
