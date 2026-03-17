package com.probie.musicreator.Musicreator;

import lombok.Data;
import java.util.function.Supplier;
import com.probie.musicreator.Musicreator.Interface.IMusicreatorData;

@Data
public class MusicreatorData implements IMusicreatorData {

    private volatile static MusicreatorData INSTANCE;

    public synchronized static MusicreatorData getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new MusicreatorData();
        }
        return INSTANCE;
    }

    private volatile static Musicreator musicreator = Musicreator.getINSTANCE();
    private volatile static MusicreatorData musicreatorData = MusicreatorData.getINSTANCE();
    private volatile static MusicreatorFunction musicreatorFunction = MusicreatorFunction.getINSTANCE();
    private volatile static MusicreatorElement musicreatorElement = MusicreatorElement.getINSTANCE();
    private volatile static MusicreatorStyle musicreatorStyle = MusicreatorStyle.getINSTANCE();
    private volatile static MusicreatorEvent musicreatorEvent = MusicreatorEvent.getINSTANCE();

    private Supplier<Integer> stageWidth = () -> 1500;
    private Supplier<Integer> stageHeight = () -> 800;

    private Supplier<Integer> fontSizeLargeLargeLarge = () -> 30;
    private Supplier<Integer> fontSizeLargeLarge = () -> 25;
    private Supplier<Integer> fontSizeLarge = () -> 20;
    private Supplier<Integer> fontSizeMiddle = () -> 15;
    private Supplier<Integer> fontSizeSmall = () -> 10;
    private Supplier<Integer> fontSizeSmallSmall = () -> 5;

    private Supplier<Integer> delay = () -> 10;
    private Supplier<Integer> offset = () -> 10;

    private Supplier<String> musicName = () -> "Music";
    private Supplier<String> musicPlayInstrument = () -> "minecraft:block.note.guitar";
    private Supplier<Long> musicPlayDelay = () -> 1L;
    private Supplier<Long> musicPlaySpeed = () -> 5L;
    private Supplier<String> objectiveName = () -> musicName.get();
    private Supplier<String> objectiveShowName = () -> getObjectiveName().get();
    private Supplier<String> playerName = () -> "@a";

    private Supplier<String> minecraftCommandAddObjective = () -> "scoreboard objectives add %s dummy %s".formatted(objectiveName.get(), objectiveShowName.get());
    private Supplier<String> minecraftCommandAddPlayerScore = () -> "scoreboard players add %s %s 1".formatted(playerName.get(), objectiveName.get());
    private Supplier<String> minecraftCommandShowTitle = () -> "title @a actionbar [{\"text\":\"正在播放音乐\",\"color\":\"aqua\",\"bold\":true},{\"text\":\"【\",\"color\":\"aqua\",\"bold\":true},{\"text\":\"%s\",\"color\":\"gold\",\"bold\":true},{\"text\":\"】\",\"color\":\"aqua\",\"bold\":true}]".formatted(musicName.get());
    private Supplier<String> minecraftCommandPlayMusic = () -> "execute %s[score_%s_min=%d,score_%s=%d] ~ ~ ~ playsound %s block @s ~ ~ ~ 1 %f"; // player objective tick objective tick instrument pitch
    private Supplier<String> minecraftCommandStopMusic = () -> "scoreboard players reset %s[score_%s_min=%d,score_%s=%d] %s"; // player objective tick objective tick objective

}