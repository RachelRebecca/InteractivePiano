package piano.main;

import piano.instruments.InstrumentsPanel;
import piano.keyboard.keyboardaudio.KeyboardListener;
import piano.keyboard.keyboardui.Keyboard;
import piano.recorder.Recorder;
import piano.recorder.RecorderPanel;
import java.awt.*;
import javax.sound.midi.MidiChannel;
import javax.swing.*;

public class PianoGUI extends JFrame {
    private Keyboard keyboard;

    public PianoGUI(MidiChannel midiChannel, Recorder recorder) {
        keyboard = new Keyboard(midiChannel, recorder);

        setTitle("MY PIANO");
        int width = MainFrameInterface.KEYBOARD_WIDTH + 20;
        int height = MainFrameInterface.FRAME_HEIGHT;
        setSize(width, height);
        setMinimumSize(new Dimension(width, height));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(Color.BLACK);

        // consistent constraints
        GridBagConstraints gcb = new GridBagConstraints();
        gcb.fill = GridBagConstraints.HORIZONTAL;
        gcb.gridwidth = 5;
        gcb.weighty = 0.5;

        // first row
        gcb.gridy = 0;
        gcb.gridheight = 1;

        root.add(new RecorderPanel(recorder), gcb);

        // second through sixth row
        gcb.gridy = 1;
        gcb.gridheight = 5;

        root.add(new Keyboard(midiChannel, recorder), gcb);

        // seventh row
        gcb.gridy = 6;
        gcb.gridheight = 1;

        root.add(new InstrumentsPanel(midiChannel), gcb);

        root.addKeyListener(new KeyboardListener(recorder, this));
        root.setFocusable(true);

        setContentPane(root);
    }
    public Keyboard getKeyboard() {
        return keyboard;
    }
}
