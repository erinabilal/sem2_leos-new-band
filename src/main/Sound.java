package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	Clip[] clips = new Clip[8];
	URL soundURL[] = new URL[8];
	
	public Sound() {
		
		soundURL[0] = getClass().getResource("/sound/title select.wav");
		soundURL[1] = getClass().getResource("/sound/grow apart.wav");
		soundURL[2] = getClass().getResource("/sound/overworld.wav");
		soundURL[3] = getClass().getResource("/sound/dialogue.wav");
		soundURL[4] = getClass().getResource("/sound/hitmonster.wav");
		soundURL[5] = getClass().getResource("/sound/receivedamage.wav");
		soundURL[6] = getClass().getResource("/sound/gameover.wav");
		soundURL[7] = getClass().getResource("/sound/victoryfanfare.wav");
	
		for (int i = 0; i < soundURL.length; i++) {
            if (soundURL[i] != null) {
                try {
                    AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
                    clips[i] = AudioSystem.getClip();
                    clips[i].open(ais);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
	}
	
	public void setFile(int i) {
		clip = clips[i];
	}
	
	public void play() { 
		if ( clip != null) {
			clip.setFramePosition(0);
			clip.start();
		}
	}
	
	public void loop() { if ( clip != null) clip.loop(Clip.LOOP_CONTINUOUSLY); }
	
	public void stop() { if ( clip != null) clip.stop(); }
}
