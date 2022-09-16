package piano.main;

import piano.instruments.InstrumentsPanel;
import piano.keyboard.keyboardui.Keyboard;
import piano.recorder.Recorder;
import piano.recorder.RecorderPanel;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.sound.midi.MidiChannel;
import javax.swing.*;

public class PianoGUI extends JFrame
{
    public PianoGUI(MidiChannel midiChannel, Recorder recorder)
    {
        setTitle("MY PIANO");
        setSize(MainFrameInterface.KEYBOARD_WIDTH, MainFrameInterface.FRAME_HEIGHT);
        setMinimumSize(new Dimension(740, 205));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(Color.BLACK);

        Keyboard keyboard = new Keyboard(midiChannel, recorder);

        root.add(new RecorderPanel(recorder), BorderLayout.NORTH);
        root.add(keyboard, BorderLayout.CENTER);
        root.add(new InstrumentsPanel(midiChannel), BorderLayout.SOUTH);
        setContentPane(root);

        onResize(keyboard);
    }

    private void onResize(Keyboard keyboard)
    {
        addComponentListener(new ComponentAdapter()
        {
            public void componentResized(ComponentEvent evt)
            {
                if (getWidth() != (keyboard.getWidth() + (keyboard.getPlacement() * 2)))
                {
                    keyboard.resize((JFrame) evt.getSource());
                }
            }
        });
    }
}
