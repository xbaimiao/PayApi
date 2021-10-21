plugins {
    java
    id("io.izzel.taboolib") version "1.30"
    id("org.jetbrains.kotlin.jvm") version "1.5.10"
    `maven-publish`
}

taboolib {
    description {
        contributors {
            name("小白").description("TabooLib Developer")
        }
//        dependencies {
//            name("ExamplePlugin")
//            name("ExamplePlugin").optional(true)
//        }
    }
    install("common")
    install("platform-bukkit")
    install("module-configuration")
    install("module-chat")
    install("module-lang")
    install("module-nms")
    install("module-nms-util")
    version = "6.0.3-15"
}

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor("org.projectlombok:lombok:1.18.22")
    compileOnly("org.projectlombok:lombok:1.18.22")
    taboo("com.squareup.retrofit2:retrofit:2.9.0")
    taboo("commons-codec:commons-codec:1.10")
    taboo("com.squareup.retrofit2:converter-gson:2.9.0")
    taboo("com.squareup.retrofit2:converter-simplexml:2.9.0")
    taboo("com.fasterxml.jackson.core:jackson-databind:2.13.0")
    taboo("org.simpleframework:simple-xml:2.7.1")
    taboo("com.google.zxing:core:3.4.1")
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
        maven("https://www.xbaimiao.com/repository/maven-releases/") {
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
//        create<MavenPublication>("maven") {
//            artifactId = "payapi"
//            groupId = "org.serverct"
//            version = project.version.toString()
//            println("> version $version")
//            file("$buildDir/libs").listFiles()?.forEach { file ->
//                if (file.name.endsWith(".jar")) {
//                    artifact(file) {
//                        classifier = if (file.name.contains("all")) "all" else ""
////                            file.name.toLowerCase().replace("payapi-$version", "").replace(".jar", "").replace("-", "")
//                        println("> module $classifier (${file.name})")
//                    }
//                }
//            }
//        }
    }
}