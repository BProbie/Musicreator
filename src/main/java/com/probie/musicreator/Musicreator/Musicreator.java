package com.probie.musicreator.Musicreator;

import lombok.Data;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.concurrent.Executors;
import javafx.application.Application;
import java.util.concurrent.ExecutorService;
import com.probie.musicreator.Config.ParamConfig;
import com.probie.musicreator.Config.SettingConfig;
import com.probie.musicreator.Musicreator.Interface.IMusicreator;

@Data
public class Musicreator implements IMusicreator {

    private final String NAME = "Musicreator";
    private final String VERSION = "1.0";
    private final String AUTHOR = "Probie";

    private volatile static Musicreator INSTANCE;

    public synchronized static Musicreator getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Musicreator();
        }
        return INSTANCE;
    }

    private String keyRenewConfigUri = "RenewConfigUri";
    private String keyMusicreatorUri = "MusicreatorUri";

    private String keyMusicreatorFilePath = "MusicreatorFilePath";
    private String keyJavaFilePath = "JavaFilePath";
    private String keyLibFilePath = "LibFilePath";
    private String keyConfigFilePath = "ConfigFilePath";

    private String keySettingConfigFileName = "SettingConfigFileName";
    private String keyParamConfigFileName = "ParamConfigFileName";
    private String keyRenewConfigFileName = "RenewConfigFileName";
    private String keyChosenFilePath = "ChosenFilePath";
    private String keySpawnFilePath = "SpawnFilePath";

    private String keyBanChannel = "BanChannel";

    private volatile ExecutorService musicreatorPool = Executors.newFixedThreadPool(64);

    private volatile Supplier<String> timeOfNow = () -> LocalDateTime.now().toString();

    private volatile Supplier<String> renewConfigUri = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeyRenewConfigUri(), "https://github.com/BProbie/DailyPaper/raw/refs/heads/master/renew.config").toString();
    private volatile Supplier<String> musicreatorUri = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeyMusicreatorUri(), "https://github.com/BProbie/DailyPaper/raw/refs/heads/master/" + NAME + ".exe").toString();

    private volatile Supplier<String> currentFilePath = () -> System.getProperty("user.dir");
    private volatile Supplier<String> musicreatorFilePath = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeyMusicreatorFilePath(), currentFilePath.get() + File.separator + "musicreator").toString();
    private volatile Supplier<String> javaFilePath = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeyJavaFilePath(), musicreatorFilePath.get() + File.separator + "jre-21.0.8").toString();
    private volatile Supplier<String> libFilePath = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeyLibFilePath(), musicreatorFilePath.get() + File.separator + "lib").toString();
    private volatile Supplier<String> configFilePath = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeyConfigFilePath(), musicreatorFilePath.get()).toString();

    private volatile Supplier<String> settingConfigFileName = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeySettingConfigFileName(), "setting.config").toString();
    private volatile Supplier<String> paramConfigFileName = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeyParamConfigFileName(), "param.config").toString();
    private volatile Supplier<String> renewConfigFileName = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeyRenewConfigFileName(), "renew.config").toString();
    private volatile Supplier<String> chosenFilePath = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeyChosenFilePath(), currentFilePath.get()).toString();
    private volatile Supplier<String> spawnFilePath = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeySpawnFilePath(), chosenFilePath.get()).toString();

    private volatile Supplier<ArrayList<Integer>> banChannel = () -> Arrays.stream(ParamConfig.getINSTANCE().getLocalDB().get(getKeyBanChannel(), new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15))).toString().replace("[", "").replace("]", "").replace(" ", "").split(",")).filter(string -> !string.isEmpty()).map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));

    @Override
    public void launch(String[] args) {
        Application.launch(MusicreatorApplication.class, args);
    }

}