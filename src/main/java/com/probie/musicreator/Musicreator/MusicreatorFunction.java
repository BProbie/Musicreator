package com.probie.musicreator.Musicreator;

import lombok.Data;
import javafx.scene.Node;
import javax.sound.midi.*;
import javafx.application.Platform;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import com.probie.musicreator.Config.RenewConfig;
import com.probie.musicreator.System.ComputerSystem;
import com.probie.musicreator.Musicreator.Interface.IMusicreatorFunction;

import java.io.File;

@Data
public class MusicreatorFunction implements IMusicreatorFunction {

    private volatile static MusicreatorFunction INSTANCE;

    public synchronized static MusicreatorFunction getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new MusicreatorFunction();
        }
        return INSTANCE;
    }

    private volatile static Musicreator musicreator = Musicreator.getINSTANCE();
    private volatile static MusicreatorData musicreatorData = MusicreatorData.getINSTANCE();
    private volatile static MusicreatorFunction musicreatorFunction = MusicreatorFunction.getINSTANCE();
    private volatile static MusicreatorElement musicreatorElement = MusicreatorElement.getINSTANCE();
    private volatile static MusicreatorStyle musicreatorStyle = MusicreatorStyle.getINSTANCE();
    private volatile static MusicreatorEvent musicreatorEvent = MusicreatorEvent.getINSTANCE();

    @Override
    public void waitASecond() {
        try {
            Thread.sleep(musicreatorData.getDelay().get());
        } catch (InterruptedException interruptedException) {
            throw new RuntimeException(interruptedException);
        }
    }

    @Override
    public void waitMoreSecond() {
        try {
            Thread.sleep(musicreatorData.getDelay().get() * 100L);
        } catch (InterruptedException interruptedException) {
            throw new RuntimeException(interruptedException);
        }
    }

    @Override
    public boolean checkRenew() {
        return Double.parseDouble(String.valueOf(RenewConfig.getINSTANCE().getLocalRemoteDB().get("VERSION", Musicreator.getINSTANCE().getVERSION()))) > Double.parseDouble(Musicreator.getINSTANCE().getVERSION());
    }

    @Override
    public boolean downloadRenew() {
        String command = "cmd /c" + " "
                + musicreator.getJavaFilePath().get() + File.separator + "bin" + File.separator + "java" + " " + "-jar" + " "
                + musicreator.getRenewLocalFilePath().get() + File.separator + musicreator.getRenewLocalFileName().get() + " "
                + musicreator.getMusicreatorUri().get() + " "
                + musicreator.getMusicreatorLocalFilePath().get() + File.separator + musicreator.getMusicreatorLocalFileName().get() + " "
                + musicreator.getAutoOpenRenew().get();
        return ComputerSystem.getINSTANCE().runCommand(command) == 0;
    }

    @Override
    public void chooseMenu(Node node) {
        if (!musicreatorElement.getRootPaneCenterStageBarVBox().getChildren().contains(node)) {
            musicreatorElement.getRootPaneCenterStageBarVBox().getChildren().clear();
            musicreatorElement.getRootPaneCenterStageBarVBox().getChildren().add(node);
        } else {
            if (node == musicreatorElement.getMusicreatorVBox()) {
                clearMusicreator();
            } else if (node == musicreatorElement.getParamVBox()) {
                clearParam();
            } else if (node == musicreatorElement.getSettingVBox()) {
                clearSetting();
            } else if (node == musicreatorElement.getRenewVBox()) {
                clearRenew();
            }
        }
    }

    @Override
    public void clearMusicreator() {
        musicreatorStyle.createMusicreatorStyle();
    }

    @Override
    public void clearParam() {
        musicreatorStyle.createParamStyle();
    }

    @Override
    public void clearSetting() {
        musicreatorStyle.createSettingStyle();
    }

    @Override
    public void clearRenew() {
        musicreatorStyle.createRenewStyle();
    }

    @Override
    public void scrollToBottom(ScrollPane scrollPane) {
        musicreator.getMusicreatorPool().submit(() -> {
            musicreatorFunction.waitASecond();
            ScrollBar verticalScrollBar = (ScrollBar) scrollPane.lookup(".scroll-bar:vertical");
            if (verticalScrollBar != null) {
                Platform.runLater(() -> verticalScrollBar.setValue(verticalScrollBar.getMax()));
            } else {
                Platform.runLater(() -> scrollPane.setVvalue(1.0));
            }
        });
    }

}