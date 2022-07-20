plugins {
    java
    id("io.izzel.taboolib") version "1.39"
    id("org.jetbrains.kotlin.jvm") version "1.6.10"
    `maven-publish`
}

taboolib {
    description {
        contributors {
            name("小白").description("PayApi")
        }
    }
    install("common")
    install("common-5")
    install("platform-bukkit")
    install("module-configuration")
    install("module-chat")
    install("module-lang")
    install("module-nms")
    install("module-nms-util")
    version = "6.0.9-13"
    relocate("com.lly835.bestpay", "com.xbaimiao.pay.core")
}

repositories {
    mavenCentral()
}

dependencies {
    taboo("com.google.zxing:core:3.4.1")
    taboo(fileTree("libs"))
    compileOnly("ink.ptms.core:v11701:11701:mapped")
    compileOnly("ink.ptms.core:v11701:11701:universal")
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib:1.5.31")
    compileOnly(fileTree("libs"))
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.jar {
    exclude("META-INF/LICENSE.txt")
    exclude("META-INF/LICENSE")
    exclude("META-INF/NOTICE")
    exclude("META-INF/versions/11/module-info.class")
    exclude("META-INF/NOTICE.txt")
}

publishing {
    repositories {
        maven("https://repo.xbaimiao.com/nexus/content/repositories/releases/") {
            credentials {
                username = project.findProperty("user").toString()
                password = project.findProperty("password").toString()
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            groupId = "org.serverct"
        }
    }
}