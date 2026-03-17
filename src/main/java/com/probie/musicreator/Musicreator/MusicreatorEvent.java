package com.probie.musicreator.Musicreator;

import lombok.Data;
import java.io.File;
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
        musicreatorElement.getMusicreatorChooseFileButton().setOnAction(actionEvent -> {
            File file = musicreatorElement.getMusicreatorChooseFileFileChooser().showOpenDialog(musicreatorElement.getStage());
            if (file != null) {
                musicreatorElement.getMusicreatorChooseFileLabel().setText(file.getAbsolutePath());
                musicreator.setChosenFilePath(() -> new File(musicreatorElement.getMusicreatorChooseFileLabel().getText()).getParentFile().getAbsolutePath());
                musicreatorElement.getMusicreatorChooseFileFileChooser().setInitialDirectory(new File(musicreator.getChosenFilePath().get()));

                String format = file.getName().toLowerCase().substring(file.getName().lastIndexOf('.') + 1);
                if (format.equals("mid") || format.equals("mp3")) {
                    musicreator.getMusicreatorPool().submit(() -> {
                        switch (format) {
                            case "mid": {
                                System.out.println("mid");
                                break;
                            }
                            case "mp3": {
                                System.out.println("mp3");
                                break;
                            }
                        }
                    });
                }
            }
        });
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

    }

    @Override
    public void createParamEvent() {

    }

    @Override
    public void createSettingEvent() {

    }

    @Override
    public void createRenewEvent() {

    }

}