package com.probie.musicreator.Musicreator;

import lombok.Data;
import java.io.File;
import java.util.List;
import java.util.Arrays;
import java.io.Closeable;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.concurrent.Executors;
import javafx.application.Application;
import java.util.concurrent.ExecutorService;
import com.probie.musicreator.Config.ParamConfig;
import com.probie.musicreator.Config.SettingConfig;
import com.probie.musicreator.System.ComputerSystem;
import com.probie.musicreator.Musicreator.Interface.IMusicreator;

@Data
public class Musicreator implements IMusicreator, Closeable {

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

    /// 参数键
    private String keyBanChannel = "BanChannel";

    /// 设置键
    private String keyMusicreatorFilePath = "MusicreatorFilePath";
    private String keyJavaFilePath = "JavaFilePath";
    private String keyLibFilePath = "LibFilePath";
    private String keyConfigFilePath = "ConfigFilePath";
    private String keyResourceFilePath = "ResourceFilePath";

    private String keySettingConfigFileName = "SettingConfigFileName";
    private String keyParamConfigFileName = "ParamConfigFileName";
    private String keyRenewConfigFileName = "RenewConfigFileName";
    private String keyChosenFilePath = "ChosenFilePath";
    private String keySpawnFilePath = "SpawnFilePath";

    private String keyRenewConfigUri = "RenewConfigUri";
    private String keyMusicreatorUriWin = "MusicreatorUriWin";
    private String keyMusicreatorUriLinux = "MusicreatorUriLinux";
    private String keyMusicreatorUriMac = "MusicreatorUriMac";
    private String keyMusicreatorUriAndroid = "MusicreatorUriAndroid";
    private String keyMusicreatorUri = "MusicreatorUri";

    private String keyRenewLocalFilePath = "RenewLocalFilePath";
    private String keyRenewLocalFileName = "RenewLocalFileName";
    private String keyMusicreatorLocalFilePath = "MusicreatorLocalFilePath";
    private String keyMusicreatorLocalFileName = "MusicreatorLocalFileName";

    private String keyAutoCheckRenew = "AutoCheckRenew";
    private String keyAutoDownloadRenew = "AutoDownloadRenew";
    private String keyAutoOpenRenew = "AutoOpenRenew";

    private String keyDebug = "Debug";


    /// 全局值
    private volatile ExecutorService musicreatorPool = Executors.newFixedThreadPool(64);

    private volatile Supplier<String> timeOfNow = () -> LocalDateTime.now().toString();

    /// 参数值
    private volatile Supplier<ArrayList<Integer>> banChannel = () -> Arrays.stream(ParamConfig.getINSTANCE().getLocalDB().get(getKeyBanChannel(), new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15))).toString().replace("[", "").replace("]", "").replace(" ", "").split(",")).filter(string -> !string.isEmpty()).map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));

    /// 设置值
    private volatile Supplier<String> currentFilePath = () -> System.getProperty("user.dir");
    private volatile Supplier<String> musicreatorFilePath = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeyMusicreatorFilePath(), currentFilePath.get() + File.separator + "musicreator").toString();
    private volatile Supplier<String> javaFilePath = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeyJavaFilePath(), musicreatorFilePath.get() + File.separator + "jdk-21.0.8").toString();
    private volatile Supplier<String> libFilePath = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeyLibFilePath(), musicreatorFilePath.get() + File.separator + "lib").toString();
    private volatile Supplier<String> configFilePath = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeyConfigFilePath(), musicreatorFilePath.get()).toString();

    private volatile Supplier<String> settingConfigFileName = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeySettingConfigFileName(), "setting.config").toString();
    private volatile Supplier<String> paramConfigFileName = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeyParamConfigFileName(), "param.config").toString();
    private volatile Supplier<String> renewConfigFileName = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeyRenewConfigFileName(), "renew.config").toString();
    private volatile Supplier<String> chosenFilePath = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeyChosenFilePath(), currentFilePath.get()).toString();
    private volatile Supplier<String> spawnFilePath = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeySpawnFilePath(), chosenFilePath.get()).toString();

    private volatile Supplier<String> renewConfigUri = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeyRenewConfigUri(), "https://github.com/BProbie/Musicreator/raw/refs/heads/master/res/renew.config").toString();
    private volatile Supplier<String> musicreatorUriWin = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeyMusicreatorUriWin(), "https://github.com/BProbie/Musicreator/raw/refs/heads/master/res/" + NAME + ".exe").toString();
    private volatile Supplier<String> musicreatorUriLinux = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeyMusicreatorUriLinux(), "https://github.com/BProbie/Musicreator/raw/refs/heads/master/res/" + NAME + ".exe").toString();
    private volatile Supplier<String> musicreatorUriMac = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeyMusicreatorUriMac(), "https://github.com/BProbie/Musicreator/raw/refs/heads/master/res/" + NAME + ".exe").toString();
    private volatile Supplier<String> musicreatorUriAndroid = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeyMusicreatorUriAndroid(), "https://github.com/BProbie/Musicreator/raw/refs/heads/master/res/" + NAME + ".exe").toString();
    private volatile Supplier<String> musicreatorUri = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeyMusicreatorUri(), ComputerSystem.getINSTANCE().getSystemName().toLowerCase().contains("windows") ? musicreatorUriWin.get() : ComputerSystem.getINSTANCE().getSystemName().toLowerCase().contains("linux") ? musicreatorUriLinux.get() : ComputerSystem.getINSTANCE().getSystemName().toLowerCase().contains("mac") ? musicreatorUriMac.get() : ComputerSystem.getINSTANCE().getSystemName().toLowerCase().contains("android") ? musicreatorUriAndroid.get() : null).toString();

    private volatile Supplier<String> renewLocalFilePath = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeyRenewLocalFilePath(), libFilePath.get()).toString();
    private volatile Supplier<String> renewLocalFileName = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeyRenewLocalFileName(), "renew.jar").toString();
    private volatile Supplier<String> musicreatorLocalFilePath = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeyMusicreatorLocalFilePath(), currentFilePath.get()).toString();
    private volatile Supplier<String> musicreatorLocalFileName = () -> SettingConfig.getINSTANCE().getLocalDB().get(getKeyMusicreatorLocalFileName(), NAME + ".exe").toString();

    private volatile Supplier<Boolean> autoCheckRenew = () -> Boolean.parseBoolean(String.valueOf(SettingConfig.getINSTANCE().getLocalDB().get(getKeyAutoCheckRenew(), false)));
    private volatile Supplier<Boolean> autoDownloadRenew = () -> Boolean.parseBoolean(String.valueOf(SettingConfig.getINSTANCE().getLocalDB().get(getKeyAutoDownloadRenew(), false)));
    private volatile Supplier<Boolean> autoOpenRenew = () -> Boolean.parseBoolean(String.valueOf(SettingConfig.getINSTANCE().getLocalDB().get(getKeyAutoOpenRenew(), true)));

    private volatile Supplier<Boolean> debug = () -> Boolean.parseBoolean(String.valueOf(SettingConfig.getINSTANCE().getLocalDB().get(getKeyDebug(), false)));

    @Override
    public void launch(String[] args) {
        Application.launch(MusicreatorApplication.class, args);
    }

    @Override
    public void close() {
        /// 保存参数
        ParamConfig.getINSTANCE().getLocalDB().set(Musicreator.getINSTANCE().getKeyBanChannel(), Musicreator.getINSTANCE().getBanChannel().get());

        /// 保存设置
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

        if (!new File(Musicreator.getINSTANCE().getConfigFilePath().get()).exists()) {
            new File(Musicreator.getINSTANCE().getConfigFilePath().get()).mkdirs();
        }

        ParamConfig.getINSTANCE().getLocalDB().commit();
        SettingConfig.getINSTANCE().getLocalDB().commit();
    }
}