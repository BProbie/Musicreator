package com.probie.musicreator.Musicreator;

import javafx.application.Platform;
import javafx.scene.control.ScrollPane;
import lombok.Data;
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import com.probie.musicreator.Musicreator.Interface.IMusicreatorStyle;

@Data
public class MusicreatorStyle implements IMusicreatorStyle {

    private volatile static MusicreatorStyle INSTANCE;

    public synchronized static MusicreatorStyle getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new MusicreatorStyle();
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
    public void createStyle() {
        musicreatorStyle.createStageStyle();
        musicreatorStyle.createSceneStyle();
        musicreatorStyle.createStageBarStyle();
        musicreatorStyle.createRootPaneStyle();
        musicreatorStyle.createStageBarStyle();
        musicreatorStyle.createMenuBarStyle();
        musicreatorStyle.createMusicreatorStyle();
        musicreatorStyle.createParamStyle();
        musicreatorStyle.createSettingStyle();
        musicreatorStyle.createRenewStyle();
    }

    @Override
    public void createStageStyle() {
        musicreatorElement.getStage().setWidth(musicreatorData.getStageWidth().get());
        musicreatorElement.getStage().setHeight(musicreatorData.getStageHeight().get());
    }

    @Override
    public void createSceneStyle() {

    }

    @Override
    public void createRootPaneStyle() {

    }

    @Override
    public void createStageBarStyle() {
        musicreatorElement.getRootPaneCenterStageBarVBox().setAlignment(Pos.CENTER);
    }

    @Override
    public void createMenuBarStyle() {
        musicreatorElement.getRootPaneLeftMenuBarVBox().setSpacing(musicreatorData.getOffset().get());
        musicreatorElement.getRootPaneLeftMenuBarVBox().setAlignment(Pos.CENTER);

        musicreatorElement.getRootPaneLeftMenuBarMusicreatorButton().setText("创作");
        musicreatorElement.getRootPaneLeftMenuBarMusicreatorButton().setFont(new Font(musicreatorData.getFontSizeLarge().get()));
        musicreatorElement.getRootPaneLeftMenuBarParamButton().setText("参数");
        musicreatorElement.getRootPaneLeftMenuBarParamButton().setFont(new Font(musicreatorData.getFontSizeLarge().get()));
        musicreatorElement.getRootPaneLeftMenuBarSettingButton().setText("设置");
        musicreatorElement.getRootPaneLeftMenuBarSettingButton().setFont(new Font(musicreatorData.getFontSizeLarge().get()));
        musicreatorElement.getRootPaneLeftMenuBarRenewButton().setText("更新");
        musicreatorElement.getRootPaneLeftMenuBarRenewButton().setFont(new Font(musicreatorData.getFontSizeLarge().get()));
    }

    @Override
    public void createMusicreatorStyle() {
        musicreatorElement.getMusicreatorVBox().setSpacing(musicreatorData.getOffset().get());
        musicreatorElement.getMusicreatorVBox().setAlignment(Pos.CENTER);

        musicreatorElement.getMusicreatorChooseFileHBox().setSpacing(musicreatorData.getOffset().get());
        musicreatorElement.getMusicreatorChooseFileHBox().setAlignment(Pos.CENTER);

        musicreatorElement.getMusicreatorChooseFileChooseFileButton().setText("浏览");
        musicreatorElement.getMusicreatorChooseFileChooseFileButton().setFont(new Font(musicreatorData.getFontSizeLarge().get()));

        musicreatorElement.getMusicreatorChooseFileShowChosenFileLabel().setText(musicreator.getChosenFilePath().get());
        musicreatorElement.getMusicreatorChooseFileShowChosenFileLabel().setFont(new Font(musicreatorData.getFontSizeLarge().get()));

        musicreatorElement.getMusicreatorChooseFileFileChooser().getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ALL", "*.*"),
                new FileChooser.ExtensionFilter("MID", "*.mid"),
                new FileChooser.ExtensionFilter("MP3", "*.mp3")
        );

        musicreatorElement.getMusicreatorSpawnFileHBox().setSpacing(musicreatorData.getOffset().get());
        musicreatorElement.getMusicreatorSpawnFileHBox().setAlignment(Pos.CENTER);
        musicreatorElement.getMusicreatorSpawnFileHBox().setVisible(false);

        musicreatorElement.getMusicreatorSpawnFileChooseFileButton().setText("浏览");
        musicreatorElement.getMusicreatorSpawnFileChooseFileButton().setFont(new Font(musicreatorData.getFontSizeLarge().get()));

        musicreatorElement.getMusicreatorSpawnFileShowChosenFileTextField().setFont(new Font(musicreatorData.getFontSizeLarge().get()));

        musicreatorElement.getMusicreatorSpawnFileSpawnFileButton().setText("生成");
        musicreatorElement.getMusicreatorSpawnFileSpawnFileButton().setFont(new Font(musicreatorData.getFontSizeLarge().get()));

        musicreatorElement.getMusicreatorSpawnFileFileChooser().getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("MCFUNCTION", "*.mcfunction")
        );
    }

    @Override
    public void createParamStyle() {

    }

    @Override
    public void createSettingStyle() {

    }

    @Override
    public void createRenewStyle() {
        musicreatorElement.getRenewVBox().setSpacing(musicreatorData.getOffset().get());

        musicreatorElement.getRenewCheckRenewVBox().setSpacing(musicreatorData.getOffset().get());
        musicreatorElement.getRenewCheckRenewVBox().setAlignment(Pos.CENTER);

        musicreatorElement.getRenewCheckRenewButton().setText("检查更新");
        musicreatorElement.getRenewCheckRenewButton().setFont(new Font(musicreatorData.getFontSizeLarge().get()));

        musicreatorElement.getRenewDownloadRenewVBox().setSpacing(musicreatorData.getOffset().get());
        musicreatorElement.getRenewDownloadRenewVBox().setAlignment(Pos.CENTER);
        musicreatorElement.getRenewDownloadRenewVBox().setVisible(false);

        musicreatorElement.getRenewDownloadRenewScrollPane().maxWidthProperty().bind(musicreatorElement.getRenewDownloadRenewVBox().widthProperty().divide(2.0));
        musicreatorElement.getRenewDownloadRenewScrollPane().setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        musicreatorElement.getRenewDownloadRenewScrollPane().setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        musicreatorElement.getRenewDownloadRenewTextArea().prefWidthProperty().bind(musicreatorElement.getRenewDownloadRenewScrollPane().widthProperty());
        musicreatorElement.getRenewDownloadRenewTextArea().setStyle("-fx-text-alignment: center;");
        musicreatorElement.getRenewDownloadRenewTextArea().setWrapText(true);
        musicreatorElement.getRenewDownloadRenewTextArea().setEditable(true);
        musicreatorElement.getRenewDownloadRenewTextArea().clear();
        musicreatorElement.getRenewDownloadRenewTextArea().setFont(new Font(musicreatorData.getFontSizeLarge().get()));

        musicreatorElement.getRenewDownloadButton().setText("立即更新");
        musicreatorElement.getRenewDownloadButton().setFont(new Font(musicreatorData.getFontSizeLarge().get()));
    }

}