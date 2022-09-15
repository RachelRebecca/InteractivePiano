package piano.main;

import piano.instruments.InstrumentsPanel;
import piano.keyboard.keyboardui.KeyStats;
import piano.keyboard.keyboardui.Keyboard;
import piano.keyboard.keyboardui.PianoLabel;
import piano.recorder.Recorder;
import piano.recorder.RecorderPanel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.sound.midi.MidiChannel;
import javax.swing.*;

public class PianoGUI extends JFrame {

    public PianoGUI(MidiChannel midiChannel, Recorder recorder) {
        setTitle("MY PIANO");
        setSize(MainFrameInterface.KEYBOARD_WIDTH, MainFrameInterface.FRAME_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(Color.BLACK);

        Keyboard keyboard = new Keyboard(midiChannel, recorder);

        root.add(new RecorderPanel(recorder), BorderLayout.NORTH);
        root.add(keyboard, BorderLayout.CENTER);
        root.add(new InstrumentsPanel(midiChannel), BorderLayout.SOUTH);
        setContentPane(root);

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt)
            {
                Component c = (Component) evt.getSource();

                for (Component component : keyboard.getComponents())
                {
                    PianoLabel key = (PianoLabel) component;

                    int height = c.getHeight() - MainFrameInterface.RECORDER_PANEL_HEIGHT
                            - MainFrameInterface.INSTRUMENTS_PANEL_HEIGHT;

                    int whiteWidth = (c.getWidth() / KeyStats.OCTAVES) / KeyStats.NUM_WHITE_KEYS_IN_OCTAVE - KeyStats.SPACE_BETWEEN_WHITE_KEYS;
                    int blackWidth = (int) (13.7 * whiteWidth / 23.5); // proportion: 13.7 and 23.5 are average size of white and black keys, respectively

                    int blackHeight = (int) (height / 1.75);

                    if (key.getDefaultColor() == Color.WHITE)
                    {
                        key.setSize(new Dimension(whiteWidth, height));
                    }
                    else {
                        key.setSize(new Dimension(blackWidth, blackHeight));
                    }

                }

                System.out.println(c.getSize());
                revalidate();
            }
        });
	}
}
