package com.probie.musicreator.Config;

import lombok.Data;
import java.io.File;
import com.probie.easydb.EasyDB.EasyDB;
import com.probie.easydb.Database.Local.LocalDB;
import com.probie.musicreator.Musicreator.Musicreator;
import com.probie.musicreator.Config.Interface.ISettingConfig;

@Data
public class SettingConfig extends Config implements ISettingConfig {

    private volatile static SettingConfig INSTANCE;

    public synchronized static SettingConfig getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new SettingConfig();
        }
        if (INSTANCE.getLocalDB() == null) {
            INSTANCE.setLocalDB(EasyDB.getInstance().getLocalDatabaseFactory().buildLocalDB());
            INSTANCE.getLocalDB().setFullFilePath(Musicreator.getINSTANCE().getConfigFilePath().get() + File.separator + Musicreator.getINSTANCE().getSettingConfigFileName().get());
            INSTANCE.getLocalDB().setIsAutoCommit(false);
            INSTANCE.getLocalDB().connect();
        }
        return INSTANCE;
    }

    private volatile LocalDB localDB;

}