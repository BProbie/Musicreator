package com.probie.musicreator.System.Interface;

import java.awt.*;
import java.io.File;
import javax.sound.midi.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.TarsosDSPAudioFormat;
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchProcessor;
import com.probie.musicreator.Musicreator.Musicreator;
import com.probie.musicreator.Musicreator.MusicreatorData;

public interface IAudioSystem {

    /**
     * 将 Midi 音符编号转换成音符名称
     * @param noteIndex 音符编号
     * @return 音符名称
     * */
    default String turnMidiNoteIndexToNoteName(int noteIndex) {
        if (noteIndex < 0 || noteIndex > 127) return "";
        String[] noteNames = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
        int noteLevel = (noteIndex / 12) - 1;
        return noteNames[noteIndex % 12] + noteLevel;
    }

    /**
     *将 Midi 音符编号转换成音符频率
     * @param noteIndex 音符编号
     * @return 音符频率
     * */
    default Double turnMidiNoteIndexToNotePitch(int noteIndex) {
        return Math.pow(2, (noteIndex - 66) / 12.0);
    }

    /**
     * 将 Midi 音频文件转换成音符数据文件
     * @param midiFullFilePath 完整 Midi 音频文件路径
     * @return LinkedHashMap<时间时刻(Tick), Midi音符编号(组合)> 实例化对象
     * */
    default LinkedHashMap<Long, ArrayList<Integer>> turnMidiToData(String midiFullFilePath) {
        LinkedHashMap<Long, ArrayList<Integer>> midiNoteDataLinkedHashMap = new LinkedHashMap<>();
        Sequence sequence;
        try {
            sequence = MidiSystem.getSequence(new File(midiFullFilePath));
        } catch (InvalidMidiDataException | IOException exception) {
            throw new RuntimeException(exception);
        }
        float divisionType = sequence.getDivisionType();
        int resolution = sequence.getResolution();

        Track[] tracks = sequence.getTracks();
        for (Track track : tracks) {
            for (int i = 0; i < track.size(); i++) {
                MidiEvent midiEvent = track.get(i);
                MidiMessage midiMessage = midiEvent.getMessage();

                if (midiMessage instanceof ShortMessage) {
                    ShortMessage shortMessage = (ShortMessage) midiMessage;
                    int command = shortMessage.getCommand();
                    int channel = shortMessage.getChannel();
                    int midiNoteIndex = shortMessage.getData1();
                    int velocity = shortMessage.getData2();

                    if (command == ShortMessage.NOTE_ON && velocity > 0 && !Musicreator.getINSTANCE().getBanChannel().get().contains(channel)) {
                        long tick = midiEvent.getTick();

                        if (midiNoteDataLinkedHashMap.containsKey(tick)) {
                            midiNoteDataLinkedHashMap.get(tick).add(midiNoteIndex);
                        } else {
                            ArrayList<Integer> midiNoteIndexArrayList = new ArrayList<>();
                            midiNoteIndexArrayList.add(midiNoteIndex);
                            midiNoteDataLinkedHashMap.put(tick, midiNoteIndexArrayList);
                        }
                    }
                }
            }
        }
        return midiNoteDataLinkedHashMap;
    }

    /**
     * 将 Mp3 音频文件转换成 Midi 音频文件
     * @param mp3FullFilePath 完整 mp3 音频文件路径
     * @return 完整 Midi 音频文件路径
     * */
    default String turnMp3ToMidi(String mp3FullFilePath) {
        return new File(mp3FullFilePath).getParentFile().getAbsolutePath() + File.separator + new File(mp3FullFilePath).getName().toLowerCase().replace(".mp3", ".mid");
    }

    /**
     * 将 Midi 音频文件转成 Minecraft 函数指令
     * @param midiFullFilePath 完整 Midi 音频文件路径
     * @return Minecraft 函数指令
     * */
    default String turnMidiToMinecraftFunctionCommand(String midiFullFilePath) {
        StringBuilder function = new StringBuilder();
        LinkedHashMap<Long, ArrayList<Integer>> midiNoteDataLinkedHashMap = turnMidiToData(midiFullFilePath);
        function.append(MusicreatorData.getINSTANCE().getMinecraftCommandAddObjective().get()).append("\n");
        function.append(MusicreatorData.getINSTANCE().getMinecraftCommandAddPlayerScore().get()).append("\n");
        function.append(MusicreatorData.getINSTANCE().getMinecraftCommandShowTitle().get()).append("\n");
        midiNoteDataLinkedHashMap.forEach((key, values) -> {
            for (Integer value : values) {
                function.append(MusicreatorData.getINSTANCE().getMinecraftCommandPlayMusic().get().formatted(
                        MusicreatorData.getINSTANCE().getPlayerName().get(),
                        MusicreatorData.getINSTANCE().getObjectiveName().get(),
                        (key * MusicreatorData.getINSTANCE().getMusicPlayDelay().get()) / MusicreatorData.getINSTANCE().getMusicPlaySpeed().get(),
                        MusicreatorData.getINSTANCE().getObjectiveName().get(),
                        (key * MusicreatorData.getINSTANCE().getMusicPlayDelay().get()) / MusicreatorData.getINSTANCE().getMusicPlaySpeed().get(),
                        MusicreatorData.getINSTANCE().getMusicPlayInstrument().get(),
                        turnMidiNoteIndexToNotePitch(value)
                )).append("\n");
            }
        });
        function.append(MusicreatorData.getINSTANCE().getMinecraftCommandStopMusic().get().formatted(
                MusicreatorData.getINSTANCE().getPlayerName().get(),
                MusicreatorData.getINSTANCE().getObjectiveName().get(),
                ((midiNoteDataLinkedHashMap.keySet().stream().reduce((first, second) -> second).orElse(-1L) * MusicreatorData.getINSTANCE().getMusicPlayDelay().get()) / MusicreatorData.getINSTANCE().getMusicPlaySpeed().get()) + 1,
                MusicreatorData.getINSTANCE().getObjectiveName().get(),
                ((midiNoteDataLinkedHashMap.keySet().stream().reduce((first, second) -> second).orElse(-1L) * MusicreatorData.getINSTANCE().getMusicPlayDelay().get()) / MusicreatorData.getINSTANCE().getMusicPlaySpeed().get()) + 1,
                MusicreatorData.getINSTANCE().getObjectiveName().get()
        )).append("\n");
        return function.toString();
    }

    /**
     * 将 Mp3 音频文件转成 Minecraft 函数指令
     * @param mp3FullFilePath 完整 Midi 音频文件路径
     * @return Minecraft 函数指令
     * */
    default String turnMp3MinecraftFunctionCommand(String mp3FullFilePath) {
        return turnMidiToMinecraftFunctionCommand(turnMp3ToMidi(mp3FullFilePath));
    }

}