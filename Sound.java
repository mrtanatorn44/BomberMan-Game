package BomberMan;

import java.io.*;
import javax.sound.sampled.*;
public class Sound {
    /*
    * youtube > mp3     https://youtubemp3.today/v12/
    * increase mp3      https://www.mp3louder.com/
    * sound trim        https://audiotrimmer.com/
    * mp3 > wav         https://online-audio-converter.com/th/
     */
    public static synchronized void play(final String fileName)
    {
        // only .wav files
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(fileName));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.out.println("cant find some wav sound in Sound class, file name : " + fileName);
                }
            }
        }).start();
    }
}