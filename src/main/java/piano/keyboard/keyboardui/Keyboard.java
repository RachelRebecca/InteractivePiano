package piano.keyboard.keyboardui;

import piano.keyboard.keyboardaudio.Key;
import piano.keyboard.keyboardaudio.KeyListener;
import piano.main.MainFrameInterface;
import piano.recorder.Recorder;

import javax.sound.midi.MidiChannel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Keyboard extends JLayeredPane
{
    private Colors colors;
    private MidiChannel midiChannel;
    private Recorder recorder;
    private final int BACK_LAYER = 0;
    private final int FRONT_LAYER = 1;

    private int placement;

    public Keyboard(MidiChannel midiChannel, Recorder recorder)
    {
        this.midiChannel = midiChannel;
        this.recorder = recorder;
        colors = new Colors();
        this.placement = 0;

        setSize(MainFrameInterface.KEYBOARD_WIDTH, MainFrameInterface.KEYBOARD_HEIGHT);
        setBackground(Color.BLACK);

        addWhitePianoLabels();
        addBlackPianoLabels();
    }

    public void resize(JFrame frame)
    {
        System.out.println(getSize());

        removeAll();

        setPlacement((frame.getWidth() - MainFrameInterface.KEYBOARD_WIDTH) / 2);

        addWhitePianoLabels();
        addBlackPianoLabels();

        revalidate();
    }

    private void addWhitePianoLabels()
    {
        int placement = this.placement;

        int index = 0;
        for (int octave = 0; octave < KeyStats.OCTAVES; octave++)
        {
            for (int whiteKey = 0; whiteKey < KeyStats.NUM_WHITE_KEYS_IN_OCTAVE; whiteKey++)
            {
                addPianoLabel(Color.WHITE, index, placement);

                placement += KeyStats.WHITE_WIDTH;

                if (whiteKey == 2 || whiteKey == KeyStats.NUM_WHITE_KEYS_IN_OCTAVE - 1)
                {
                    index++;
                } else
                {
                    index += 2;
                }
            }
        }
    }

    private void addBlackPianoLabels()
    {
        int placement = this.placement + KeyStats.FIRST_BLACK;
        int index = 1;
        for (int octave = 0; octave < KeyStats.OCTAVES; octave++)
        {
            for (int blackKey = 0; blackKey < KeyStats.NUM_BLACK_KEYS_IN_OCTAVE; blackKey++)
            {
                addPianoLabel(Color.BLACK, index, placement);

                if (blackKey == 1 || blackKey == KeyStats.NUM_BLACK_KEYS_IN_OCTAVE - 1)
                {
                    placement += KeyStats.BLACK_WIDTH + KeyStats.BIG_SPACE_BETWEEN_BLACK_KEYS;
                    index += 3;
                } else
                {
                    placement += KeyStats.BLACK_WIDTH + KeyStats.SPACE_BETWEEN_BLACK_KEYS;
                    index += 2;
                }
            }
        }
    }

    private void addPianoLabel(Color color, int index, int placement)
    {
        PianoLabel pianoLabel = new PianoLabel(color, colors.getColor(index), new Key(index, midiChannel));
        pianoLabel.setLocation(placement, 0);
        pianoLabel.addMouseListener(new KeyListener(recorder));

        if (color == Color.WHITE)
        {
            setLayer(pianoLabel, BACK_LAYER);
        } else
        {
            setLayer(pianoLabel, FRONT_LAYER);
        }

        add(pianoLabel);
    }

    public void setPlacement(int placement)
    {
        this.placement = placement;
    }

    public int getPlacement()
    {
        return this.placement;
    }
}
