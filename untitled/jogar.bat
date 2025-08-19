@echo off
echo ========================================
echo         BATALHA NAVAL
echo ========================================
echo.

REM Verificar se o JAR existe
if not exist "target\batalha-naval-1.0.2.jar" (
    echo JAR nao encontrado! Executando compilacao...
    call criar-executavel.bat
    if errorlevel 1 (
        echo ERRO: Nao foi possivel criar o executavel!
        pause
        exit /b 1
    )
)

echo Executando Batalha Naval...
java -jar target\batalha-naval-1.0.2.jar

echo.
echo Jogo finalizado.
pause 