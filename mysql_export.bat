@echo off
chcp 65001 >nul 2>&1
cls
echo.
echo ========================================
echo        MySQL数据库导出工具
echo ========================================
echo.

:input_database
set "db_name="
set /p db_name=请输入数据库名: 
if "%db_name%"=="" (
    echo 数据库名不能为空，请重新输入！
    echo.
    goto input_database
)

:input_username
set "user_name="
set /p user_name=请输入用户名（默认root）: 
if "%user_name%"=="" set user_name=root

:input_host
set "host_name="
set /p host_name=请输入主机地址（默认localhost）: 
if "%host_name%"=="" set host_name=localhost

:input_port
set "port_num="
set /p port_num=请输入端口号（默认3306）: 
if "%port_num%"=="" set port_num=3306

echo.
echo ========================================
echo 确认导出信息:
echo ========================================
echo 数据库: %db_name%
echo 用户名: %user_name%
echo 主机: %host_name%
echo 端口: %port_num%
echo.

set "confirm="
set /p confirm=确认导出？(y/n): 
if /i not "%confirm%"=="y" (
    echo 取消导出
    goto end
)

:: 创建导出目录
if not exist exports mkdir exports

:: 生成时间戳文件名
for /f "tokens=2 delims==" %%i in ('wmic OS Get localdatetime /value') do set datetime=%%i
set year=%datetime:~0,4%
set month=%datetime:~4,2%
set day=%datetime:~6,2%
set hour=%datetime:~8,2%
set minute=%datetime:~10,2%
set second=%datetime:~12,2%
set timestamp=%year%%month%%day%_%hour%%minute%%second%

set output_file=exports\%db_name%_%timestamp%.sql

echo.
echo ========================================
echo 开始导出...
echo ========================================
echo 输出文件: %output_file%
echo.
echo 正在导出数据库，请稍候...
echo （导出过程中会要求输入密码）
echo.

:: 执行导出，显示进度
mysqldump -h%host_name% -P%port_num% -u%user_name% -p --single-transaction --routines --triggers --default-character-set=utf8mb4 --verbose %db_name% > %output_file% 2>&1

if errorlevel 1 (
    echo.
    echo ========================================
    echo 导出失败！
    echo ========================================
    echo 可能原因：
    echo 1. MySQL服务未启动
    echo 2. 用户名密码错误
    echo 3. 数据库不存在
    echo 4. mysqldump命令未找到
) else (
    echo.
    echo ========================================
    echo 导出成功！
    echo ========================================
    echo 文件位置: %output_file%
    
    for %%A in (%output_file%) do echo 文件大小: %%~zA 字节
    echo.
)

:end
echo.
echo 按任意键退出...
pause >nul
