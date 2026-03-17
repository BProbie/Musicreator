package com.probie.musicreator.System;

import com.probie.musicreator.System.Interface.IAudioSystem;

public class AudioSystem extends FileSystem implements IAudioSystem {

    private volatile static AudioSystem INSTANCE;

    public synchronized static AudioSystem getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new AudioSystem();
        }
        return INSTANCE;
    }

}