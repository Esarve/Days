package bd.diu.sourav.days.Realm;

import io.realm.RealmConfiguration;

public class DaysRealm {
    public RealmConfiguration getConfiguration() {
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("days.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        return configuration;
    }
}
