package common;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BeepTest2 {
    public static void main(String[] args) {
        AudioStream as = null;
        FileInputStream fileau = null;
        try {
            File mediaFile = new File("D:/tmp/media/notify.wav");
            fileau = new FileInputStream(mediaFile);
            as = new AudioStream(fileau);
            AudioPlayer.player.start(as);
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                as.close();
                fileau.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
