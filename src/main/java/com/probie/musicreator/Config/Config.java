package com.probie.musicreator.Config;

import lombok.Data;
import com.probie.musicreator.Config.Interface.IConfig;

@Data
public class Config implements IConfig {

    private volatile static Config INSTANCE;

    public synchronized static Config getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Config();
        }
        return INSTANCE;
    }

}