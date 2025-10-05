import java.io.File
import java.io.IOException

val osName = System.getProperty("os.name").lowercase()
val repoPath = System.getProperty("user.dir")
val jjBinary = if (osName.contains("win")) "jj.exe" else "jj"

println("Testing JJ commands on repo: $repoPath")
println("OS: $osName")
println("JJ binary: $jjBinary")
println()

runCommand(listOf(jjBinary, "log", "--limit", "5"), repoPath)
printSeparator()
runCommand(listOf(jjBinary, "git", "remote", "list"), repoPath)
printSeparator()
runCommand(listOf(jjBinary, "git", "fetch", "--remote", "origin"), repoPath)

fun runCommand(command: List<String>, workingDir: String) {
    println("Running: ${command.joinToString(" ")}")
    println("Working directory: $workingDir")

    val dir = File(workingDir)
    println("Directory exists: ${dir.exists()}")
    println("Is directory: ${dir.isDirectory}")
    println("Absolute path: ${dir.absolutePath}")
    println("Canonical path:")
    try {
        println("  ${dir.canonicalPath}")
    } catch (e: IOException) {
        println("  Error getting canonical path: ${e.message}")
    }

    try {
        val process = ProcessBuilder(command)
            .directory(dir)
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)
            .start()

        val output = process.inputStream.readAllBytes().decodeToString()
        val error = process.errorStream.readAllBytes().decodeToString()

        println("Output bytes: ${output.length}")
        println("Error bytes: ${error.length}")

        if (output.isNotEmpty()) {
            println("\nOutput:")
            print(output)
        }

        if (error.isNotEmpty()) {
            println("\nError:")
            print(error)
        }

        if (output.isEmpty() && error.isEmpty()) {
            println("\nWARNING: Both output and error are empty!")
        }
    } catch (e: IOException) {
        println("Exception: ${e.message}")
        e.printStackTrace()
    }
}

fun printSeparator() {
    println("\n================================================================================\n")
}
