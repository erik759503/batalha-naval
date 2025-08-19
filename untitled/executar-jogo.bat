@echo off
echo ========================================
echo    BATALHA NAVAL - EXECUTAVEL
echo ========================================
echo.

REM Verificar se o Java está instalado
java -version >nul 2>&1
if errorlevel 1 (
    echo ERRO: Java nao encontrado!
    echo Por favor, instale o Java 11 ou superior.
    echo Download: https://adoptium.net/
    pause
    exit /b 1
)

REM Verificar se o Maven está instalado
mvn -version >nul 2>&1
if errorlevel 1 (
    echo ERRO: Maven nao encontrado!
    echo Por favor, instale o Apache Maven.
    echo Download: https://maven.apache.org/download.cgi
    pause
    exit /b 1
)

echo Compilando o projeto...
mvn clean compile

if errorlevel 1 (
    echo ERRO: Falha na compilacao!
    pause
    exit /b 1
)

echo.
echo Executando o jogo...
echo.

REM Executar o jogo
mvn exec:java -Dexec.mainClass="batalha_naval.TelaInicial"

echo.
echo Jogo finalizado.
pause 