package piano.main;

import piano.instruments.InstrumentsPanel;
import piano.keyboard.keyboardui.Keyboard;
import piano.recorder.Recorder;
import piano.recorder.RecorderPanel;
import java.awt.*;
import javax.sound.midi.MidiChannel;
import javax.swing.*;

public class PianoGUI extends JFrame {

    public PianoGUI(MidiChannel midiChannel, Recorder recorder) {
        setTitle("MY PIANO");
        int width = MainFrameInterface.KEYBOARD_WIDTH;
        int height = MainFrameInterface.FRAME_HEIGHT;
        setSize(width, height);
        setMinimumSize(new Dimension(width, height));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel root = new JPanel(new GridBagLayout());
        GridBagConstraints gcb = new GridBagConstraints();

        root.setBackground(Color.BLACK);

        gcb.gridx = 0;
        gcb.gridy = 0;
        gcb.gridwidth = 5;
        gcb.gridheight = 1;

        root.add(new RecorderPanel(recorder), gcb);

        gcb.gridx = 0;
        gcb.gridy = 1;
        gcb.gridwidth = 5;
        gcb.gridheight = 5;

        root.add(new Keyboard(midiChannel, recorder), gcb);

        gcb.gridx = 0;
        gcb.gridy = 6;
        gcb.gridwidth = 5;
        gcb.gridheight = 1;

        root.add(new InstrumentsPanel(midiChannel), gcb);
        setContentPane(root);

	}
}
