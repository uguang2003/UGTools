# UGTools 项目介绍

> 基于 MooTool 改造的个人定制版桌面开发者效率工具集

## 📋 项目概述

UGTools 是一款跨平台的桌面端开发者效率工具集，采用 Java Swing 构建，使用 FlatLaf 主题框架提供现代化的 UI 体验。项目基于开源项目 MooTool 改造，去除了原有的推广与统计功能，保留并优化了核心工具模块，打造成个人自用版本。

### 主要特性
- 🎨 现代化 UI 设计，支持多种主题切换（深色/浅色/跟随系统）
- 💾 本地 SQLite 数据库存储，数据完全离线
- 🚀 JVM 参数优化，使用 ZGC 垃圾回收器
- 📦 支持打包成 Windows EXE / macOS App / Linux 可执行文件
- 🔧 20+ 开发者常用工具集成

---

## 🛠️ 技术栈

### 核心依赖

| 技术 | 版本 | 说明 |
|------|------|------|
| **Java** | 21 | 核心开发语言（需要 JDK 21+） |
| **Maven** | 3.x | 项目构建与依赖管理 |
| **FlatLaf** | 3.6.2 | 现代化 Swing UI 主题框架 |
| **SQLite** | 3.43.0 | 嵌入式数据库 |
| **MyBatis** | 3.5.13 | ORM 持久层框架 |
| **Hutool** | 5.8.22 | Java 工具类库 |
| **OkHttp** | 4.11.0 | HTTP 客户端 |
| **ZXing** | 3.5.3 | 二维码生成与解析 |
| **RSyntaxTextArea** | 3.6.0 | 代码语法高亮编辑器 |
| **Logback** | 1.4.11 | 日志框架 |

### 其他重要依赖

| 技术 | 版本 | 说明 |
|------|------|------|
| **Groovy** | 4.0.15 | 动态语言支持（Java 控制台） |
| **JavaParser** | 3.26.2 | Java 代码解析与格式化 |
| **cron-utils** | 9.2.1 | Cron 表达式解析 |
| **iTextPDF** | 5.5.1 | PDF 处理 |
| **SnakeYAML** | 1.23 | YAML 解析 |
| **FastJSON** | 2.0.40 | JSON 处理 |
| **Jackson** | 2.13.3 | JSON/XML 转换 |
| **java-diff-utils** | 4.16 | 文本差异对比 |
| **JNA** | 5.13.0 | 本地系统调用 |
| **OSHI** | 6.4.6 | 系统信息获取 |

---

## 📂 项目结构

```
UGTools/
├── pom.xml                          # Maven 配置文件（含打包配置）
├── readme.md                        # 功能列表说明
├── PROJECT_INTRO.md                 # 项目详细介绍（本文件）
├── UGTools.l4j.ini                  # Launch4j 配置（可选）
│
├── assets/                          # 资源文件
│   ├── icons/                       # 多平台图标资源
│   │   ├── android/res/             # Android 各分辨率图标
│   │   ├── ios/                     # iOS 图标
│   │   ├── macos/                   # macOS .icns 图标
│   │   ├── web/                     # Web favicon
│   │   └── windows/                 # Windows .ico 图标
│   ├── logo/                        # Logo 原始资源
│   ├── linux/                       # Linux 桌面图标
│   ├── mac/                         # macOS 特定资源
│   ├── material/                    # 素材文件
│   └── windows/                     # Windows 特定资源
│
├── lib/                             # 第三方本地库（如需要）
│
├── src/main/
│   ├── java/top/
│   │   ├── jthemedetecor/           # 系统主题检测器（跟随系统深浅色）
│   │   └── ug666/ug/tools/
│   │       ├── App.java             # ⭐ 应用程序主入口
│   │       ├── bean/                # 数据实体类（MyBatis 映射）
│   │       ├── dao/                 # 数据访问层接口
│   │       ├── domain/              # 领域模型
│   │       ├── enums/               # 枚举定义
│   │       ├── service/             # 业务服务层
│   │       ├── ui/                  # 用户界面
│   │       │   ├── component/       # 自定义 Swing 组件
│   │       │   ├── dialog/          # 弹出对话框
│   │       │   ├── form/            # 表单界面（含 .form GUI 设计文件）
│   │       │   │   └── func/        # 各功能模块表单（21个功能）
│   │       │   ├── frame/           # 主窗口框架
│   │       │   ├── listener/        # 事件监听器
│   │       │   │   └── func/        # 各功能模块监听器
│   │       │   ├── FuncConsts.java  # 功能常量定义
│   │       │   ├── Init.java        # 初始化类（主题、字体等）
│   │       │   ├── Style.java       # 样式常量
│   │       │   └── UiConsts.java    # UI 常量
│   │       └── util/                # 工具类
│   │           ├── ConfigUtil.java      # 配置管理
│   │           ├── ConfigBaseUtil.java  # 配置基类
│   │           ├── MybatisUtil.java     # MyBatis 工具
│   │           ├── SystemUtil.java      # 系统工具
│   │           └── UpgradeUtil.java     # 版本升级工具
│   │
│   └── resources/
│       ├── db_init.sql              # 数据库初始化 SQL
│       ├── mybatis-config.xml       # MyBatis 配置
│       ├── logback.xml              # 日志配置
│       ├── generatorConfig.xml      # MyBatis Generator 配置
│       ├── version_summary.json     # 版本信息
│       ├── icon/                    # 应用内图标资源
│       ├── mapper/                  # MyBatis Mapper XML（13个）
│       ├── themes/                  # FlatLaf 主题属性文件
│       └── upgrade/                 # 数据库版本升级脚本
│
└── target/                          # Maven 构建输出目录
```

---

## 🎯 功能模块

### 📝 随手记 (QuickNote)
- 多语言语法高亮（支持 50+ 种编程语言）
- SQL/JSON/Java 代码格式化
- 文本快捷操作（去空格、去空行、大小写转换、驼峰命名转换等）
- 导出/批量导出，全局查找
- 自定义字体、字号、列表颜色

### ⏰ 时间转换 (TimeConvert)
- 时间戳与日期时间互转（毫秒/秒）
- 历史记录
- 大屏时钟显示

### 📊 JSON 工具 (JsonBeauty)
- JSON 格式化/压缩
- JSON ↔ XML 互转
- JavaBean ↔ JSON 互转
- JsonPath 查询
- 可视化 JsonPath 获取

### 🌐 翻译 (Translation)
- 中英互译
- 自动语言检测

### 🖥️ Host 管理 (Host)
- Host 文件格式化与语法高亮
- 本机 Host 管理与查看
- Host 导入/导出

### 🔗 HTTP 请求 (HttpRequest)
- 支持 GET/POST/PUT/DELETE/HEAD/PATCH/OPTIONS
- Header/Body 格式化
- 请求管理与历史记录

### 🔄 编码转换 (EnCode)
- Native ↔ Unicode
- URL 编码/解码
- 十六进制转换

### 📱 二维码 (QrCode)
- 二维码生成（自定义尺寸、纠错等级、Logo）
- 二维码解析
- 历史记录

### 🔐 加解密/随机 (Crypto)
- 对称加密：AES、DES
- 非对称加密：RSA
- 摘要算法：MD5、SHA1、SHA256、SHA384、SHA512
- Base64/Base32 编码解码
- UUID 生成、随机字符串、复杂密码生成

### 🧮 计算器 (Calculator)
- 四则运算
- 进制转换
- 最大公约数/最小公倍数
- 排列组合计算

### 🌍 网络工具 (Net)
- IP/域名查询
- netstat、ping
- IPv4 ↔ Long 转换
- 刷新 DNS

### 🎨 调色板 (ColorBoard/ColorPicker)
- 主题颜色/标准颜色
- 屏幕取色器
- 颜色格式转换
- 颜色收藏

### 🖼️ 图片助手 (Image)
- 本地图床
- 图片 Base64 编码/解码

### ⏱️ Cron 表达式 (Cron)
- Cron 表达式生成/解析/校验
- Cron 表达式收藏
- 常用 Cron 表达式模板

### 📐 正则表达式 (Regex)
- 正则匹配测试
- 正则收藏
- 常用正则模板

### ☕ Java 控制台 (JavaConsole)
- Java/Groovy 代码格式化与高亮
- Java/Groovy 代码解释执行

### 📄 格式化工具 (FileReformatting)
- Nginx 配置文件格式化
- XML/HTML/Java 格式化

### 📑 PDF 工具 (Pdf)
- PDF 拆分
- PDF 合并

### ⚙️ 环境变量 (Variables)
- 系统环境变量查看
- Java 环境变量查看

### 🔧 配置文件转换 (YmlProperties)
- Properties ↔ YAML 互转

### 📊 文本对比 (TextDiff)
- 并排对比
- 统一差异视图
- 差异复制

---

## 💾 数据存储详解

### 存储位置

所有用户数据和配置都存储在用户主目录下的 `.UGTools` 文件夹中：

| 操作系统 | 路径 |
|---------|------|
| **Windows** | `C:\Users\<用户名>\.UGTools\` |
| **macOS** | `/Users/<用户名>/.UGTools/` |
| **Linux** | `/home/<用户名>/.UGTools/` |

### 目录结构

```
~/.UGTools/
├── UGTools.db                    # SQLite 数据库文件（核心数据）
├── config/
│   └── config.setting            # 应用配置文件（Hutool Setting 格式）
├── images/                       # 本地图床存储目录
├── logs/                         # 日志文件目录
└── temp/                         # 临时文件目录（Linux 专用）
```

### SQLite 数据库表结构

数据库文件 `UGTools.db` 包含以下数据表：

| 表名 | 说明 | 主要字段 |
|-----|------|---------|
| `t_quick_note` | 随手记 | id, name, content, create_time, modified_time |
| `t_json_beauty` | JSON 格式化记录 | id, name, content, create_time, modified_time |
| `t_host` | Host 配置 | id, name, content, create_time, modified_time |
| `t_msg_http` | HTTP 请求记录 | id, msg_name, method, url, params, headers, cookies, body, body_type |
| `t_qr_code` | 二维码历史 | id, content, create_time, modified_time |
| `t_func_content` | 功能内容缓存 | id, func, content, remark |
| `t_favorite_color_list` | 颜色收藏列表 | id, title, remark |
| `t_favorite_color_item` | 颜色收藏项 | id, list_id, name, value, sort_num |
| `t_favorite_cron_list` | Cron 收藏列表 | id, title, remark |
| `t_favorite_cron_item` | Cron 收藏项 | id, list_id, name, value |
| `t_favorite_regex_list` | 正则收藏列表 | id, title, remark |
| `t_favorite_regex_item` | 正则收藏项 | id, list_id, name, value |
| `t_http_request_history` | HTTP 请求历史 | 请求记录 |
| `doc` | 文本对比文档 | doc_id, name |
| `doc_revision` | 文档版本 | rev_id, doc_id, created_at, content |
| `doc_diff_cache` | 差异缓存 | from_rev_id, to_rev_id, unified |

### 配置文件格式

`config.setting` 使用 Hutool 的 Setting 格式（类似 INI）：

```ini
[setting.common]
autoCheckUpdate=true
recentTabIndex=0
beforeVersion=v0.0.1

[setting.normal]
defaultMaxWindow=false
unifiedBackground=true
themeColorFollowSystem=true

[setting.appearance]
theme=Flat macOS Dark
font=微软雅黑
fontSize=12
accentColor=blue

[setting.quickNote]
fontName=JetBrains Mono
fontSize=14

[setting.http]
useProxy=false
proxyHost=
proxyPort=
```

---

## 🚀 构建与打包

### 环境要求
- **JDK 21+**（推荐使用 Eclipse Temurin 或 Oracle JDK）
- **Maven 3.6+**
- **IntelliJ IDEA**（推荐，支持 .form GUI 设计器）

### 开发环境运行

```bash
# 克隆项目后
cd UGTools

# 编译运行
mvn clean compile exec:java -Dexec.mainClass="top.ug666.ug.tools.App"
```

或在 IDE 中直接运行 `App.java` 的 main 方法。

### 构建 JAR 包

```bash
mvn clean package -DskipTests
```

构建产物位于 `target/UGTools-0.0.1.jar`

### 运行 JAR

```bash
java -Dswing.aatext=true \
     -XX:+UseZGC -XX:+ZGenerational -XX:-ZUncommit \
     -Xms128m -Xmx512m \
     --add-exports jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED \
     --add-exports jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED \
     --add-exports jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED \
     --add-exports jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED \
     --add-exports jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED \
     -jar target/UGTools-0.0.1.jar
```

---

## 📦 打包成可执行文件（EXE / App / 安装包）

项目使用 **JavaPackager** 插件进行跨平台打包，配置位于 `pom.xml`。

### 打包命令

```bash
# 完整打包（会根据 pom.xml 配置打包）
mvn clean package -DskipTests
```

### 打包配置说明 (pom.xml)

```xml
<plugin>
    <groupId>io.github.fvarrui</groupId>
    <artifactId>javapackager</artifactId>
    <version>1.7.5</version>
    <configuration>
        <!-- 打包 JRE -->
        <bundleJre>true</bundleJre>
        <!-- 主类 -->
        <mainClass>top.ug666.ug.tools.App</mainClass>
        <!-- 生成安装程序 -->
        <generateInstaller>true</generateInstaller>
        <!-- JVM 参数 -->
        <vmArgs>
            -Dswing.aatext=true 
            -XX:+UseZGC -XX:+ZGenerational -XX:-ZUncommit 
            -Xms128m -Xmx512m 
            --add-exports jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED
            ...
        </vmArgs>
    </configuration>
</plugin>
```

### Windows 打包（生成 EXE + 安装程序）

取消 pom.xml 中 Windows 打包配置的注释：

```xml
<execution>
    <id>bundling-for-windows</id>
    <phase>package</phase>
    <goals>
        <goal>package</goal>
    </goals>
    <configuration>
        <platform>windows</platform>
        <createZipball>true</createZipball>
        <administratorRequired>true</administratorRequired>
        <winConfig>
            <generateSetup>true</generateSetup>      <!-- Inno Setup 安装程序 -->
            <generateMsi>true</generateMsi>          <!-- MSI 安装程序 -->
            <setupMode>installForAllUsers</setupMode>
            <createDesktopIconTask>true</createDesktopIconTask>
        </winConfig>
    </configuration>
</execution>
```

**Windows 打包额外依赖：**
- [Inno Setup](https://jrsoftware.org/isinfo.php) - 生成 Setup.exe
- [WiX Toolset](https://wixtoolset.org/) - 生成 MSI 安装包

### macOS 打包（生成 .app）

当前 pom.xml 默认启用了 macOS 打包：

```xml
<execution>
    <id>bundling-for-mac</id>
    <phase>package</phase>
    <goals>
        <goal>package</goal>
    </goals>
    <configuration>
        <platform>mac</platform>
        <createTarball>true</createTarball>
        <macConfig>
            <macStartup>UNIVERSAL</macStartup>  <!-- 支持 Intel + Apple Silicon -->
        </macConfig>
    </configuration>
</execution>
```

构建后产物：`target/UGTools.app` 和 `target/UGTools-0.0.1-mac.tar.gz`

### Linux 打包

取消 pom.xml 中 Linux 配置的注释即可。

### 打包输出目录

```
target/
├── UGTools-0.0.1.jar           # 可执行 JAR
├── UGTools/                     # 打包后的应用目录
│   ├── UGTools.exe             # Windows 可执行文件
│   ├── UGTools.app/            # macOS 应用程序
│   ├── jre/                    # 捆绑的 JRE
│   └── ...
├── UGTools-0.0.1-windows.zip   # Windows 便携版
├── UGTools-0.0.1-mac.tar.gz    # macOS 压缩包
└── UGTools_Setup.exe           # Windows 安装程序（如启用）
```

---

## 🎨 主题与外观

### 支持的主题

项目集成了 FlatLaf 及其 IntelliJ 主题扩展：

| 主题名称 | 类型 | 说明 |
|---------|------|------|
| Flat Light | 浅色 | FlatLaf 默认浅色 |
| Flat Dark | 深色 | FlatLaf 默认深色 |
| Flat Darcula | 深色 | IntelliJ Darcula 风格 |
| Flat IntelliJ | 浅色 | IntelliJ 默认浅色 |
| Flat macOS Light | 浅色 | macOS 风格浅色 |
| Flat macOS Dark | 深色 | macOS 风格深色 |
| Monokai Pro | 深色 | Monokai 配色 |
| One Dark | 深色 | Atom One Dark |
| GitHub Dark | 深色 | GitHub 深色主题 |
| Dark Purple | 深色 | 紫色主题 |
| 系统默认 | - | 使用系统原生外观 |

### 主题跟随系统

启用后会自动检测系统深浅色模式切换：
- 使用 `jthemedetecor` 包检测系统主题
- Windows/macOS/Linux 均支持

### 自定义强调色

支持以下强调色：
- blue（默认）
- purple
- red
- orange
- yellow
- green

---

## ⚙️ 核心代码说明

### 应用入口 - App.java

```java
package top.ug666.ug.tools;

@Slf4j
public class App {
    // 全局配置实例
    public static ConfigUtil config = ConfigUtil.getInstance();
    
    // 主窗口
    public static MainFrame mainFrame;
    
    // MyBatis SqlSession（全局单例）
    public static SqlSession sqlSession = MybatisUtil.getSqlSession();
    
    // 系统托盘
    public static SystemTray tray;
    public static TrayIcon trayIcon;
    
    // 临时目录
    public static File tempDir = null;

    public static void main(String[] args) {
        // 1. macOS 特殊设置（菜单栏、应用名等）
        // 2. 注册自定义主题源
        // 3. 初始化主题
        // 4. 创建主窗口
        // 5. 执行平滑升级
        // 6. 初始化全局字体
        // 7. 初始化所有功能标签页
    }
}
```

### 配置管理 - ConfigUtil.java

单例模式，基于 Hutool 的 Setting：

```java
public class ConfigUtil extends ConfigBaseUtil {
    private static ConfigUtil configUtil = new ConfigUtil();
    
    // 配置项示例
    public String getTheme() { ... }
    public void setTheme(String theme) { ... }
    public int getFontSize() { ... }
    public void setFontSize(int size) { ... }
    
    // 保存配置到文件
    public void save() { ... }
}
```

### 数据库工具 - MybatisUtil.java

```java
public class MybatisUtil {
    // 数据库文件路径
    private static File dbFile = new File(
        SystemUtil.CONFIG_HOME + File.separator + "UGTools.db"
    );
    
    // 获取 SqlSession（懒加载单例）
    public static SqlSession getSqlSession() {
        if (sqlSession == null) {
            // 1. 检查数据库文件是否存在，不存在则初始化
            // 2. 读取 mybatis-config.xml
            // 3. 创建 SqlSessionFactory
            // 4. 执行表初始化
        }
        return sqlSession;
    }
    
    // 初始化数据库（执行 db_init.sql）
    public static void initDbFile() { ... }
}
```

### 系统工具 - SystemUtil.java

```java
public class SystemUtil {
    // 用户主目录
    private static final String USER_HOME = System.getProperty("user.home");
    
    // 配置目录
    public static final String CONFIG_HOME = USER_HOME + File.separator + ".UGTools";
    
    // 日志目录
    public static final String LOG_DIR = CONFIG_HOME + File.separator + "logs";
    
    // 系统检测方法
    public static boolean isMacOs() { ... }
    public static boolean isWindowsOs() { ... }
    public static boolean isLinuxOs() { ... }
}
```

### UI 初始化 - Init.java

```java
public class Init {
    // 初始化主题
    public static void initTheme() {
        // 根据配置选择主题
        // 支持跟随系统深浅色
    }
    
    // 初始化全局字体
    public static void initGlobalFont() {
        // 根据系统和配置设置字体
    }
    
    // 初始化所有功能标签页
    public static void initAllTab() {
        ThreadUtil.execute(QuickNoteForm::init);
        ThreadUtil.execute(TimeConvertForm::init);
        // ... 其他功能模块初始化
    }
}
```

---

## 🔧 开发指南

### 添加新功能模块

1. **创建 Form 类**
   - 在 `ui/form/func/` 下创建 `XXXForm.java` 和 `XXXForm.form`（IDEA GUI Designer）

2. **创建 Listener 类**
   - 在 `ui/listener/func/` 下创建 `XXXListener.java`

3. **注册到主窗口**
   - 在 `MainWindow.form` 中添加 Tab
   - 在 `Init.initAllTab()` 中添加初始化调用

4. **如需数据库**
   - 在 `db_init.sql` 添加建表语句
   - 创建对应的 Bean、DAO、Mapper

### IntelliJ IDEA GUI Designer

项目使用 IDEA 的 GUI Designer 设计界面：
- `.form` 文件是 GUI 设计文件
- `.java` 文件是生成的代码
- 需要在 IDEA 中安装 GUI Designer 插件
- 设置：File → Settings → Editor → GUI Designer → Generate GUI into: Java source code

### 代码风格

- 使用 Lombok 简化代码（@Slf4j, @Data, @Getter 等）
- DAO 层使用 MyBatis Mapper
- 工具类使用静态方法
- UI 事件处理在 Listener 类中

---

## 📝 版本升级机制

### 数据库升级

升级脚本位于 `resources/upgrade/` 目录：

```
upgrade/
├── 1.sql      # 版本 1 的升级脚本
├── 4.sql      # 版本 4 的升级脚本
├── 12.sql     # 版本 12 的升级脚本
└── ...
```

`UpgradeUtil.java` 会根据版本号自动执行对应的升级脚本。

### 版本信息

`version_summary.json` 记录版本信息：

```json
{
  "currentVersion": "v0.0.0",
  "versionIndex": {
    "v0.0.0": "0"
  },
  "versionDetailList": [
    {
      "version": "v0.0.0",
      "title": "基础版本",
      "log": "基础版本\n"
    }
  ]
}
```

---

## 🐛 常见问题

### Q: 启动报错 "module jdk.compiler does not export..."

A: 需要添加 JVM 参数：
```
--add-exports jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED
--add-exports jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED
--add-exports jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED
--add-exports jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED
--add-exports jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED
```

### Q: 如何迁移数据到新电脑？

A: 复制整个 `~/.UGTools/` 目录到新电脑即可。

### Q: 如何备份数据？

A: 备份以下文件：
- `~/.UGTools/UGTools.db` - 数据库
- `~/.UGTools/config/config.setting` - 配置
- `~/.UGTools/images/` - 本地图床图片

### Q: 如何修改数据存储位置？

A: 在设置中可以配置 `dbFilePath` 指向自定义路径。

---

## 🙏 致谢

- [MooTool](https://github.com/rememberber/MooTool) - 原项目
- [Hutool](http://hutool.cn/) - Java 工具类库
- [FlatLaf](https://github.com/JFormDesigner/FlatLaf) - 现代化 Swing 主题
- [vscode-icons](https://github.com/microsoft/vscode-icons) - 图标资源
- [iconfont](https://www.iconfont.cn/) - 图标资源

---

**项目地址**: `https://ug666.top/UGTools`  
**包名**: `top.ug666.ug.tools`  
**主类**: `top.ug666.ug.tools.App`  
**当前版本**: v0.0.1  
**最后更新**: 2025年11月29日
