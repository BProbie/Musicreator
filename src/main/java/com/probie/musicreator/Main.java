package com.probie.musicreator;

import com.probie.musicreator.Config.SettingConfig;

public class Main {

    public static void main(String[] args) {
        com.probie.musicreator.Musicreator.Musicreator.getINSTANCE().launch(args);
        SettingConfig.getINSTANCE();
    }

}