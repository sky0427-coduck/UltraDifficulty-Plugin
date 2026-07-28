import xyz.jpenilla.resourcefactory.bukkit.BukkitPluginYaml

plugins {
    id("java-library")
    alias(libs.plugins.paperweight.userdev)
    alias(libs.plugins.run.paper)
    alias(libs.plugins.resource.factory.bukkit)
}

repositories {
    mavenCentral()
}

dependencies {
    paperweight.paperDevBundle(libs.versions.paper.api.get())
}

bukkitPluginYaml {
    main = "com.sky0427_coduck.UltraDifficultyPlugin"
    paperPluginLoader = "com.sky0427_coduck.UltraDifficultyPluginLoader"
    apiVersion = "26.1.2"

    load = BukkitPluginYaml.PluginLoadOrder.STARTUP
    authors.addAll("Sky")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(25)
}

tasks {
    runServer {
        minecraftVersion(libs.versions.minecraft.get())
        jvmArgs("-Xms2G", "-Xmx2G", "-Dcom.mojang.eula.agree=true")
    }
}
