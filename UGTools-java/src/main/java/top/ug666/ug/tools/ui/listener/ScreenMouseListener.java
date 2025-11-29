package top.ug666.ug.tools.ui.listener;

import top.ug666.ug.tools.ui.Init;
import top.ug666.ug.tools.ui.form.MainWindow;
import top.ug666.ug.tools.ui.form.func.ColorBoardForm;
import top.ug666.ug.tools.ui.form.func.ColorPickerForm;
import top.ug666.ug.tools.ui.frame.ColorPickerFrame;
import top.ug666.ug.tools.ui.frame.ScreenFrame;
import top.ug666.ug.tools.util.ColorUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * <pre>
 * ScreenMouseListener
 * </pre>
 *
 * @author <a href="https://github.com/rememberber">RememBerBer</a>
 * @since 2019/11/19.
 */
@Slf4j
public class ScreenMouseListener implements MouseInputListener {
    public static Robot robot;

    @Override
    public void mouseClicked(MouseEvent e) {
        Point point = e.getLocationOnScreen();
        ScreenFrame.exit();
        Color color = robot.getPixelColor(point.x, point.y);

        ColorBoardForm.setSelectedColor(color);
        // 跳转到调色板 Tab（索引 12）
        MainWindow.getInstance().getTabbedPane().setSelectedIndex(12);
        Init.showMainFrame();
        ColorPickerFrame.exit();
        ScreenMouseListener.robot = null;
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point point = e.getLocationOnScreen();
        int x = point.x;
        int y = point.y;

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        if (x < 250 && y < 300) {
            ColorPickerFrame.getInstance().setLocation((int) screen.getWidth() - 220, (int) screen.getHeight() - 300);
        } else if (x > (screen.getWidth() - 250) && y > (screen.getHeight() - 350)) {
            ColorPickerFrame.getInstance().setLocation(10, 10);
        }

        // 由于不透明度问题 颜色值存在误差
        Color color = robot.getPixelColor(x, y);
        ColorPickerForm.getInstance().getZoomPanel().setBackground(color);
        ColorPickerForm.getInstance().getCurrentColorPanel().setBackground(color);
        ColorPickerForm.getInstance().getCurrentColorLabel().setText(ColorUtil.toHex(color));
    }

    public static Robot getRobot() {
        if (robot == null) {
            try {
                robot = new Robot();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(ColorBoardForm.getInstance().getColorBoardPanel(), e.getMessage(), "系统异常", JOptionPane.ERROR_MESSAGE);
                log.error(ExceptionUtils.getStackTrace(e));
            }
        }
        return robot;
    }
}
