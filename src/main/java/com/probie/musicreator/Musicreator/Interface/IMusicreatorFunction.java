package com.probie.musicreator.Musicreator.Interface;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public interface IMusicreatorFunction {

    /**
     * 菜单选择
     * */
    void chooseMenu(Node node);

    /**
     * 清理模块
     * */
    void clearMusicreator();
    void clearParam();
    void clearSetting();
    void clearRenew();

    /**
     * 更新滚轮到最底
     * */
    void scrollToBottom(ScrollPane scrollPane);

    /**
     * 等待一段时间
     * */
    void waitASecond();

}