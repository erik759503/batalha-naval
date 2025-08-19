@echo off
echo ========================================
echo    CRIANDO EXECUTAVEL BATALHA NAVAL
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

echo Compilando e criando JAR executavel...
mvn clean package

if errorlevel 1 (
    echo ERRO: Falha na criacao do JAR!
    pause
    exit /b 1
)

echo.
echo JAR executavel criado com sucesso!
echo Localizacao: target\batalha-naval-1.0.2.jar
echo.
echo Para executar o jogo, use:
echo java -jar target\batalha-naval-1.0.2.jar
echo.

REM Perguntar se quer executar o jogo agora
set /p executar="Deseja executar o jogo agora? (s/n): "
if /i "%executar%"=="s" (
    echo.
    echo Executando o jogo...
    java -jar target\batalha-naval-1.0.2.jar
)

echo.
echo Processo finalizado.
pause 