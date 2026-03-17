package com.probie.musicreator.System;

import com.probie.musicreator.System.Interface.IComputerSystem;

public class ComputerSystem extends NetworkSystem implements IComputerSystem {

    private volatile static ComputerSystem INSTANCE;

    public synchronized static ComputerSystem getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ComputerSystem();
        }
        return INSTANCE;
    }

}