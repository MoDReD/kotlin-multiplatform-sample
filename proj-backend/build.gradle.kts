val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    application
    kotlin("jvm")
}

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenLocal()
    jcenter()
    maven { url = uri("https://kotlin.bintray.com/ktor") }
}

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    compile("io.ktor:ktor-server-netty:$ktor_version")
    compile("ch.qos.logback:logback-classic:$logback_version")
    compile("io.ktor:ktor-server-core:$ktor_version")
    compile("io.ktor:ktor-freemarker:$ktor_version")
    compile("io.ktor:ktor-velocity:$ktor_version")
    compile("io.ktor:ktor-server-host-common:$ktor_version")
    compile("io.ktor:ktor-server-sessions:$ktor_version")
    compile("io.ktor:ktor-webjars:$ktor_version")
    compile("org.webjars:jquery:3.2.1")
    compile("io.ktor:ktor-websockets:$ktor_version")
    compile("io.ktor:ktor-auth:$ktor_version")
    compile("io.ktor:ktor-auth-jwt:$ktor_version")
    
    compile(project(":proj-common"))
    
    // here I am trying to import front webjar
    // This FAILS with
    //       > Unable to find a matching variant of project :proj-angularfront:
    //          - Variant 'apiElements':
    //              - Required org.gradle.usage 'java-runtime' and found incompatible value 'kotlin-api'.
    //              - Found org.jetbrains.kotlin.localToProject 'public' but wasn't required.
    //              - Required org.jetbrains.kotlin.platform.type 'jvm' and found incompatible value 'js'.
    //          - Variant 'compile':
    //              - Required org.gradle.usage 'java-runtime' but no value provided.
    //              - Found org.jetbrains.kotlin.localToProject 'local to :proj-angularfront' but wasn't required.
    //              - Required org.jetbrains.kotlin.platform.type 'jvm' and found incompatible value 'js'.
    //          - Variant 'compileOnly':
    //              - Required org.gradle.usage 'java-runtime' but no value provided.
    //              - Found org.jetbrains.kotlin.localToProject 'local to :proj-angularfront' but wasn't required.
    //              - Required org.jetbrains.kotlin.platform.type 'jvm' and found incompatible value 'js'.
    compile(project(":proj-angularfront"))
    
    testCompile("io.ktor:ktor-server-tests:$ktor_version")
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")
