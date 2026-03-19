package com.probie.musicreator.Musicreator;

import lombok.Data;
import java.io.File;
import java.io.Closeable;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.application.Application;
import com.probie.musicreator.Config.ParamConfig;
import com.probie.musicreator.Config.SettingConfig;
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
        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyRenewConfigUri(), Musicreator.getINSTANCE().getRenewConfigUri().get());
        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyMusicreatorUri(), Musicreator.getINSTANCE().getMusicreatorUri().get());

        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyMusicreatorFilePath(), Musicreator.getINSTANCE().getMusicreatorFilePath().get());
        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyJavaFilePath(), Musicreator.getINSTANCE().getJavaFilePath().get());
        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyLibFilePath(), Musicreator.getINSTANCE().getLibFilePath().get());
        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyConfigFilePath(), Musicreator.getINSTANCE().getConfigFilePath().get());

        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeySettingConfigFileName(), Musicreator.getINSTANCE().getSettingConfigFileName().get());
        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyParamConfigFileName(), Musicreator.getINSTANCE().getParamConfigFileName().get());
        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyRenewConfigFileName(), Musicreator.getINSTANCE().getRenewConfigFileName().get());
        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyChosenFilePath(), Musicreator.getINSTANCE().getChosenFilePath().get());
        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeySpawnFilePath(), Musicreator.getINSTANCE().getSpawnFilePath().get());

        ParamConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyBanChannel(), Musicreator.getINSTANCE().getBanChannel().get());

        if (!new File(Musicreator.getINSTANCE().getConfigFilePath().get()).exists()) {
            new File(Musicreator.getINSTANCE().getConfigFilePath().get()).mkdirs();
        }

        SettingConfig.getINSTANCE().getLocalDB().commit();
        ParamConfig.getINSTANCE().getLocalDB().commit();
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