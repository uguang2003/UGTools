package top.ug666.ug.tools;

import cn.hutool.core.io.FileUtil;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatDesktop;
import com.formdev.flatlaf.fonts.jetbrains_mono.FlatJetBrainsMonoFont;
import com.formdev.flatlaf.util.SystemInfo;
import top.ug666.ug.tools.ui.Init;
import top.ug666.ug.tools.ui.dialog.AboutDialog;
import top.ug666.ug.tools.ui.dialog.SettingDialog;
import top.ug666.ug.tools.ui.form.LoadingForm;
import top.ug666.ug.tools.ui.form.MainWindow;
import top.ug666.ug.tools.ui.frame.MainFrame;
import top.ug666.ug.tools.util.ConfigUtil;
import top.ug666.ug.tools.util.MybatisUtil;
import top.ug666.ug.tools.util.SystemUtil;
import top.ug666.ug.tools.util.UpgradeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * <pre>
 * Main Enter!
 * </pre>
 *
 * @author UG
 * @since 2025/11/29.
 */
@Slf4j
public class App {

    public static ConfigUtil config = ConfigUtil.getInstance();

    public static MainFrame mainFrame;

    public static SqlSession sqlSession = MybatisUtil.getSqlSession();

    public static SystemTray tray;

    public static TrayIcon trayIcon;

    public static JPopupMenu popupMenu;

    public static File tempDir = null;

    public static void main(String[] args) {

        if (SystemInfo.isMacOS) {
//            java -Xdock:name="UGTools" -Xdock:icon=UGTools.jpg ... (whatever else you normally specify here)
//            java -Xms64m -Xmx256m -Dapple.awt.application.name="UGTools" -Dcom.apple.mrj.application.apple.menu.about.name="UGTools" -cp "./lib/*" com.luoboduner.moo.tool.App
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("apple.awt.application.name", "UGTools");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "UGTools");
            System.setProperty("apple.awt.application.appearance", "system");
            System.setProperty("flatlaf.useRoundedPopupBorder", "true");

            FlatDesktop.setAboutHandler(() -> {
                try {
                    AboutDialog dialog = new AboutDialog();

                    dialog.pack();
                    dialog.setVisible(true);
                } catch (Exception e2) {
                    log.error(ExceptionUtils.getStackTrace(e2));
                }
            });
            FlatDesktop.setPreferencesHandler(() -> {
                try {
                    SettingDialog dialog = new SettingDialog();

                    dialog.pack();
                    dialog.setVisible(true);
                } catch (Exception e2) {
                    log.error(ExceptionUtils.getStackTrace(e2));
                }
            });
            FlatDesktop.setQuitHandler(FlatDesktop.QuitResponse::performQuit);

        }

        FlatLaf.registerCustomDefaultsSource("themes");
        SwingUtilities.invokeLater(FlatJetBrainsMonoFont::install);

        Init.initTheme();

        // install inspectors
//        FlatInspector.install("ctrl shift alt X");
//        FlatUIDefaultsInspector.install("ctrl shift alt Y");

        mainFrame = new MainFrame();
        mainFrame.init();
        JPanel loadingPanel = new LoadingForm().getLoadingPanel();
        mainFrame.add(loadingPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if (config.isDefaultMaxWindow() || screenSize.getWidth() <= 1366) {
            // 低分辨率下自动最大化窗口
            mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }

        UpgradeUtil.smoothUpgrade();

        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Init.initGlobalFont();
        mainFrame.setContentPane(MainWindow.getInstance().getMainPanel());

        if (SystemUtil.isLinuxOs()) {
            tempDir = new File(SystemUtil.CONFIG_HOME + File.separator + "temp");
        } else {
            tempDir = new File(FileUtil.getTmpDirPath() + "UGTools");
        }
        if (!tempDir.exists()) {
            tempDir.mkdirs();
        }
        FileUtil.clean(tempDir);

        if (App.config.getRecentTabIndex() != 3 && MainWindow.getInstance().getTabbedPane().getTabCount() > App.config.getRecentTabIndex()) {
            MainWindow.getInstance().getTabbedPane().setSelectedIndex(App.config.getRecentTabIndex());
        }
        MainWindow.getInstance().init();
        Init.initAllTab();
        Init.initOthers();
        mainFrame.remove(loadingPanel);
//        Init.fontSizeGuide();
    }
}
