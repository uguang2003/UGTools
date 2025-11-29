package top.ug666.ug.tools.ui.form;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.fastjson2.JSON;
import top.ug666.ug.tools.bean.VersionSummary;
import top.ug666.ug.tools.ui.UiConsts;
import top.ug666.ug.tools.util.ScrollUtil;
import top.ug666.ug.tools.util.UpgradeUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * 关于页面
 *
 * @author UG
 */
@Getter
@Slf4j
public class AboutForm {

    private JPanel aboutPanel;
    private JScrollPane scrollPane;

    private static AboutForm aboutForm;

    private AboutForm() {
        initUI();
    }

    public static AboutForm getInstance() {
        if (aboutForm == null) {
            aboutForm = new AboutForm();
        }
        return aboutForm;
    }

    public static void init() {
        aboutForm = getInstance();
        ScrollUtil.smoothPane(aboutForm.getScrollPane());
        aboutForm.getAboutPanel().updateUI();
    }

    private void initUI() {
        // 主面板
        aboutPanel = new JPanel(new BorderLayout());
        aboutPanel.setPreferredSize(new Dimension(400, 300));

        // 滚动内容
        JPanel contentPanel = createContentPanel();
        scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        aboutPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createContentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(30, 50, 30, 50));

        // ========== 顶部 Logo 区域 ==========
        panel.add(createHeaderSection());
        panel.add(Box.createVerticalStrut(25));

        // ========== 分割线 ==========
        panel.add(createSeparator());
        panel.add(Box.createVerticalStrut(20));

        // ========== UGTools 功能介绍 ==========
        panel.add(createFeaturesCard());
        panel.add(Box.createVerticalStrut(15));

        // ========== 版本更新记录 ==========
        panel.add(createChangelogCard());
        panel.add(Box.createVerticalStrut(15));

        // ========== 作者信息卡片 ==========
        panel.add(createAuthorCard());
        panel.add(Box.createVerticalStrut(15));

        // ========== 链接卡片 ==========
//        panel.add(createLinksCard());
//        panel.add(Box.createVerticalStrut(15));

        // ========== 作品展示卡片 ==========
//        panel.add(createWorksCard());
//        panel.add(Box.createVerticalStrut(15));

        // ========== 致谢卡片 ==========
        panel.add(createThanksCard());
        panel.add(Box.createVerticalStrut(25));

        // ========== 底部版权 ==========
        panel.add(createFooter());

        return panel;
    }

    private JPanel createHeaderSection() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setOpaque(false);
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Logo
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

        // 应用名
        JLabel nameLabel = new JLabel("UGTools");
        nameLabel.setFont(new Font(nameLabel.getFont().getName(), Font.BOLD, 32));
        nameLabel.setForeground(new Color(0x4FC3F7));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 版本
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

        header.add(logoLabel);
        header.add(Box.createVerticalStrut(12));
        header.add(nameLabel);
        header.add(Box.createVerticalStrut(5));
        header.add(versionLabel);
        header.add(Box.createVerticalStrut(8));
        header.add(sloganLabel);

        return header;
    }

    private JPanel createAuthorCard() {
        JPanel card = createCardPanel("关于作者");

        addTextLine(card, "我是 UG，一个斗码大陆苦逼的三段码之气的少年。");
        card.add(Box.createVerticalStrut(10));
        addTextLine(card, "前端：Vue / React / TypeScript");
        addTextLine(card, "后端：Java / Node / Python");
        addTextLine(card, "游戏：Unity3D / Cocos3 / UE5");
        addTextLine(card, "运维：Docker / Linux / CI/CD");
        card.add(Box.createVerticalStrut(10));

        JLabel quoteLabel = new JLabel("「三十年河东，三十年河西，莫欺少年穷。」");
        quoteLabel.setFont(new Font(quoteLabel.getFont().getName(), Font.ITALIC, 13));
        quoteLabel.setForeground(new Color(0xFFCC00));
        quoteLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(quoteLabel);

        return card;
    }

    private JPanel createFeaturesCard() {
        JPanel card = createCardPanel("UGTools 功能介绍");

        addTextLine(card, "UGTools 是一款跨平台桌面开发者效率工具集");
        card.add(Box.createVerticalStrut(8));

        addTextLine(card, "【常用工具】");
        addTextLine(card, "  - 时间转换：时间戳与日期格式互转");
        addTextLine(card, "  - JSON格式化：JSON美化、压缩、校验");
        addTextLine(card, "  - 编码转换：Base64、URL、Unicode编解码");
        addTextLine(card, "  - 加密解密：MD5、SHA、AES、RSA等");
        addTextLine(card, "  - 正则测试：正则表达式在线测试");
        addTextLine(card, "  - 二维码：生成和解析二维码");
        card.add(Box.createVerticalStrut(6));

        addTextLine(card, "【开发辅助】");
        addTextLine(card, "  - 取色器：屏幕取色、颜色转换");
        addTextLine(card, "  - 翻译：多语言在线翻译");
        addTextLine(card, "  - Cron表达式：Cron生成与解析");
        addTextLine(card, "  - HTTP请求：接口调试工具");
        addTextLine(card, "  - 快捷便签：随手记录灵感");
        card.add(Box.createVerticalStrut(6));

        addTextLine(card, "【特性】");
        addTextLine(card, "  - 桌面优先，离线可用");
        addTextLine(card, "  - 数据本地存储，隐私安全");
        addTextLine(card, "  - 现代化 UI，多主题支持");
        addTextLine(card, "  - 跨平台：Windows / macOS / Linux");

        return card;
    }

    private JPanel createChangelogCard() {
        JPanel card = createCardPanel("版本更新记录");

        try {
            // 从 version_summary.json 读取版本信息
            String versionSummaryJsonContent = FileUtil.readString(
                    UiConsts.class.getResource("/version_summary.json"), CharsetUtil.UTF_8);
            versionSummaryJsonContent = versionSummaryJsonContent.replace("\n", "");
            VersionSummary versionSummary = JSON.parseObject(versionSummaryJsonContent, VersionSummary.class);

            List<VersionSummary.Version> versionList = versionSummary.getVersionDetailList();
            if (versionList != null && !versionList.isEmpty()) {
                // 显示最近的版本记录（最多显示5个）
                int displayCount = Math.min(versionList.size(), 5);
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
                    versionLabel.setForeground(new Color(0x4FC3F7));
                    versionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    card.add(versionLabel);
                    card.add(Box.createVerticalStrut(4));

                    // 版本日志
                    if (version.getLog() != null && !version.getLog().isEmpty()) {
                        String[] logLines = version.getLog().split("\n");
                        for (String line : logLines) {
                            if (!line.trim().isEmpty()) {
                                addTextLine(card, "  " + line.trim());
                            }
                        }
                    }

                    if (i < displayCount - 1) {
                        card.add(Box.createVerticalStrut(8));
                    }
                }
            }
        } catch (Exception e) {
            log.error("读取版本信息失败", e);
            addTextLine(card, "v" + UiConsts.APP_VERSION + " (当前版本)");
            addTextLine(card, "  基于 MooTool 改造的个人定制版");
        }

        return card;
    }

    private JPanel createLinksCard() {
        JPanel card = createCardPanel("相关链接");

        JPanel linksPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        linksPanel.setOpaque(false);
        linksPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        linksPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        linksPanel.add(createLinkButton("个人主页", "https://ug666.top/"));
        linksPanel.add(createLinkButton("UG博客", "https://blog.ug666.top/"));
        linksPanel.add(createLinkButton("GitHub", "https://github.com/uguang2003"));
        linksPanel.add(createLinkButton("联系邮箱", "mailto:uguang2003@email.com"));

        card.add(linksPanel);

        return card;
    }

    private JPanel createWorksCard() {
        JPanel card = createCardPanel("UG的宝藏");

        addTextLine(card, "探索更多有趣的项目和作品：");
        card.add(Box.createVerticalStrut(10));

        JPanel worksPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        worksPanel.setOpaque(false);
        worksPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        worksPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        worksPanel.add(createLinkButton("音乐播放器", "https://music.ug666.top/"));
        worksPanel.add(createLinkButton("AI聊天室", "https://ai.ug666.top/"));
        worksPanel.add(createLinkButton("地图世界", "https://map.ug666.top/"));
        worksPanel.add(createLinkButton("Unity游戏", "http://unity.ug666.top/"));

        card.add(worksPanel);

        return card;
    }

    private JPanel createThanksCard() {
        JPanel card = createCardPanel("致谢");

        String[][] thanks = {
                {"MooTool", "https://github.com/rememberber/MooTool"},
                {"FlatLaf", "https://github.com/JFormDesigner/FlatLaf"},
                {"Hutool", "https://hutool.cn/"},
                {"vscode-icons", "https://github.com/microsoft/vscode-icons"},
                {"Darcula", "https://github.com/bulenkov/Darcula"}
        };

        JPanel thanksPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 5));
        thanksPanel.setOpaque(false);
        thanksPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        for (String[] item : thanks) {
            JLabel link = createHyperlinkLabel(item[0], item[1]);
            thanksPanel.add(link);
        }

        card.add(thanksPanel);

        return card;
    }

    private JPanel createFooter() {
        JPanel footer = new JPanel();
        footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));
        footer.setOpaque(false);
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel copyrightLabel = new JLabel("© 2022 - 2025 UG. All rights reserved.");
        copyrightLabel.setFont(new Font(copyrightLabel.getFont().getName(), Font.PLAIN, 12));
        copyrightLabel.setForeground(new Color(0x666666));
        copyrightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel madeLabel = new JLabel("Made with by UG");
        madeLabel.setFont(new Font(madeLabel.getFont().getName(), Font.PLAIN, 12));
        madeLabel.setForeground(new Color(0x666666));
        madeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        footer.add(copyrightLabel);
        footer.add(Box.createVerticalStrut(5));
        footer.add(madeLabel);

        return footer;
    }

    // ========== 工具方法 ==========

    private JPanel createCardPanel(String title) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 80, 80), 1, true),
                new EmptyBorder(15, 20, 15, 20)
        ));
        card.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 15));
        titleLabel.setForeground(new Color(0x4FC3F7));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(12));

        return card;
    }

    private void addTextLine(JPanel panel, String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 13));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(label);
        panel.add(Box.createVerticalStrut(4));
    }

    private JButton createLinkButton(String text, String url) {
        JButton button = new JButton(text);
        button.setFont(new Font(button.getFont().getName(), Font.PLAIN, 12));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFocusPainted(false);
        button.addActionListener(e -> openUrl(url));
        return button;
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
            log.error("无法打开链接: " + url, ex);
        }
    }
}
