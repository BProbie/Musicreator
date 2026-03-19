package com.probie.musicreator.Musicreator;


import lombok.Data;
import java.io.File;
import java.io.Closeable;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.application.Application;
import com.probie.musicreator.Config.RenewConfig;
import com.probie.musicreator.Config.ParamConfig;
import com.probie.musicreator.System.NetworkSystem;
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
        if (!new File(musicreator.getJavaFilePath().get()).exists()) new File(musicreator.getJavaFilePath().get()).mkdirs();
        if (!new File(musicreator.getLibFilePath().get()).exists()) new File(musicreator.getLibFilePath().get()).mkdirs();
    }

    @Override
    public void afterStart() {
        /// 窗口居中
        musicreatorElement.getStage().centerOnScreen();

        /// 检查下载更新
        if (musicreator.getAutoCheckRenew().get()) {
            musicreatorElement.getRenewCheckRenewButton().setDisable(true);
            musicreatorElement.getRenewShowRenewHBox().setVisible(true);
            musicreatorElement.getRenewShowRenewTextArea().setText("正在检查更新...");
            musicreator.getMusicreatorPool().submit(() -> {
                try {
                    if (NetworkSystem.getINSTANCE().getHasNetwork()) {
                        if (musicreatorFunction.checkRenew()) {
                            musicreatorElement.getRenewDownloadRenewHBox().setVisible(true);
                            musicreatorElement.getRenewShowRenewTextArea().setText("发现更新版本！");
                            musicreatorElement.getRenewShowRenewTextArea().setText(musicreatorElement.getRenewShowRenewTextArea().getText() + "\n" + RenewConfig.getINSTANCE().getLocalRemoteDB().get("RENEW", "未知更新内容"));

                            if (musicreator.getAutoDownloadRenew().get()) {
                                musicreatorElement.getRenewCheckRenewButton().setDisable(true);
                                musicreatorElement.getRenewShowRenewTextArea().setText("正在更新软件...");
                                musicreator.getMusicreatorPool().submit(() -> {
                                    try {
                                        if (NetworkSystem.getINSTANCE().getHasNetwork()) {
                                            if (musicreatorFunction.downloadRenew()) {
                                                musicreatorElement.getRenewShowRenewTextArea().setText("版本更新完成！");
                                                if (musicreator.getAutoOpenRenew().get()) {
                                                    System.exit(0);
                                                }
                                            } else {
                                                musicreatorElement.getRenewShowRenewTextArea().setText("版本更新失败？");
                                            }
                                        } else {
                                            musicreatorElement.getRenewShowRenewTextArea().setText("网络不给力哦？");
                                        }
                                    } catch (Exception ignored) {
                                        musicreatorElement.getRenewShowRenewTextArea().setText("不小心出错了？");
                                    } finally {
                                        musicreatorElement.getRenewCheckRenewButton().setDisable(false);
                                    }
                                });
                            }

                        } else {
                            musicreatorElement.getRenewShowRenewTextArea().setText("已是最新版本！");
                        }
                    } else {
                        musicreatorElement.getRenewShowRenewTextArea().setText("网络不给力哦？");
                    }
                } catch (Exception exception) {
                    musicreatorElement.getRenewShowRenewTextArea().setText("不小心出错了？");
                    musicreatorElement.getRenewShowRenewTextArea().setText(musicreatorElement.getRenewShowRenewTextArea().getText() + "\n" + exception.getMessage());
                } finally {
                    musicreatorElement.getRenewCheckRenewButton().setDisable(false);
                }
            });
        }
    }

    @Override
    public void beforeStop() {
        /// 保存配置
        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyMusicreatorFilePath(), Musicreator.getINSTANCE().getMusicreatorFilePath().get());
        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyJavaFilePath(), Musicreator.getINSTANCE().getJavaFilePath().get());
        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyLibFilePath(), Musicreator.getINSTANCE().getLibFilePath().get());
        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyConfigFilePath(), Musicreator.getINSTANCE().getConfigFilePath().get());

        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeySettingConfigFileName(), Musicreator.getINSTANCE().getSettingConfigFileName().get());
        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyParamConfigFileName(), Musicreator.getINSTANCE().getParamConfigFileName().get());
        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyRenewConfigFileName(), Musicreator.getINSTANCE().getRenewConfigFileName().get());
        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyChosenFilePath(), Musicreator.getINSTANCE().getChosenFilePath().get());
        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeySpawnFilePath(), Musicreator.getINSTANCE().getSpawnFilePath().get());

        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyRenewConfigUri(), Musicreator.getINSTANCE().getRenewConfigUri().get());
        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyMusicreatorUriWin(), Musicreator.getINSTANCE().getMusicreatorUriWin().get());
        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyMusicreatorUriLinux(), Musicreator.getINSTANCE().getMusicreatorUriLinux().get());
        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyMusicreatorUriMac(), Musicreator.getINSTANCE().getMusicreatorUriMac().get());
        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyMusicreatorUriAndroid(), Musicreator.getINSTANCE().getMusicreatorUriAndroid().get());
        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyMusicreatorUri(), Musicreator.getINSTANCE().getMusicreatorUri().get());

        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyRenewLocalFilePath(), Musicreator.getINSTANCE().getRenewLocalFilePath().get());
        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyRenewLocalFileName(), Musicreator.getINSTANCE().getRenewLocalFileName().get());
        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyMusicreatorLocalFilePath(), Musicreator.getINSTANCE().getMusicreatorLocalFilePath().get());
        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyMusicreatorLocalFileName(), Musicreator.getINSTANCE().getMusicreatorLocalFileName().get());

        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyAutoCheckRenew(), Musicreator.getINSTANCE().getAutoCheckRenew().get());
        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyAutoDownloadRenew(), Musicreator.getINSTANCE().getAutoDownloadRenew().get());
        SettingConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyAutoOpenRenew(), Musicreator.getINSTANCE().getAutoOpenRenew().get());

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