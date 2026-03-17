package com.probie.musicreator;

import com.probie.musicreator.Config.ConfigConfig;

public class Main {

    public static void main(String[] args) {
        com.probie.musicreator.Musicreator.Musicreator.getINSTANCE().launch(args);
        ConfigConfig.getINSTANCE();
    }

}