package cli

//import cli.CliCommand.FileType.*
//import cli.CliConfig.COMMAND_NAME
//import cli.CliConfig.CURRENT_GIT_USER
//import cli.CliConfig.FIND
//import cli.CliConfig.GIT
//import cli.CliConfig.GIT_STANDUP_WHITELIST
//import com.github.ajalt.clikt.completion.completionOption
//import com.github.ajalt.clikt.core.CliktCommand
//import com.github.ajalt.clikt.parameters.options.*
//import com.github.ajalt.clikt.parameters.types.choice
//import com.github.ajalt.clikt.parameters.types.enum
//import com.github.ajalt.clikt.parameters.types.int
//import com.github.ajalt.mordant.rendering.TextColors.*
//import com.github.ajalt.mordant.terminal.Terminal
//import io.fileIsReadable
//import io.readAllText

/**
 * CliKt provides Kotlin Multiplatform command line interface parsing for Kotlin
 * https://ajalt.github.io/clikt/
 *
 */
//class CliCommand : CliktCommand(
//    help = """
//       Transform your string resources into Android and iOS counterparts.
//    """.trimIndent(),
//    epilog = """
//
//        Examples:
//            $COMMAND_NAME -a "John Doe" -w "MON-FRI" -m 3
//    """.trimIndent(),
//    name = COMMAND_NAME
//) {
//    init {
//        completionOption()
//    }
//
//    enum class FileType {
//        Ios, Android, CSV
//    }
//
//    lateinit var reportFile: String
//    val inputType by option(
//        "--input-type",
//        "-i",
//        help = "Specify the input type Transkribe should look for. Type Android looks for strings.xml, type csv looks for strings.csv"
//    )
//        .choice("android", "ios", "csv")
//        .enum<FileType>()
//        .default(CSV)
//    val outputTypes by option(
//        "--output-type",
//        "-o",
//        help = "Specify the output type(s) Transkribe should transform to"
//    ).choice("android", "ios", "csv").enum<FileType>().multiple().unique()
//    val inputRoot by option(
//        "--input-root",
//        "-I",
//        help = "Specify input directory/file Transkribe should use"
//    ).default(".")
//    val outputRoot by option(
//        "--output-root",
//        "-O",
//        help = "Specify output directory root Transkribe should use. It will contain folders named after output types requested"
//    ).default(".")
//
//
//    val authorOpt: String by option("--author", "-a", help = "Specify author to restrict search to").default("me")
//    val branch: String by option(
//        "--branch",
//        "-b",
//        help = "Specify branch to restrict search to (unset: all branches, \"\$remote/\$branch\" to include fetches)"
//    ).default("")
//    val weekDay: String by option("--week-day", "-w", help = "Specify weekday range to limit search to").default("")
//    val depth: Int by option("--depth", "-m", help = "Specify the depth of recursive directory search").int()
//        .default(-1)
//    val forceRecursion by option("--force-recursion", "-F", help = "Force recursion up to speficied depth")
//    val symbolicLinks by option(
//        "--symbolic-links",
//        "-L",
//        help = "Toggle inclusion of symbolic links in recursive directory search"
//    ).flag(default = false, defaultForHelp = "disabled")
//    val daysTo: Int by option("-d", "--days", help = "Specify the number of days back to include").int().default(1)
//    val daysUntil: Int by option("-u", "--until", help = "Specify the number of days back until this day").int()
//        .default(0)
//    val dateFormat: String by option(
//        "-D",
//        "--date-format",
//        help = "Specify the number of days back until this day"
//    ).default("")
//    val help: Boolean by option("-h", "--help", help = "Display this help screen").flag()
//    val gpgSigned by option(
//        "-g",
//        "--gpg-signed",
//        help = "Show if commit is GPG signed (G) or not (N)"
//    ).flag("disabled")
//    val fetch by option("-f", "--fetch", help = "Fetch the latest commits beforehand").flag("--no-fetch")
//    val silence by option(
//        "-s",
//        "--silence",
//        help = "Silences the no activity message (useful when running in a directory having many repositories)"
//    ).flag()
//    val report by option("-r", "--report", help = "Generate a file with the report").flag()
//    val diffStat by option("-c", "--diff-stat", help = "Show diffstat for every matched commit")
//    val afterOpt: String by option("-A", "--after", help = "List commits after this date").default("")
//    val before: String by option("-B", "--before", help = "List commits before this date").default("")
//    val authorDate by option(
//        "-R",
//        "--author-date",
//        help = "Display the author date instead of the committer date"
//    ).flag()
//    val verbose by option("-v", "--verbose", help = "verbose").flag(defaultForHelp = "disabled")
//
//    override fun run() {
//        if (verbose) println(this)
//    }
//
//    fun gitLogCommand(): List<String> {
//        val args = mutableListOf<String>()
//
//        val branch = if (branch.isBlank()) "--all" else "--first-parent $branch"
//        val since = if (daysTo == 1) "yesterday" else "$daysTo days ago"
//        val after = if (afterOpt.isNotBlank()) "--after=$afterOpt" else ""
//        val gitDateFormat = if (dateFormat.isBlank()) "relative" else dateFormat
//        val color = "always" // ???
//        val author = authorName()
//        val gitPrettyDate = if (authorDate) "%ad" else "%cd"
//        var gitPrettyFormat = "%Cred%h%Creset - %s %Cgreen($gitPrettyDate) %C(bold blue)<%an>%Creset"
//        if (gpgSigned) gitPrettyFormat += " %C(yellow)gpg: %G?%Creset"
//        if (report) {
//            val withGpg = if (gpgSigned) "gpg: %G?" else ""
//            gitPrettyFormat = "%h - %s  ($gitPrettyDate) <%an>  $withGpg\n"
//        }
//
//        val until = when {
//            daysUntil != 0 -> "--until='${daysUntil} days ago'"
//            before.isNotBlank() -> "--until='$before'"
//            else -> ""
//        }
//
//        args += listOf(GIT, "--no-pager", "log")
//        args += branch.split(" ")
//        args += "--no-merges"
//        args += "--since=$since"
//        args += until
//        args += after
//        args += "--author=$author"
//        args += "--abbrev-commit"
//        args += "--oneline"
//        args += "--pretty=format:$gitPrettyFormat"
//        args += "--date=$gitDateFormat"
//        args += "--color=$color"
//        if (diffStat != null) args += ("--stat")
//        return args
//    }
//
//    fun authorName() = when (authorOpt) {
//        "all" -> ".*"
//        "me" -> CURRENT_GIT_USER
//        else -> authorOpt
//    }
//
//    // TODO rework finding all files with translations
//    fun findImportFileCommand(): List<String> {
//        val args = mutableListOf<String>()
//
//        args += FIND
//        args += listOf(".")
//        args += "-mindepth"
//        args += "0"
//        args += "-name"
//        args += when (inputType) {
//            Ios -> "Localizable.strings"
//            Android -> "strings.xml"
//            CSV -> "strings.csv"
//        }
//        return args.also { if (verbose) println("$ $it") }
//    }
//
//
//    fun findCommand(): List<String> {
//        val args = mutableListOf<String>()
//
//        args += FIND
//        args += when {
//            fileIsReadable(GIT_STANDUP_WHITELIST) -> readAllText(GIT_STANDUP_WHITELIST).lines()
//                .map { it.trim().removeSuffix("/") }
//            else -> listOf(".")
//        }
//        if (symbolicLinks) args += "-L"
//        args += "-maxdepth"
//        args += if (depth == -1) "2" else (depth + 1).toString()
//        args += "-mindepth"
//        args += "0"
//        args += "-name"
//        args += ".git"
//        return args.also { if (verbose) println("$ $it") }
//    }
//}
//
