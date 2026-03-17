package com.probie.musicreator.Musicreator.Interface;

import javafx.stage.Stage;

public interface IMusicreatorElement {

    /**
     * 创建元素
     * @param stage 舞台对象
     * */
    void createElement(Stage stage);

    /**
     * 创建 Stage 元素
     * */
    void createStageElement();

    /**
     * 创建 Scene 元素
     * */
    void createSceneElement();

    /**
     * 创建 pane 元素
     * */
    void createRootPaneElement();

    /**
     * 创建 Bar 元素
     * */
    void createStageBarElement();
    void createMenuBarElement();

    /**
     * 创建 模块 元素
     * */
    void createMusicreatorElement();
    void createParamElement();
    void createSettingElement();
    void createRenewElement();

}