package com.probie.musicreator.Musicreator.Interface;

public interface IMusicreatorEvent {

    /**
     * 创建事件
     * */
    void createEvent();

    /**
     * 创建 Stage 事件
     * */
    void createStageEvent();

    /**
     * 创建 Scene 事件
     * */
    void createSceneEvent();

    /**
     * 创建 Pane 事件
     * */
    void createRootPaneEvent();

    /**
     * 创建 Bar 事件
     * */
    void createStageBarEvent();
    void createMenuBarEvent();

    /**
     * 创建 功能 事件
     */
    void createMusicreatorEvent();
    void createParamEvent();
    void createSettingEvent();
    void createRenewEvent();

}