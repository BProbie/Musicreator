package com.probie.musicreator.Musicreator.Interface;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public interface IMusicreatorFunction {

    /**
     * 检查更新
     * @return 是否存在更新版本
     * */
    boolean checkRenew();

    /**
     * 下载更新
     * @return 是否更新成功
     * */
    boolean downloadRenew();

    /**
     * 菜单选择
     * @param node 功能模块
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
     * @param scrollPane 滚轮实例
     * */
    void scrollToBottom(ScrollPane scrollPane);

    /**
     * 等待时间
     * */
    void waitADelay();
    void waitADelay(int times);

}