#!/bin/sh

# Minimal Gradle wrapper bootstrap script supporting Compose project.

set -e

APP_HOME=$(cd "$(dirname "$0")" && pwd)

if [ -n "$JAVA_HOME" ]; then
  JAVA_CMD="$JAVA_HOME/bin/java"
else
  JAVA_CMD="java"
fi

CLASSPATH="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"
SHARED_JAR="$APP_HOME/gradle/wrapper/gradle-wrapper-shared.jar"
if [ -f "$SHARED_JAR" ]; then
  CLASSPATH="$CLASSPATH:$SHARED_JAR"
fi

exec "$JAVA_CMD" -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
