@file:Suppress("UnstableApiUsage")

pluginManagement {
    plugins {
        kotlin("jvm") version "1.5.20-M1"
        kotlin("plugin.serialization") version "1.5.20-M1"

        id("net.mamoe.mirai-console") version "2.6.5"
    }
    repositories {
        mavenLocal()
        maven(url = "https://maven.aliyun.com/repository/releases")
        maven(url = "https://maven.aliyun.com/repository/public")
        mavenCentral()
        jcenter()
        maven(url = "https://maven.aliyun.com/repository/gradle-plugin")
        gradlePluginPortal()
    }
}
rootProject.name = "arknights-helper"

include("tools")