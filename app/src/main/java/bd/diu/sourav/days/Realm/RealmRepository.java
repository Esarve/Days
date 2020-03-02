package bd.diu.sourav.days.Realm;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmRepository {
    private Realm realm;

    public RealmRepository() {
        realm = Realm.getInstance(getConfiguration());
    }

    private RealmConfiguration getConfiguration() {
        return new RealmConfiguration.Builder()
                .name("days.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
    }

    public void addData(final String body, final String datetime) {
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
                realm.commitTransaction();
            }
        });
    }

}
