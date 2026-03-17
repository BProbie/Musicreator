package com.probie.musicreator.Config;

import lombok.Data;
import java.io.File;
import com.probie.easydb.EasyDB.EasyDB;
import com.probie.easydb.Database.Local.LocalDB;
import com.probie.musicreator.Musicreator.Musicreator;

@Data
public class ConfigConfig extends Config implements IConfigConfig {

    private volatile static ConfigConfig INSTANCE;

    public synchronized static ConfigConfig getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ConfigConfig();
        }
        if (INSTANCE.getLocalDB() == null) {
            INSTANCE.setLocalDB(EasyDB.getInstance().getLocalDatabaseFactory().buildLocalDB());
            INSTANCE.getLocalDB().setFullFilePath(Musicreator.getINSTANCE().getConfigFilePath().get() + File.separator + Musicreator.getINSTANCE().getConfigConfigFileName().get());
            INSTANCE.getLocalDB().setIsAutoCommit(false);
            INSTANCE.getLocalDB().connect();
        }
        return INSTANCE;
    }

    private volatile LocalDB localDB;

}