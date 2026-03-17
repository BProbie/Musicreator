package com.probie.musicreator.Musicreator;

import com.probie.musicreator.Config.ConfigConfig;
import lombok.Data;
import java.io.Closeable;
import java.io.File;
import java.util.function.Supplier;

import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.application.Application;
import com.probie.musicreator.Musicreator.Interface.IMusicreatorApplication;

@Data
public class MusicreatorApplication extends Application implements IMusicreatorApplication, Closeable {

    private volatile static MusicreatorApplication INSTANCE;

    public synchronized static MusicreatorApplication getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new MusicreatorApplication();
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
    public void start(Stage stage) {
        beforeStart();
        stage.show();
        musicreatorElement.createElement(stage);
        musicreatorStyle.createStyle();
        musicreatorEvent.createEvent();
        afterStart();
    }

    @Override
    public void stop() {
        beforeStop();
        try {
            super.stop();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        afterStop();
    }

    @Override
    public void beforeStart() {

    }

    @Override
    public void afterStart() {
        /// 窗口居中
        musicreatorElement.getStage().centerOnScreen();
    }

    @Override
    public void beforeStop() {
        /// 保存配置
        ConfigConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyMusicreatorFilePath(), Musicreator.getINSTANCE().getMusicreatorFilePath().get());
        ConfigConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyJavaFilePath(), Musicreator.getINSTANCE().getJavaFilePath().get());
        ConfigConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyLibFilePath(), Musicreator.getINSTANCE().getLibFilePath().get());
        ConfigConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyConfigFilePath(), Musicreator.getINSTANCE().getConfigFilePath().get());

        ConfigConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyConfigConfigFileName(), Musicreator.getINSTANCE().getConfigConfigFileName().get());
        ConfigConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyChosenFilePath(), Musicreator.getINSTANCE().getChosenFilePath().get());

        ConfigConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyBanChannel(), Musicreator.getINSTANCE().getBanChannel().get());

        if (!new File(Musicreator.getINSTANCE().getConfigFilePath().get()).exists()) {
            new File(Musicreator.getINSTANCE().getConfigFilePath().get()).mkdirs();
        }
        ConfigConfig.getINSTANCE().getLocalDB().commit();
    }

    @Override
    public void afterStop() {
        close();
    }

    @Override
    public void close() {
        Musicreator.getINSTANCE().getMusicreatorPool().shutdownNow();
        musicreatorElement.getStage().close();
        Platform.exit();
        System.gc();
    }

}