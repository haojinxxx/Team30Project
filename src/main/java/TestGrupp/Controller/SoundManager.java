package TestGrupp.Controller;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SoundManager {

    // List to hold all active fire sound clips
    private static List<Clip> activeFireClips = new ArrayList<>();
    private static Clip thrusterSoundClip;
    private static Clip ambientThemeClip;  // New clip for ambient theme music

    // Initialize the sounds
    public static void initialize() {
        try {
            // Load the thruster sound (example)
            File thrusterFile = new File("src/main/resources/sounds/player-thrusters.wav");
            AudioInputStream thrusterAudioIn = AudioSystem.getAudioInputStream(thrusterFile);
            thrusterSoundClip = AudioSystem.getClip();
            thrusterSoundClip.open(thrusterAudioIn);

            // Load the ambient theme music (example)
            File ambientFile = new File("src/main/resources/sounds/ambient-theme.wav");
            AudioInputStream ambientAudioIn = AudioSystem.getAudioInputStream(ambientFile);
            ambientThemeClip = AudioSystem.getClip();
            ambientThemeClip.open(ambientAudioIn);

            // Loop the ambient theme indefinitely
            ambientThemeClip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Play the fire sound (allowing overlapping)
    public static void playFireSound() {
        try {
            // Load and play a new fire sound clip for each shot
            File soundFile = new File("src/main/resources/sounds/player-fire.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip fireSoundClip = AudioSystem.getClip();
            fireSoundClip.open(audioIn);
            fireSoundClip.setFramePosition(0); // Rewind to the start of the sound
            fireSoundClip.start();  // Play the sound

            // Add the new fire clip to the active list
            activeFireClips.add(fireSoundClip);

            // Optionally, you could remove finished clips from the list
            fireSoundClip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    activeFireClips.remove(fireSoundClip);
                }
            });

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Play the thruster sound without stopping immediately when "W" is released
    public static void playThrusterSound() {
        try {
            if (thrusterSoundClip != null && !thrusterSoundClip.isRunning()) {
                thrusterSoundClip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the thruster sound
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Stop the thruster sound only when it's completely finished
    public static void stopThrusterSound() {
        if (thrusterSoundClip != null && thrusterSoundClip.isRunning()) {
            thrusterSoundClip.stop(); // Stop immediately
            thrusterSoundClip.setFramePosition(0);  // Reset to start (so it can start from the beginning next time)
        }
    }

    // Stop all active fire sounds
    public static void stopAllFireSounds() {
        for (Clip clip : activeFireClips) {
            if (clip.isRunning()) {
                clip.stop();
            }
        }
        activeFireClips.clear();  // Clear the list of active clips
    }

    // Stop the ambient theme sound if needed
    public static void stopAmbientTheme() {
        if (ambientThemeClip != null && ambientThemeClip.isRunning()) {
            ambientThemeClip.stop();
        }
    }
}
