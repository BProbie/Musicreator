package com.probie.musicreator.Musicreator;

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

        musicreatorElement.getMusicreatorChooseFileButton().setText("浏览");
        musicreatorElement.getMusicreatorChooseFileButton().setFont(new Font(musicreatorData.getFontSizeLarge().get()));

        musicreatorElement.getMusicreatorChooseFileLabel().setText(musicreator.getChosenFilePath().get());
        musicreatorElement.getMusicreatorChooseFileLabel().setFont(new Font(musicreatorData.getFontSizeLarge().get()));

        musicreatorElement.getMusicreatorChooseFileFileChooser().getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ALL", "*.*"),
                new FileChooser.ExtensionFilter("MID", "*.mid"),
                new FileChooser.ExtensionFilter("MP3", "*.mp3")
        );
        musicreatorElement.getMusicreatorChooseFileFileChooser().setInitialDirectory(new File(musicreatorElement.getMusicreatorChooseFileLabel().getText()));
    }

    @Override
    public void createParamStyle() {

    }

    @Override
    public void createSettingStyle() {

    }

    @Override
    public void createRenewStyle() {

    }

}