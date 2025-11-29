package top.ug666.ug.tools.ui.listener;


import cn.hutool.core.util.RuntimeUtil;
import top.ug666.ug.tools.ui.form.MainWindow;
import top.ug666.ug.tools.ui.form.func.NetForm;
import top.ug666.ug.tools.util.SystemUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * <pre>
 * tab事件监听
 * </pre>
 *
 * @author <a href="https://github.com/rememberber">RememBerBer</a>
 * @since 2017/6/21.
 */
@Slf4j
public class TabListener {

    private static boolean warnFlag = true;

    public static void addListeners() {
        MainWindow.getInstance().getTabbedPane().addChangeListener(new ChangeListener() {
            /**
             * Invoked when the target of the listener has changed its state.
             *
             * @param e a ChangeEvent object
             */
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = MainWindow.getInstance().getTabbedPane().getSelectedIndex();
                switch (index) {
                    case 9:
                        try {
                            String ipConfigStr;
                            if (SystemUtil.isWindowsOs()) {
                                ipConfigStr = RuntimeUtil.execForStr("ipconfig");
                            } else {
                                ipConfigStr = RuntimeUtil.execForStr("ifconfig");
                            }
                            NetForm.getInstance().getIpConfigTextArea().setText(ipConfigStr);
                            NetForm.getInstance().getIpConfigTextArea().setCaretPosition(0);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            log.error(ExceptionUtils.getStackTrace(ex));
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
