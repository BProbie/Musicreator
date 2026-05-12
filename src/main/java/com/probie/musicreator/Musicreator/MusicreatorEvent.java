package com.probie.musicreator.Musicreator;

import java.awt.*;
import lombok.Data;
import java.io.File;
import java.net.URI;
import java.io.IOException;
import java.nio.file.Files;
import javafx.application.Platform;
import com.probie.musicreator.Config.RenewConfig;
import com.probie.musicreator.System.AudioSystem;
import com.probie.musicreator.System.NetworkSystem;
import com.probie.musicreator.Musicreator.Interface.IMusicreatorEvent;

@Data
public class MusicreatorEvent implements IMusicreatorEvent {

    private volatile static MusicreatorEvent INSTANCE;

    public synchronized static MusicreatorEvent getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new MusicreatorEvent();
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
    public void createEvent() {
        musicreatorEvent.createStageEvent();
        musicreatorEvent.createSceneEvent();
        musicreatorEvent.createRootPaneEvent();
        musicreatorEvent.createStageBarEvent();
        musicreatorEvent.createMenuBarEvent();
        musicreatorEvent.createMusicreatorEvent();
        musicreatorEvent.createParamEvent();
        musicreatorEvent.createSettingEvent();
        musicreatorEvent.createRenewEvent();
    }

    @Override
    public void createStageEvent() {

    }

    @Override
    public void createSceneEvent() {

    }

    @Override
    public void createRootPaneEvent() {

    }

    @Override
    public void createStageBarEvent() {

    }

    @Override
    public void createMenuBarEvent() {
        musicreatorElement.getRootPaneLeftMenuBarMusicreatorButton().setOnAction(actionEvent -> musicreatorFunction.chooseMenu(musicreatorElement.getMusicreatorVBox()));
        musicreatorElement.getRootPaneLeftMenuBarParamButton().setOnAction(actionEvent -> musicreatorFunction.chooseMenu(musicreatorElement.getParamVBox()));
        musicreatorElement.getRootPaneLeftMenuBarSettingButton().setOnAction(actionEvent -> musicreatorFunction.chooseMenu(musicreatorElement.getSettingVBox()));
        musicreatorElement.getRootPaneLeftMenuBarRenewButton().setOnAction(actionEvent -> musicreatorFunction.chooseMenu(musicreatorElement.getRenewVBox()));
    }

    @Override
    public void createMusicreatorEvent() {
        musicreatorElement.getMusicreatorChooseFileChooseFileButton().setOnAction(actionEvent -> {
            musicreatorElement.getMusicreatorChooseFileFileChooser().setInitialDirectory(new File(musicreatorElement.getMusicreatorChooseFileShowChosenFileLabel().getText()).isDirectory() ? new File(musicreatorElement.getMusicreatorChooseFileShowChosenFileLabel().getText()).getAbsoluteFile() : new File(musicreatorElement.getMusicreatorChooseFileShowChosenFileLabel().getText()).getParentFile().getAbsoluteFile());
            File file = musicreatorElement.getMusicreatorChooseFileFileChooser().showOpenDialog(musicreatorElement.getStage());
            if (file != null) {
                musicreatorElement.getMusicreatorChooseFileShowChosenFileLabel().setText(file.getAbsolutePath());
                musicreator.setChosenFilePath(() -> file.getParentFile().getAbsolutePath());
                if (file.getName().toLowerCase().endsWith("mid") || file.getName().toLowerCase().endsWith("mp3")) {
                    musicreatorElement.getMusicreatorSpawnFileHBox().setVisible(true);
                    musicreatorData.setMusicName(() -> file.getName().contains(".") ? file.getName().substring(0, file.getName().lastIndexOf(".")) : file.getName());
                    musicreatorElement.getMusicreatorSpawnFileShowChosenFileTextField().setText(musicreator.getSpawnFilePath().get() + File.separator + musicreatorData.getMusicName().get() + ".mcfunction");
                    return;
                }
            }
            musicreatorElement.getMusicreatorSpawnFileHBox().setVisible(false);
        });

        musicreatorElement.getMusicreatorSpawnFileChooseFileButton().setOnAction(actionEvent -> {
            musicreatorElement.getMusicreatorSpawnFileFileChooser().setInitialDirectory(new File(musicreatorElement.getMusicreatorSpawnFileShowChosenFileTextField().getText()).isDirectory() ? new File(musicreatorElement.getMusicreatorSpawnFileShowChosenFileTextField().getText()).getAbsoluteFile() : new File(musicreatorElement.getMusicreatorSpawnFileShowChosenFileTextField().getText()).getParentFile().getAbsoluteFile());
            musicreatorElement.getMusicreatorSpawnFileFileChooser().setInitialFileName(new File(musicreatorElement.getMusicreatorSpawnFileShowChosenFileTextField().getText()).isDirectory() ? musicreatorData.getMusicName().get() : new File(musicreatorElement.getMusicreatorSpawnFileShowChosenFileTextField().getText()).getName().contains(".") ? new File(musicreatorElement.getMusicreatorSpawnFileShowChosenFileTextField().getText()).getName().substring(0, new File(musicreatorElement.getMusicreatorSpawnFileShowChosenFileTextField().getText()).getName().lastIndexOf(".")) : new File(musicreatorElement.getMusicreatorSpawnFileShowChosenFileTextField().getText()).getName());
            File file = musicreatorElement.getMusicreatorSpawnFileFileChooser().showSaveDialog(musicreatorElement.getStage());
            if (file != null) {
                musicreatorElement.getMusicreatorSpawnFileShowChosenFileTextField().setText(file.getAbsolutePath());
                musicreator.setSpawnFilePath(() -> file.getParentFile().getAbsolutePath());
                musicreatorData.setMusicName(() -> file.getName().contains(".") ? file.getName().substring(0, file.getName().lastIndexOf(".")) : file.getName());
            }
        });

        musicreatorElement.getMusicreatorSpawnFileSpawnFileButton().setOnAction(actionEvent -> {
            File spawnFile = new File(musicreatorElement.getMusicreatorSpawnFileShowChosenFileTextField().getText());
            if (!spawnFile.isDirectory()) {
                musicreator.setSpawnFilePath(() -> spawnFile.getParentFile().getAbsolutePath());
                musicreatorData.setMusicName(() -> spawnFile.getName().contains(".") ? spawnFile.getName().substring(0, spawnFile.getName().lastIndexOf(".")) : spawnFile.getName());
                String format = musicreatorElement.getMusicreatorChooseFileShowChosenFileLabel().getText().substring(musicreatorElement.getMusicreatorChooseFileShowChosenFileLabel().getText().lastIndexOf(".") + 1);
                switch (format) {
                    case "mid": {
                        String minecraftFunctionCommand = AudioSystem.getINSTANCE().turnMidiToMinecraftFunctionCommand(musicreatorElement.getMusicreatorChooseFileShowChosenFileLabel().getText());
                        try {
                            Files.writeString(spawnFile.toPath(), minecraftFunctionCommand);
                        } catch (IOException ioException) {
                            throw new RuntimeException(ioException);
                        }

                        String temp = musicreatorElement.getMusicreatorSpawnFileSpawnFileButton().getText();
                        musicreatorElement.getMusicreatorSpawnFileSpawnFileButton().setText("成功");
                        musicreator.getMusicreatorPool().submit(() -> {
                            musicreatorElement.getMusicreatorSpawnFileSpawnFileButton().setDisable(true);
                            musicreatorFunction.waitADelay(100);
                            Platform.runLater(() -> musicreatorElement.getMusicreatorSpawnFileSpawnFileButton().setText(temp));
                            musicreatorElement.getMusicreatorSpawnFileSpawnFileButton().setDisable(false);
                        });
                        break;
                    }
                    case "mp3": {

//                        String minecraftFunctionCommand = AudioSystem.getINSTANCE().turnMp3MinecraftFunctionCommand(musicreatorElement.getMusicreatorChooseFileShowChosenFileLabel().getText());
//                        try {
//                            Files.writeString(spawnFile.toPath(), minecraftFunctionCommand);
//                        } catch (IOException ioException) {
//                            throw new RuntimeException(ioException);
//                        }
//                        break;

                        String temp = musicreatorElement.getMusicreatorSpawnFileSpawnFileButton().getText();
                        musicreatorElement.getMusicreatorSpawnFileSpawnFileButton().setText("暂时仅支持.mid格式");
                        musicreator.getMusicreatorPool().submit(() -> {
                            musicreatorElement.getMusicreatorSpawnFileSpawnFileButton().setDisable(true);
                            musicreatorFunction.waitADelay(100);
                            Platform.runLater(() -> musicreatorElement.getMusicreatorSpawnFileSpawnFileButton().setText(temp));
                            musicreatorElement.getMusicreatorSpawnFileSpawnFileButton().setDisable(false);
                            try {
                                Desktop.getDesktop().browse(URI.create("https://audio-convert.com/cn/mp3-converter/mp3-to-midi"));
                            } catch (IOException ioException) {
                                throw new RuntimeException(ioException);
                            }
                        });
                        break;

                    }
                }
            } else {
                musicreatorElement.getMusicreatorSpawnFileShowChosenFileTextField().setText(musicreatorElement.getMusicreatorSpawnFileShowChosenFileTextField().getText() + (musicreatorElement.getMusicreatorSpawnFileShowChosenFileTextField().getText().endsWith(File.separator) ? "" : File.separator) + musicreatorData.getMusicName().get() + ".mcfunction");
                musicreator.setSpawnFilePath(spawnFile::getAbsolutePath);
            }
        });
    }

    @Override
    public void createParamEvent() {

    }

    @Override
    public void createSettingEvent() {

    }

    @Override
    public void createRenewEvent() {
        musicreatorElement.getRenewCheckRenewButton().setOnAction(actionEvent -> {
            musicreatorElement.getRenewCheckRenewButton().setDisable(true);
            musicreatorElement.getRenewShowRenewHBox().setVisible(true);
            musicreatorElement.getRenewShowRenewTextArea().setText("正在检查更新...");
            musicreator.getMusicreatorPool().submit(() -> {
                try {
                    if (NetworkSystem.getINSTANCE().getHasNetwork()) {
                        if (musicreatorFunction.checkRenew()) {
                            musicreatorElement.getRenewDownloadRenewHBox().setVisible(true);
                            Platform.runLater(() -> {
                                musicreatorElement.getRenewShowRenewTextArea().setText("发现更新版本！");
                                musicreatorElement.getRenewShowRenewTextArea().setText(musicreatorElement.getRenewShowRenewTextArea().getText() + "\n" + RenewConfig.getINSTANCE().getLocalRemoteDB().get("RENEW", "未知更新内容"));
                            });
                        } else {
                            Platform.runLater(() -> musicreatorElement.getRenewShowRenewTextArea().setText("已是最新版本！"));
                        }
                    } else {
                        Platform.runLater(() -> musicreatorElement.getRenewShowRenewTextArea().setText("网络不给力哦？"));
                    }
                } catch (Exception ignored) {
                    Platform.runLater(() -> musicreatorElement.getRenewShowRenewTextArea().setText("不小心出错了？"));
                } finally {
                    musicreatorElement.getRenewCheckRenewButton().setDisable(false);
                }
            });
        });
        musicreatorElement.getRenewDownloadButton().setOnAction(actionEvent -> {
            musicreatorElement.getRenewCheckRenewButton().setDisable(true);
            musicreatorElement.getRenewShowRenewTextArea().setText("正在更新软件...");
            musicreator.getMusicreatorPool().submit(() -> {
                try {
                    if (NetworkSystem.getINSTANCE().getHasNetwork()) {
                        if (musicreatorFunction.downloadRenew()) {
                            Platform.runLater(() -> musicreatorElement.getRenewShowRenewTextArea().setText("版本更新完成！"));
                            if (musicreator.getAutoOpenRenew().get()) {
                                System.exit(0);
                            }
                        } else {
                            Platform.runLater(() -> musicreatorElement.getRenewShowRenewTextArea().setText("版本更新失败？"));
                        }
                    } else {
                        Platform.runLater(() -> musicreatorElement.getRenewShowRenewTextArea().setText("网络不给力哦？"));
                    }
                } catch (Exception ignored) {
                    Platform.runLater(() -> musicreatorElement.getRenewShowRenewTextArea().setText("不小心出错了？"));
                } finally {
                    musicreatorElement.getRenewCheckRenewButton().setDisable(false);
                }
            });
        });

        musicreatorElement.getRenewAutoRenewCheckGroup().selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            musicreator.setAutoCheckRenew(() -> newValue == musicreatorElement.getRenewAutoRenewCheckOnButton());
            if (newValue == musicreatorElement.getRenewAutoRenewCheckOffButton() && musicreatorElement.getRenewAutoRenewDownloadOnButton().isSelected()) {
                musicreatorElement.getRenewAutoRenewCheckOnButton().setSelected(true);
                musicreator.setAutoCheckRenew(() -> true);
            }
        });

        musicreatorElement.getRenewAutoRenewDownloadGroup().selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            musicreator.setAutoDownloadRenew(() -> newValue == musicreatorElement.getRenewAutoRenewDownloadOnButton());
            if (newValue == musicreatorElement.getRenewAutoRenewDownloadOnButton() && !musicreatorElement.getRenewAutoRenewCheckOnButton().isSelected()) {
                musicreatorElement.getRenewAutoRenewCheckOnButton().setSelected(true);
                musicreator.setAutoCheckRenew(() -> true);
            }
        });
    }

}