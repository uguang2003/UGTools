@echo off
chcp 65001 >nul
echo ====================================
echo      端口进程终止工具
echo ====================================
echo.

:input
set /p port="请输入要终止的端口号: "

:: 检查输入是否为空
if "%port%"=="" (
    echo 错误: 端口号不能为空！
    goto input
)

:: 检查输入是否为数字
echo %port%| findstr /r "^[0-9][0-9]*$" >nul
if errorlevel 1 (
    echo 错误: 请输入有效的端口号！
    goto input
)

echo.
echo 正在查找占用端口 %port% 的进程...

:: 启用延迟变量扩展
setlocal enabledelayedexpansion

:: 查找占用指定端口的进程ID
set found=0
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :%port%') do (
    if not "%%a"=="0" (
        set found=1
        echo 找到进程ID: %%a
        echo 正在终止进程...
        taskkill /PID %%a /F >nul 2>&1
        if !errorlevel! equ 0 (
            echo 进程 %%a 已成功终止
        ) else (
            echo 终止进程 %%a 失败
        )
    )
)

if %found%==0 (
    echo 没有找到占用端口 %port% 的进程
)

echo.
echo 检查端口 %port% 状态:
netstat -ano | findstr :%port%
if %errorlevel% neq 0 (
    echo 端口 %port% 现在没有被占用
) else (
    echo 端口 %port% 仍然被占用
)

echo.
set /p again="是否要终止其他端口? (y/n): "
if /i "%again%"=="y" goto input

echo 操作完成，按任意键退出...
pause >nul