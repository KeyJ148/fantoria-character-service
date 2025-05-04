val imageName = "${project.name}:${project.version}"

tasks.register("dockerPrintImageName") {
    group = "docker"
    doLast {
        print(imageName)
    }
}

tasks.register<Exec>("dockerBuild") {
    group = "docker"
    commandLine("docker", "build", "-t", imageName, ".")
}

tasks.register<Exec>("dockerComposeUp") {
    group = "docker"
    dependsOn("dockerBuild")
    commandLine("docker-compose", "up", "-d")
}

tasks.register<Exec>("dockerComposePortsUp") {
    group = "docker"
    dependsOn("dockerBuild")
    commandLine("docker-compose",
        "-f", "docker-compose.yaml",
        "-f", "docker-compose.ports.yaml",
        "up", "-d")
}

tasks.register<Exec>("dockerComposeDown") {
    group = "docker"
    commandLine("docker-compose", "down")
}