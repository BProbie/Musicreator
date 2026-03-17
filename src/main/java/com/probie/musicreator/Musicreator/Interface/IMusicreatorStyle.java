package com.probie.musicreator.Musicreator.Interface;

public interface IMusicreatorStyle {

    /**
     * 创建样式
     * */
    void createStyle();

    /**
     * 创建 Stage 样式
     * */
    void createStageStyle();

    /**
     * 创建 Scene 样式
     * */
    void createSceneStyle();

    /**
     * 创建 Pane 样式
     * */
    void createRootPaneStyle();

    /**
     * 创建 Bar 样式
     * */
    void createStageBarStyle();
    void createMenuBarStyle();

    /**
     * 创建 功能 元素
     * */
    void createMusicreatorStyle();
    void createParamStyle();
    void createSettingStyle();
    void createRenewStyle();

}