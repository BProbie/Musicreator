package com.probie.musicreator.Config;

import lombok.Data;
import java.io.File;
import com.probie.easydb.EasyDB.EasyDB;
import com.probie.easydb.Database.Local.LocalRemoteDB;
import com.probie.musicreator.Musicreator.Musicreator;
import com.probie.musicreator.Config.Interface.IRenewConfig;

@Data
public class RenewConfig implements IRenewConfig {

    private volatile static RenewConfig INSTANCE;

    public synchronized static RenewConfig getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new RenewConfig();
        }
        if (INSTANCE.getLocalRemoteDB() == null) {
            INSTANCE.setLocalRemoteDB(EasyDB.getInstance().getLocalDatabaseFactory().buildLocalRemoteDB(Musicreator.getINSTANCE().getRenewConfigUri().get()));
            INSTANCE.getLocalRemoteDB().setFullFilePath(Musicreator.getINSTANCE().getConfigFilePath().get() + File.separator + Musicreator.getINSTANCE().getRenewConfigFileName().get());
            INSTANCE.getLocalRemoteDB().setIsAutoCommit(false);
            INSTANCE.getLocalRemoteDB().downloadDatabase(true);
            INSTANCE.getLocalRemoteDB().connect();
        }
        return INSTANCE;
    }

    private volatile LocalRemoteDB localRemoteDB;

}