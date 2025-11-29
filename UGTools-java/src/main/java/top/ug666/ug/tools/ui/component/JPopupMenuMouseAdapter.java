package top.ug666.ug.tools.ui.component;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import top.ug666.ug.tools.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JPopupMenuMouseAdapter extends MouseAdapter {

    private static final Log logger = LogFactory.get();

    private JPopupMenu popupMenu;

    public JPopupMenuMouseAdapter(JPopupMenu popupMenu) {
        this.popupMenu = popupMenu;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        maybeShowPopup(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        maybeShowPopup(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1: {
                showMainFrame();
                break;
            }
            case MouseEvent.BUTTON2: {
                logger.debug("托盘图标中键事件");
                break;
            }
            case MouseEvent.BUTTON3: {
                logger.debug("托盘图标右键事件");
                break;
            }
            default: {
                break;
            }
        }
    }

    private void maybeShowPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            // 使用 MouseInfo 获取真实的鼠标屏幕位置
            Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
            Dimension size = popupMenu.getPreferredSize();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            
            // 计算菜单位置，显示在鼠标左上方
            int x = mouseLocation.x - size.width;
            int y = mouseLocation.y - size.height;
            
            // 确保不超出屏幕边界
            if (x < 0) x = mouseLocation.x;
            if (y < 0) y = mouseLocation.y;
            if (x + size.width > screenSize.width) x = screenSize.width - size.width;
            if (y + size.height > screenSize.height) y = screenSize.height - size.height;
            
            popupMenu.setLocation(x, y);
            popupMenu.setInvoker(popupMenu);
            popupMenu.setVisible(true);
        }
    }

    public static void showMainFrame() {
        App.mainFrame.setVisible(true);
        if (App.mainFrame.getExtendedState() == Frame.ICONIFIED) {
            App.mainFrame.setExtendedState(Frame.NORMAL);
        } else if (App.mainFrame.getExtendedState() == 7) {
            App.mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        }
        App.mainFrame.requestFocus();
    }
}
