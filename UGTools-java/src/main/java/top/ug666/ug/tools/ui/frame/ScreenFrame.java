package top.ug666.ug.tools.ui.frame;

import top.ug666.ug.tools.ui.UiConsts;
import top.ug666.ug.tools.ui.listener.ScreenMouseListener;

import javax.swing.*;
import java.awt.*;

/**
 * 屏幕透明框架
 */
public class ScreenFrame extends JFrame {
    private static ScreenFrame screenFrame;

    private ScreenFrame() {
    }

    public static ScreenFrame getInstance() {
        if (screenFrame == null) {
            screenFrame = new ScreenFrame();
            screenFrame.init();
        }
        return screenFrame;
    }

    public static void exit() {
        getInstance().setVisible(false);
        screenFrame = null;
    }

    private void init() {
        setName(UiConsts.APP_NAME);
        setTitle(UiConsts.APP_NAME + "-ColorPickerScreen");
        FrameUtil.setFrameIcon(this);
        setAlwaysOnTop(false);
        setAutoRequestFocus(true);
        setUndecorated(true);
        setOpacity(0.01f);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(screen);
        setBounds(0, 0, (int) screen.getWidth(), (int) screen.getHeight());
        ScreenMouseListener screenMouseListener = new ScreenMouseListener();
        addMouseListener(screenMouseListener);
        addMouseMotionListener(screenMouseListener);
    }
}
