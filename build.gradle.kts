plugins {
    java
    kotlin("jvm") version "1.3.72"
}

group = "org.mjie"
version = "1.0"

repositories {
    mavenCentral()
}


//依赖包的版本号管理
val feignVersion = "11.0"
val feignFormVersion = "3.8.0"
val nettyVersion = "4.1.54.Final"
val jolVersion = "0.14"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    // https://mvnrepository.com/artifact/io.netty/netty-all
    implementation("io.netty:netty-all:${nettyVersion}")
    implementation("org.openjdk.jol:jol-core:${jolVersion}")
    implementation("io.github.openfeign:feign-httpclient:${feignVersion}")
    implementation("io.github.openfeign:feign-jackson:${feignVersion}")
    implementation("io.github.openfeign:feign-core:${feignVersion}")
    implementation("io.github.openfeign:feign-hystrix:${feignVersion}")
    implementation("io.github.openfeign:feign-slf4j:${feignVersion}")
    implementation("io.github.openfeign.form:feign-form:${feignFormVersion}")
}


configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileJava {
        options.encoding = "utf-8"
    }
}