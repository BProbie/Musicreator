package com.probie.musicreator.System;

import com.probie.musicreator.System.Interface.IFileSystem;

public class FileSystem extends ComputerSystem implements IFileSystem {

    private volatile static FileSystem INSTANCE;

    public synchronized static FileSystem getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new FileSystem();
        }
        return INSTANCE;
    }

}