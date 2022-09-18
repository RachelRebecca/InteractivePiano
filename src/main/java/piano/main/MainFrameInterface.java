package piano.main;

public interface MainFrameInterface {
    int KEYBOARD_WIDTH = 750;
    int KEYBOARD_HEIGHT = 350;
    int RECORDER_PANEL_HEIGHT = 40;
    int INSTRUMENTS_PANEL_HEIGHT = 40;
    int FRAME_HEIGHT = KEYBOARD_HEIGHT + ((RECORDER_PANEL_HEIGHT + INSTRUMENTS_PANEL_HEIGHT) * 2);
}
