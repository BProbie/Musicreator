package com.probie.musicreator.Musicreator;

import javafx.scene.control.*;
import lombok.Data;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.scene.layout.BorderPane;
import com.probie.musicreator.Musicreator.Interface.IMusicreatorElement;

@Data
public class MusicreatorElement implements IMusicreatorElement {

    private volatile static MusicreatorElement INSTANCE;

    public synchronized static MusicreatorElement getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new MusicreatorElement();
        }
        return INSTANCE;
    }

    private volatile static Musicreator musicreator = Musicreator.getINSTANCE();
    private volatile static MusicreatorData musicreatorData = MusicreatorData.getINSTANCE();
    private volatile static MusicreatorFunction musicreatorFunction = MusicreatorFunction.getINSTANCE();
    private volatile static MusicreatorElement musicreatorElement = MusicreatorElement.getINSTANCE();
    private volatile static MusicreatorStyle musicreatorStyle = MusicreatorStyle.getINSTANCE();
    private volatile static MusicreatorEvent musicreatorEvent = MusicreatorEvent.getINSTANCE();

    private Stage stage;

    private Scene scene;

    /**
     * root
     * */
    private BorderPane rootPane = new BorderPane();
    private VBox rootPaneCenterStageBarVBox = new VBox();

    /**
     * menu
     * */
    private VBox rootPaneLeftMenuBarVBox = new VBox();
    private Button rootPaneLeftMenuBarMusicreatorButton = new Button();
    private Button rootPaneLeftMenuBarParamButton = new Button();
    private Button rootPaneLeftMenuBarSettingButton = new Button();
    private Button rootPaneLeftMenuBarRenewButton = new Button();

    /**
     * musicreator
     * */
    private VBox musicreatorVBox = new VBox();
    private HBox musicreatorChooseFileHBox = new HBox();
    private Button musicreatorChooseFileChooseFileButton = new Button();
    private Label musicreatorChooseFileShowChosenFileLabel = new Label();
    private FileChooser musicreatorChooseFileFileChooser = new FileChooser();

    private HBox musicreatorSpawnFileHBox = new HBox();
    private Button musicreatorSpawnFileChooseFileButton = new Button();
    private TextField musicreatorSpawnFileShowChosenFileTextField = new TextField();
    private Button musicreatorSpawnFileSpawnFileButton = new Button();
    private FileChooser musicreatorSpawnFileFileChooser = new FileChooser();

    /**
     * param
     * */
    private VBox paramVBox = new VBox();

    /**
     * setting
     * */
    private VBox settingVBox = new VBox();

    /**
     * renew
     * */
    private VBox renewVBox = new VBox();
    private VBox renewCheckRenewVBox = new VBox();
    private Button renewCheckRenewButton = new Button();
    private VBox renewDownloadRenewVBox = new VBox();
    private ScrollPane renewDownloadRenewScrollPane = new ScrollPane();
    private TextArea renewDownloadRenewTextArea = new TextArea();
    private Button renewDownloadButton = new Button();

    @Override
    public void createElement(Stage stage) {
        if (this.stage == null) this.stage = stage;
        musicreatorElement.createRenewElement();
        musicreatorElement.createSettingElement();
        musicreatorElement.createParamElement();
        musicreatorElement.createMusicreatorElement();
        musicreatorElement.createMenuBarElement();
        musicreatorElement.createStageBarElement();
        musicreatorElement.createRootPaneElement();
        musicreatorElement.createSceneElement();
        musicreatorElement.createStageElement();
    }

    @Override
    public void createStageElement() {
        stage.setScene(scene);
    }

    @Override
    public void createSceneElement() {
        scene = new Scene(rootPane);
    }

    @Override
    public void createRootPaneElement() {
        rootPane.setLeft(rootPaneLeftMenuBarVBox);
        rootPane.setCenter(rootPaneCenterStageBarVBox);
    }

    @Override
    public void createStageBarElement() {
        rootPaneCenterStageBarVBox.getChildren().add(musicreatorVBox);
    }

    @Override
    public void createMenuBarElement() {
        rootPaneLeftMenuBarVBox.getChildren().addAll(rootPaneLeftMenuBarMusicreatorButton, rootPaneLeftMenuBarParamButton, rootPaneLeftMenuBarSettingButton, rootPaneLeftMenuBarRenewButton);
    }

    @Override
    public void createMusicreatorElement() {
        musicreatorChooseFileHBox.getChildren().addAll(musicreatorChooseFileChooseFileButton, musicreatorChooseFileShowChosenFileLabel);

        musicreatorSpawnFileHBox.getChildren().addAll(musicreatorSpawnFileChooseFileButton, musicreatorSpawnFileShowChosenFileTextField, musicreatorSpawnFileSpawnFileButton);

        musicreatorVBox.getChildren().addAll(musicreatorChooseFileHBox, musicreatorSpawnFileHBox);
    }

    @Override
    public void createParamElement() {

    }

    @Override
    public void createSettingElement() {

    }

    @Override
    public void createRenewElement() {
        renewCheckRenewVBox.getChildren().addAll(renewCheckRenewButton);

        renewDownloadRenewScrollPane.setContent(renewDownloadRenewTextArea);
        renewDownloadRenewVBox.getChildren().addAll(renewDownloadRenewScrollPane, renewDownloadButton);

        renewVBox.getChildren().addAll(renewCheckRenewVBox, renewDownloadRenewVBox);
    }

}