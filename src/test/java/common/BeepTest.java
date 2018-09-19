package common;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

public class BeepTest {
    public static void main(String[] args) {
        // Toolkit.getDefaultTool|kit().beep();
        FileInputStream fileau = null;
        AudioStream as = null;
        try {
            File mediaPath = new File("D:/tmp/media/");
            File[] mediaFiles = mediaPath.listFiles();
            if(null != mediaFiles){
                while (true){
                    for(File mediaFile : mediaFiles){
                        if (mediaFile.getName().endsWith(".wav")){
                            fileau  = new FileInputStream(mediaFile);
                            as = new AudioStream(fileau);
                            AudioPlayer.player.start(as);
                            TimeUnit.MILLISECONDS.sleep(500);
                            fileau.close();
                            as.close();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
