package com.probie.musicreator.Musicreator;

import lombok.Data;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.scene.control.ScrollPane;
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
        musicreatorElement.getRootPaneLeftMenuBarVBox().setSpacing(musicreatorData.getSpacingSizeSmall().get());
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
        musicreatorElement.getMusicreatorVBox().setSpacing(musicreatorData.getSpacingSizeSmall().get());
        musicreatorElement.getMusicreatorVBox().setAlignment(Pos.CENTER);

        musicreatorElement.getMusicreatorChooseFileHBox().setSpacing(musicreatorData.getSpacingSizeSmall().get());
        musicreatorElement.getMusicreatorChooseFileHBox().setAlignment(Pos.CENTER);

        musicreatorElement.getMusicreatorChooseFileChooseFileButton().setText("选择音频文件");
        musicreatorElement.getMusicreatorChooseFileChooseFileButton().setFont(new Font(musicreatorData.getFontSizeLarge().get()));

        musicreatorElement.getMusicreatorChooseFileShowChosenFileLabel().setText(musicreator.getChosenFilePath().get());
        musicreatorElement.getMusicreatorChooseFileShowChosenFileLabel().setFont(new Font(musicreatorData.getFontSizeLarge().get()));

        musicreatorElement.getMusicreatorChooseFileFileChooser().getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ALL", "*.*"),
                new FileChooser.ExtensionFilter("MID", "*.mid"),
                new FileChooser.ExtensionFilter("MP3", "*.mp3")
        );

        musicreatorElement.getMusicreatorSpawnFileHBox().setSpacing(musicreatorData.getSpacingSizeSmall().get());
        musicreatorElement.getMusicreatorSpawnFileHBox().setAlignment(Pos.CENTER);
        musicreatorElement.getMusicreatorSpawnFileHBox().setVisible(false);

        musicreatorElement.getMusicreatorSpawnFileChooseFileButton().setText("选择函数文件");
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
        musicreatorElement.getParamVBox().setSpacing(musicreatorData.getSpacingSizeSmall().get());
        musicreatorElement.getParamVBox().setAlignment(Pos.CENTER);
    }

    @Override
    public void createSettingStyle() {
        musicreatorElement.getSettingVBox().setSpacing(musicreatorData.getSpacingSizeSmall().get());
        musicreatorElement.getSettingVBox().setAlignment(Pos.CENTER);
    }

    @Override
    public void createRenewStyle() {
        musicreatorElement.getRenewVBox().setSpacing(musicreatorData.getSpacingSizeSmall().get());

        musicreatorElement.getRenewCheckRenewHBox().setSpacing(musicreatorData.getSpacingSizeSmall().get());
        musicreatorElement.getRenewCheckRenewHBox().setAlignment(Pos.CENTER);

        musicreatorElement.getRenewCheckRenewButton().setText("检查更新");
        musicreatorElement.getRenewCheckRenewButton().setFont(new Font(musicreatorData.getFontSizeLarge().get()));

        musicreatorElement.getRenewShowRenewHBox().setSpacing(musicreatorData.getSpacingSizeSmall().get());
        musicreatorElement.getRenewShowRenewHBox().setAlignment(Pos.CENTER);
        musicreatorElement.getRenewShowRenewHBox().setVisible(false);

        musicreatorElement.getRenewShowRenewScrollPane().maxWidthProperty().bind(musicreatorElement.getRenewShowRenewHBox().widthProperty().divide(2.0));
        musicreatorElement.getRenewShowRenewScrollPane().setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        musicreatorElement.getRenewShowRenewScrollPane().setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        musicreatorElement.getRenewShowRenewTextArea().prefWidthProperty().bind(musicreatorElement.getRenewShowRenewScrollPane().widthProperty());
        musicreatorElement.getRenewShowRenewTextArea().setStyle("""
                        -fx-text-alignment: center !important;
                        """);
        musicreatorElement.getRenewShowRenewTextArea().setWrapText(true);
        musicreatorElement.getRenewShowRenewTextArea().setEditable(false);
        musicreatorElement.getRenewShowRenewTextArea().clear();
        musicreatorElement.getRenewShowRenewTextArea().setFont(new Font(musicreatorData.getFontSizeLarge().get()));

        musicreatorElement.getRenewDownloadRenewHBox().setSpacing(musicreatorData.getSpacingSizeSmall().get());
        musicreatorElement.getRenewDownloadRenewHBox().setAlignment(Pos.CENTER);
        musicreatorElement.getRenewDownloadRenewHBox().setVisible(false);

        musicreatorElement.getRenewDownloadButton().setText("立即更新");
        musicreatorElement.getRenewDownloadButton().setFont(new Font(musicreatorData.getFontSizeLarge().get()));

        musicreatorElement.getRenewAutoRenewHBox().setSpacing(musicreatorData.getSpacingSizeSmall().get());
        musicreatorElement.getRenewAutoRenewHBox().setAlignment(Pos.CENTER);

        musicreatorElement.getRenewAutoRenewCheckHBox().setSpacing(musicreatorData.getSpacingSizeSmall().get());
        musicreatorElement.getRenewAutoRenewCheckHBox().setAlignment(Pos.CENTER);

        musicreatorElement.getRenewAutoRenewCheckLabel().setText("自动检查更新");
        musicreatorElement.getRenewAutoRenewCheckLabel().setFont(new Font(musicreatorData.getFontSizeLarge().get()));

        musicreatorElement.getRenewAutoRenewCheckOnButton().setText("开");
        musicreatorElement.getRenewAutoRenewCheckOnButton().setFont(new Font(musicreatorData.getFontSizeLarge().get()));
        musicreatorElement.getRenewAutoRenewCheckOnButton().setToggleGroup(musicreatorElement.getRenewAutoRenewCheckGroup());

        musicreatorElement.getRenewAutoRenewCheckOffButton().setText("关");
        musicreatorElement.getRenewAutoRenewCheckOffButton().setFont(new Font(musicreatorData.getFontSizeLarge().get()));
        musicreatorElement.getRenewAutoRenewCheckOffButton().setToggleGroup(musicreatorElement.getRenewAutoRenewCheckGroup());

        if (musicreator.getAutoCheckRenew().get()) {
            musicreatorElement.getRenewAutoRenewCheckOnButton().setSelected(true);
        } else {
            musicreatorElement.getRenewAutoRenewCheckOffButton().setSelected(true);
        }

        musicreatorElement.getRenewAutoRenewDownloadHBox().setSpacing(musicreatorData.getSpacingSizeSmall().get());
        musicreatorElement.getRenewAutoRenewDownloadHBox().setAlignment(Pos.CENTER);

        musicreatorElement.getRenewAutoRenewDownloadLabel().setText("自动下载软件");
        musicreatorElement.getRenewAutoRenewDownloadLabel().setFont(new Font(musicreatorData.getFontSizeLarge().get()));

        musicreatorElement.getRenewAutoRenewDownloadOnButton().setText("开");
        musicreatorElement.getRenewAutoRenewDownloadOnButton().setFont(new Font(musicreatorData.getFontSizeLarge().get()));
        musicreatorElement.getRenewAutoRenewDownloadOnButton().setToggleGroup(musicreatorElement.getRenewAutoRenewDownloadGroup());

        musicreatorElement.getRenewAutoRenewDownloadOffButton().setText("关");
        musicreatorElement.getRenewAutoRenewDownloadOffButton().setFont(new Font(musicreatorData.getFontSizeLarge().get()));
        musicreatorElement.getRenewAutoRenewDownloadOffButton().setToggleGroup(musicreatorElement.getRenewAutoRenewDownloadGroup());

        if (musicreator.getAutoDownloadRenew().get()) {
            musicreatorElement.getRenewAutoRenewDownloadOnButton().setSelected(true);
        } else {
            musicreatorElement.getRenewAutoRenewDownloadOffButton().setSelected(true);
        }
    }

}