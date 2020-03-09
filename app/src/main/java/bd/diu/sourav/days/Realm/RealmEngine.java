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
//                realm.beginTransaction();

                DaysModel daysModel = new DaysModel(nextID, body, date, time, datetime);
                //daysModel.setId(nextID);
//                daysModel.setBody(body);
//                daysModel.setDatetime(datetime);
//                daysModel.setDate(date);
//                daysModel.setTime(time);
                realm.insertOrUpdate(daysModel);
//                realm.commitTransaction();
            }
        });
    }

    public void addData(final DaysModel daysModel) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(daysModel);
            }
        });
    }

    public RealmResults<DaysModel> getAllData() {
        return realm.where(DaysModel.class).findAll();
    }

    public DaysModel getSpecific(int id) {
        return realm.copyFromRealm(realm.where(DaysModel.class).equalTo("id", id).findAll().last());
    }

    public void deleteData(final int id) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(DaysModel.class)
                        .equalTo("id", id)
                        .findAll()
                        .deleteAllFromRealm();
            }
        });
    }
}
