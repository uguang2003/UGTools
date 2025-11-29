package top.ug666.ug.tools.ui.dialog;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.fastjson2.JSON;
import com.formdev.flatlaf.util.SystemInfo;
import top.ug666.ug.tools.App;
import top.ug666.ug.tools.bean.VersionSummary;
import top.ug666.ug.tools.ui.UiConsts;
import top.ug666.ug.tools.util.ComponentUtil;
import top.ug666.ug.tools.util.ScrollUtil;
import top.ug666.ug.tools.util.SystemUtil;
import top.ug666.ug.tools.util.UpgradeUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * 关于对话框
 *
 * @author UG
 */
public class AboutDialog extends JDialog {

    public AboutDialog() {
        super(App.mainFrame, "关于 UGTools");
        ComponentUtil.setPreferSizeAndLocateToCenter(this, 0.42, 0.72);
        setModal(true);

        // macOS 适配
        if (SystemUtil.isMacOs() && SystemInfo.isMacFullWindowContentSupported) {
            this.getRootPane().putClientProperty("apple.awt.fullWindowContent", true);
            this.getRootPane().putClientProperty("apple.awt.transparentTitleBar", true);
            this.getRootPane().putClientProperty("apple.awt.fullscreenable", true);
            this.getRootPane().putClientProperty("apple.awt.windowTitleVisible", false);
        }

        // 创建主面板
        JPanel mainPanel = createMainPanel();
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        ScrollUtil.smoothPane(scrollPane);

        setContentPane(scrollPane);

        // 关闭事件
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // ESC 关闭
        getRootPane().registerKeyboardAction(
                e -> dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW
        );
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(40, 50, 40, 50));

        // ========== Logo 区域 ==========
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.Y_AXIS));
        logoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoPanel.setOpaque(false);

        // Logo 图片 - 使用高清图标
        JLabel logoLabel = new JLabel();
        Image scaledLogo = UiConsts.IMAGE_LOGO_512.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(scaledLogo));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoLabel.setToolTipText("访问 ug666.top");
        logoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openUrl("https://ug666.top/");
            }
        });

        // 应用名称
        JLabel nameLabel = new JLabel("UGTools");
        nameLabel.setFont(new Font(nameLabel.getFont().getName(), Font.BOLD, 32));
        nameLabel.setForeground(new Color(0x4FC3F7));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 版本号
        JLabel versionLabel = new JLabel(UiConsts.APP_VERSION);
        versionLabel.setFont(new Font(versionLabel.getFont().getName(), Font.PLAIN, 14));
        versionLabel.setForeground(new Color(0x888888));
        versionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        versionLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        versionLabel.setToolTipText("点击检查更新");
        versionLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ThreadUtil.execute(() -> UpgradeUtil.checkUpdate(false));
            }
        });

        // Slogan
        JLabel sloganLabel = new JLabel("开发者桌面效率工具集");
        sloganLabel.setFont(new Font(sloganLabel.getFont().getName(), Font.PLAIN, 14));
        sloganLabel.setForeground(new Color(0xAAAAAA));
        sloganLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        logoPanel.add(logoLabel);
        logoPanel.add(Box.createVerticalStrut(15));
        logoPanel.add(nameLabel);
        logoPanel.add(Box.createVerticalStrut(6));
        logoPanel.add(versionLabel);
        logoPanel.add(Box.createVerticalStrut(8));
        logoPanel.add(sloganLabel);

        panel.add(logoPanel);
        panel.add(Box.createVerticalStrut(25));

        // ========== 分割线 ==========
        panel.add(createSeparator());
        panel.add(Box.createVerticalStrut(20));

        // ========== 作者信息卡片 ==========
        JPanel authorCard = createInfoCard("关于作者", new String[]{
                "我是 UG，一个斗码大陆苦逼的三段码之气的少年。",
                "",
                "前端：Vue / React / TypeScript",
                "后端：Java / Node / Python",
                "游戏：Unity3D / Cocos3 / UE5",
                "",
                "「三十年河东，三十年河西，莫欺少年穷。」"
        });
        panel.add(authorCard);
        panel.add(Box.createVerticalStrut(15));

        // ========== 项目信息卡片 ==========
        JPanel projectCard = createInfoCard("关于项目", new String[]{
                "UGTools 是一款跨平台桌面开发者效率工具集",
                "",
                "桌面优先，离线可用",
                "数据本地存储，隐私安全",
                "20+ 开发者常用工具集成",
                "现代化 UI，多主题支持"
        });
        panel.add(projectCard);
        panel.add(Box.createVerticalStrut(15));

        // ========== 版本更新记录 ==========
        JPanel changelogCard = createChangelogCard();
        panel.add(changelogCard);
        panel.add(Box.createVerticalStrut(15));

        // ========== 链接卡片 ==========
//        JPanel linksCard = createLinksCard();
//        panel.add(linksCard);
//        panel.add(Box.createVerticalStrut(15));

        // ========== 致谢卡片 ==========
        JPanel thanksCard = createThanksCard();
        panel.add(thanksCard);
        panel.add(Box.createVerticalStrut(25));

        // ========== 底部版权 ==========
        JLabel copyrightLabel = new JLabel("© 2022 - 2025 UG. All rights reserved.");
        copyrightLabel.setFont(new Font(copyrightLabel.getFont().getName(), Font.PLAIN, 12));
        copyrightLabel.setForeground(new Color(0x666666));
        copyrightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(copyrightLabel);

        return panel;
    }

    private JPanel createInfoCard(String title, String[] contents) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 80, 80), 1, true),
                new EmptyBorder(15, 20, 15, 20)
        ));
        card.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        // 标题
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 15));
        titleLabel.setForeground(new Color(0x4FC3F7));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(10));

        // 内容
        for (String content : contents) {
            if (content.isEmpty()) {
                card.add(Box.createVerticalStrut(6));
            } else {
                JLabel label = new JLabel(content);
                label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 13));
                label.setAlignmentX(Component.LEFT_ALIGNMENT);
                card.add(label);
                card.add(Box.createVerticalStrut(3));
            }
        }

        return card;
    }

    private JPanel createChangelogCard() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 80, 80), 1, true),
                new EmptyBorder(15, 20, 15, 20)
        ));
        card.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        JLabel titleLabel = new JLabel("版本更新记录");
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 15));
        titleLabel.setForeground(new Color(0x4FC3F7));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(10));

        try {
            // 从 version_summary.json 读取版本信息
            String versionSummaryJsonContent = FileUtil.readString(
                    UiConsts.class.getResource("/version_summary.json"), CharsetUtil.UTF_8);
            versionSummaryJsonContent = versionSummaryJsonContent.replace("\n", "");
            VersionSummary versionSummary = JSON.parseObject(versionSummaryJsonContent, VersionSummary.class);

            List<VersionSummary.Version> versionList = versionSummary.getVersionDetailList();
            if (versionList != null && !versionList.isEmpty()) {
                // 显示最近的版本记录（最多显示3个）
                int displayCount = Math.min(versionList.size(), 3);
                for (int i = 0; i < displayCount; i++) {
                    VersionSummary.Version version = versionList.get(i);

                    // 版本标题
                    String versionTitle = version.getVersion();
                    if (version.getTitle() != null && !version.getTitle().isEmpty()) {
                        versionTitle += " - " + version.getTitle();
                    }
                    if (i == 0) {
                        versionTitle += " (当前版本)";
                    }

                    JLabel versionLabel = new JLabel(versionTitle);
                    versionLabel.setFont(new Font(versionLabel.getFont().getName(), Font.BOLD, 13));
                    versionLabel.setForeground(new Color(0x81D4FA));
                    versionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    card.add(versionLabel);
                    card.add(Box.createVerticalStrut(4));

                    // 版本日志
                    if (version.getLog() != null && !version.getLog().isEmpty()) {
                        String[] logLines = version.getLog().split("\n");
                        for (String line : logLines) {
                            if (!line.trim().isEmpty()) {
                                JLabel logLabel = new JLabel("  " + line.trim());
                                logLabel.setFont(new Font(logLabel.getFont().getName(), Font.PLAIN, 12));
                                logLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                                card.add(logLabel);
                                card.add(Box.createVerticalStrut(2));
                            }
                        }
                    }

                    if (i < displayCount - 1) {
                        card.add(Box.createVerticalStrut(8));
                    }
                }
            }
        } catch (Exception e) {
            JLabel fallbackLabel = new JLabel("v" + UiConsts.APP_VERSION + " (当前版本)");
            fallbackLabel.setFont(new Font(fallbackLabel.getFont().getName(), Font.PLAIN, 13));
            fallbackLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            card.add(fallbackLabel);
        }

        return card;
    }

    private JPanel createLinksCard() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 80, 80), 1, true),
                new EmptyBorder(15, 20, 15, 20)
        ));
        card.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        JLabel titleLabel = new JLabel("相关链接");
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 15));
        titleLabel.setForeground(new Color(0x4FC3F7));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(12));

        // 主链接面板
        JPanel linksPanel = new JPanel(new GridLayout(2, 2, 8, 8));
        linksPanel.setOpaque(false);
        linksPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        linksPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));

        linksPanel.add(createLinkButton("个人主页", "https://ug666.top/"));
        linksPanel.add(createLinkButton("UG博客", "https://blog.ug666.top/"));
        linksPanel.add(createLinkButton("GitHub", "https://github.com/uguang2003"));
        linksPanel.add(createLinkButton("联系邮箱", "mailto:uguang2003@email.com"));

        card.add(linksPanel);
        card.add(Box.createVerticalStrut(12));

        // 更多作品标签
        JLabel moreLabel = new JLabel("更多作品：");
        moreLabel.setFont(new Font(moreLabel.getFont().getName(), Font.BOLD, 13));
        moreLabel.setForeground(new Color(0xBBBBBB));
        moreLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(moreLabel);
        card.add(Box.createVerticalStrut(8));

        // 作品链接面板
        JPanel morePanel = new JPanel(new GridLayout(2, 2, 8, 8));
        morePanel.setOpaque(false);
        morePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        morePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));

        morePanel.add(createLinkButton("音乐播放器", "https://music.ug666.top/"));
        morePanel.add(createLinkButton("AI聊天室", "https://ai.ug666.top/"));
        morePanel.add(createLinkButton("地图世界", "https://map.ug666.top/"));
        morePanel.add(createLinkButton("游戏作品", "http://unity.ug666.top/"));

        card.add(morePanel);

        return card;
    }

    private JButton createLinkButton(String text, String url) {
        JButton button = new JButton(text);
        button.setFont(new Font(button.getFont().getName(), Font.PLAIN, 12));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFocusPainted(false);
        button.addActionListener(e -> openUrl(url));
        return button;
    }

    private JPanel createThanksCard() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 80, 80), 1, true),
                new EmptyBorder(15, 20, 15, 20)
        ));
        card.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        JLabel titleLabel = new JLabel("致谢");
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 15));
        titleLabel.setForeground(new Color(0x4FC3F7));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(10));

        // 致谢列表
        String[][] thanks = {
                {"MooTool", "https://github.com/rememberber/MooTool"},
                {"FlatLaf", "https://github.com/JFormDesigner/FlatLaf"},
                {"Hutool", "https://hutool.cn/"},
                {"vscode-icons", "https://github.com/microsoft/vscode-icons"}
        };

        JPanel thanksPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        thanksPanel.setOpaque(false);
        thanksPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        for (String[] item : thanks) {
            JLabel link = createHyperlinkLabel(item[0], item[1]);
            thanksPanel.add(link);
        }

        card.add(thanksPanel);

        return card;
    }

    private JLabel createHyperlinkLabel(String text, String url) {
        JLabel link = new JLabel("<html><u>" + text + "</u></html>");
        link.setFont(new Font(link.getFont().getName(), Font.PLAIN, 13));
        link.setForeground(new Color(0x4FC3F7));
        link.setCursor(new Cursor(Cursor.HAND_CURSOR));
        link.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openUrl(url);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                link.setForeground(new Color(0x81D4FA));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                link.setForeground(new Color(0x4FC3F7));
            }
        });
        return link;
    }

    private JSeparator createSeparator() {
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        separator.setForeground(new Color(60, 60, 60));
        return separator;
    }

    private void openUrl(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException ex) {
            JOptionPane.showMessageDialog(this,
                    "无法打开链接：" + url,
                    "错误",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
