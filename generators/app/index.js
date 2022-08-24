// noinspection JSUnresolvedFunction

const Generator = require("yeoman-generator")
const chalk = require("chalk")
const yosay = require("yosay")
const path = require("path")
const mkdirp = require("mkdirp")
const {spawn} = require("child_process")

const packageRegex = new RegExp("^([A-Za-z]{1}[A-Za-z\\d_]*\\.)+[A-Za-z][A-Za-z\\d_]*$")
const projectName = new RegExp("^([^\\/]+)$")
const rootPathRegex = new RegExp("^\\/([A-z0-9-_+]+\\/)*([A-z0-9-_+]+)$")

// noinspection JSUnusedGlobalSymbols,JSUnresolvedFunction
module.exports = class extends Generator {
    async prompting() {
        this.log(yosay(`Welcome to the gnarly ${chalk.red("minimul-api")} generator!`))

        const prompts = [
            {
                type: "input",
                name: "projectName",
                message: "What is the api name?",
                default: "example-api",
                validate: (x) => {
                    const valid = x.match(projectName)
                    if (!valid) {
                        return "invalid project name, must be all lower case with optional hyphens e.g. example-api"
                    }
                    return true
                }
            },
            {
                type: "input",
                name: "group",
                message: "What is the group name?",
                default: "com.example",
                validate: (x) => {
                    const valid = x.match(packageRegex)
                    if (!valid) {
                        return "invalid package name, please refer to https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html"
                    }
                    return true
                }
            },
            {
                type: "input",
                name: "basePackage",
                message: "What is the base package?",
                default: "com.example",
                validate: (x) => {
                    const valid = x.match(packageRegex)
                    if (!valid) {
                        return "invalid package name, please refer to https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html"
                    }
                    return true
                }
            },
            {
                type: "input",
                name: "rootPath",
                message: "What is the root path?",
                default: "/example",
                validate: (x) => {
                    if (!x.match(rootPathRegex)) {
                        return "invalid root path, must start with '/' and cannot be empty e.g. /example"
                    }
                    return true
                }
            },
        ]

        this.answers = await this.prompt(prompts)
    }

    async writing() {
        const basePackage = this.answers.basePackage
        const rootPath = this.answers.rootPath

        const mainPackageClass = basePackage + ".ServerKt"
        const serverPackageDirs = basePackage.replace(/\./g, "/")

        const apiName = this.answers.projectName

        this.fs.copyTpl(
            this.templatePath("base"),
            this.destinationPath(""),
            {
                projectName: this.answers.projectName,
                group: this.answers.group,
                mainClass: mainPackageClass,
                mainClassAllLower: mainPackageClass.toLowerCase(),
                basePackage: basePackage,
                apiName: apiName
            }
        )

        const cwd = path.resolve(process.cwd())

        const serverDir = path.resolve(cwd, "server/src/main/kotlin", serverPackageDirs)
        const serverTestDir = path.resolve(cwd, "server/src/test/kotlin", serverPackageDirs)
        const coreDir = path.resolve(cwd, "core/src/main/kotlin", serverPackageDirs)

        await mkdirp(serverDir)
        await mkdirp(coreDir)

        this.fs.copyTpl(
            this.templatePath("fillins/server/main"),
            this.destinationPath(path.resolve(serverDir)),
            {
                serverPackageName: basePackage,
                rootPath: rootPath
            }
        )

        this.fs.copyTpl(
            this.templatePath("fillins/core/main"),
            this.destinationPath(path.resolve(coreDir)),
            {
                serverPackageName: basePackage,
                rootPath: rootPath
            }
        )

        this.fs.copyTpl(
            this.templatePath("fillins/server/test"),
            this.destinationPath(path.resolve(serverTestDir)),
            {
                serverPackageName: basePackage,
                rootPath: rootPath
            }
        )

        this.fs.copyTpl(
            this.templatePath("_github"),
            this.destinationPath(".github")
        )


        this.fs.copyTpl(
            this.templatePath("_gitignore"),
            this.destinationPath(".gitignore")
        )
    }

    async install() {
        this.log(yosay("installing dependencies + assembling API"))
        // noinspection JSCheckFunctionSignatures
        // const build = spawn("./gradlew", ["clean", "build", "quarkusDev"])
        const build = spawn("./gradlew", ["quarkusDev"])
        build.stdout.on("data", (data) => {
            console.log(data.toString())
        })
        build.stderr.on("data", (data) => {
            console.log(data.toString())
        })
    }
}
