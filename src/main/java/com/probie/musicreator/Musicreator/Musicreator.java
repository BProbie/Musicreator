package com.probie.musicreator.Musicreator;

import com.probie.musicreator.Config.Config;
import com.probie.musicreator.Config.ConfigConfig;
import lombok.Data;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.function.Supplier;
import java.util.concurrent.Executors;
import javafx.application.Application;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

import com.probie.musicreator.Musicreator.Interface.IMusicreator;

@Data
public class Musicreator implements IMusicreator {

    private final static String NAME = "Musicreator";
    private final static String VERSION = "1.0";
    private final static String AUTHOR = "Probie";

    private volatile static Musicreator INSTANCE;

    public synchronized static Musicreator getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Musicreator();
        }
        return INSTANCE;
    }

    private String keyMusicreatorFilePath = "MusicreatorFilePath";
    private String keyJavaFilePath = "JavaFilePath";
    private String keyLibFilePath = "LibFilePath";
    private String keyConfigFilePath = "ConfigFilePath";

    private String keyConfigConfigFileName = "ConfigConfigFileName";
    private String keyChosenFilePath = "ChosenFilePath";

    private String keyBanChannel = "BanChannel";


    private volatile ExecutorService musicreatorPool = Executors.newFixedThreadPool(64);

    private volatile Supplier<String> timeOfNow = () -> LocalDateTime.now().toString();

    private volatile Supplier<String> currentFilePath = () -> System.getProperty("user.dir");
    private volatile Supplier<String> musicreatorFilePath = () -> ConfigConfig.getINSTANCE().getLocalDB().get(getKeyMusicreatorFilePath(), currentFilePath.get() + File.separator + "musicreator").toString();
    private volatile Supplier<String> javaFilePath = () -> ConfigConfig.getINSTANCE().getLocalDB().get(getKeyJavaFilePath(), musicreatorFilePath.get() + File.separator + "jre-21.0.8").toString();
    private volatile Supplier<String> libFilePath = () -> ConfigConfig.getINSTANCE().getLocalDB().get(getKeyLibFilePath(), musicreatorFilePath.get() + File.separator + "lib").toString();
    private volatile Supplier<String> configFilePath = () -> ConfigConfig.getINSTANCE().getLocalDB().get(getKeyConfigFilePath(), musicreatorFilePath.get()).toString();

    private volatile Supplier<String> configConfigFileName = () -> ConfigConfig.getINSTANCE().getLocalDB().get(getKeyConfigConfigFileName(), "config.config").toString();
    private volatile Supplier<String> chosenFilePath = () -> ConfigConfig.getINSTANCE().getLocalDB().get(getKeyChosenFilePath(), currentFilePath.get()).toString();

    private volatile Supplier<ArrayList<Integer>> banChannel = () -> Arrays.stream(ConfigConfig.getINSTANCE().getLocalDB().get(getKeyBanChannel(), new ArrayList<>(List.of(9))).toString().replace("[", "").replace("]", "").replace(" ", "").split(",")).filter(string -> !string.isEmpty()).map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));

    @Override
    public void launch(String[] args) {
        Application.launch(MusicreatorApplication.class, args);
    }

}