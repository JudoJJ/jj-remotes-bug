@echo off
setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
set APP_HOME=%DIRNAME%
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

if defined JAVA_HOME (
  set JAVA_EXE=%JAVA_HOME%\bin\java.exe
) else (
  set JAVA_EXE=java.exe
)

set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar
if exist "%APP_HOME%\gradle\wrapper\gradle-wrapper-shared.jar" (
  set CLASSPATH=%CLASSPATH%;%APP_HOME%\gradle\wrapper\gradle-wrapper-shared.jar
)

"%JAVA_EXE%" -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
endlocal
