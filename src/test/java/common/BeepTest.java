package common;

import org.junit.Test;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BeepTest {
    @Test
    public void test() {
        // Toolkit.getDefaultTool|kit().beep();
        FileInputStream fileau = null;
        AudioStream as = null;
        try {
            File mediaPath = new File("D:/tmp/media/");
            File[] mediaFiles = mediaPath.listFiles();
            Integer[] timeouts = this.getTimeouts(mediaFiles.length);
            if (null != mediaFiles) {
                while (true) {
                    int index = 0;
                    for (File mediaFile : mediaFiles) {
                        index++;
                        if (mediaFile.getName().endsWith(".wav")) {
                            fileau = new FileInputStream(mediaFile);
                            as = new AudioStream(fileau);
                            AudioPlayer.player.start(as);
                            TimeUnit.MILLISECONDS.sleep(timeouts[index]);
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

    public static Integer[] getTimeouts(int length) {
        List<Integer> timeouts = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            int timeout = (int) (Math.random() * 6 + 5) * 100;
            timeouts.add(timeout);
            System.out.print(timeout + " ");
        }
        return timeouts.toArray(new Integer[timeouts.size()]);
    }

    @Test
    public void tmp(){
        List<Integer> timeouts = new ArrayList<>(34);
        for (int i = 0; i < 34; i++) {
            int timeout = (int) (Math.random() * 6 + 5) * 100;
            timeouts.add(timeout);
            System.out.print(timeout + " ");
        }
    }
}
