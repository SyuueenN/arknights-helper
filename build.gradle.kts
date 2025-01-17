plugins {
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.serialization") version "1.6.0"

    id("net.mamoe.mirai-console") version "2.10.0"
    id("net.mamoe.maven-central-publish") version "0.7.1"
}

group = "xyz.cssxsh"
version = "1.3.12"

mavenCentralPublish {
    useCentralS01()
    singleDevGithubProject("cssxsh", "arknights-helper")
    licenseFromGitHubProject("AGPL-3.0", "master")
    publication {
        artifact(tasks.getByName("buildPlugin"))
    }
}

repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
}

mirai {
    configureShadow {
        exclude("module-info.class")
    }
}

dependencies {
    compileOnly("net.mamoe:mirai-core:2.10.0")
    compileOnly("net.mamoe:mirai-core-utils:2.10.0")

    testImplementation(kotlin("test", "1.6.0"))
}

tasks {
    test {
        useJUnitPlatform()
    }
}