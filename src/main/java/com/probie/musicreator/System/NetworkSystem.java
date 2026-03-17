package com.probie.musicreator.System;

import com.probie.musicreator.System.Interface.INetworkSystem;

public class NetworkSystem implements INetworkSystem {

    private volatile static NetworkSystem INSTANCE;

    public synchronized static NetworkSystem getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new NetworkSystem();
        }
        return INSTANCE;
    }

}