package com.probie.musicreator.Musicreator.Interface;

public interface IMusicreatorApplication {

    /**
     * start 前的逻辑
     * */
    void beforeStart();

    /**
     * start 后的逻辑
     * */
    void afterStart();

    /**
     * stop 前的逻辑
     * */
    void beforeStop();

    /**
     * stop 后的逻辑
     * */
    void afterStop();

}