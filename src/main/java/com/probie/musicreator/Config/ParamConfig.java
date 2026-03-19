package com.probie.musicreator.Config;

import lombok.Data;
import java.io.File;
import com.probie.easydb.EasyDB.EasyDB;
import com.probie.easydb.Database.Local.LocalDB;
import com.probie.musicreator.Musicreator.Musicreator;
import com.probie.musicreator.Config.Interface.IParamConfig;

@Data
public class ParamConfig implements IParamConfig {

    private volatile static ParamConfig INSTANCE;

    public synchronized static ParamConfig getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ParamConfig();
        }
        if (INSTANCE.getLocalDB() == null) {
            INSTANCE.setLocalDB(EasyDB.getInstance().getLocalDatabaseFactory().buildLocalDB());
            INSTANCE.getLocalDB().setFullFilePath(Musicreator.getINSTANCE().getConfigFilePath().get() + File.separator + Musicreator.getINSTANCE().getParamConfigFileName().get());
            INSTANCE.getLocalDB().setIsAutoCommit(false);
            INSTANCE.getLocalDB().connect();
        }
        return INSTANCE;
    }

    private volatile LocalDB localDB;

}