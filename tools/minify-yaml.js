/*
     ___
 __ /   \    _________
/   \___/   |     |  _|
\___/   \   | |_| | |_
	\___/   |_| |_|___|
		 MatrixConfig Tools
		    Version 1.X
*/

// Define and require modules
const { stripIndents } = require("common-tags");
const log			   = require("./logger").log;
const yaml			   = require("js-yaml");
const fs			   = require("fs");

// Defaults
let args = process.argv.splice(2, process.argv.length + 1);
if (args.length === 0) args = ["../checks.free.yml", "../checks.premium.yml"];

// Minify files
log.info(stripIndents`
	YAML files to minify:
	${args.join("\n")}
`);

let minifiedAmount = 0;

args.forEach(e => {
	const filePath = e.split("/");
	const fileName = `cloud.${filePath.pop()}`;

	log.info(`Minifying ${e}...`);

	const yamlFile = fs.readFileSync(e, "utf-8");
	const yamlMini = yaml.safeDump(yaml.safeLoad(yamlFile), { "flowLevel": 0 }).trim();
	fs.writeFileSync(`${filePath.join("/")}/${fileName}`, yamlMini);

	log.ok(`Minified ${e}! Before: ${yamlFile.length} | After: ${yamlMini.length}\n`)
	minifiedAmount = minifiedAmount + (yamlFile.length - yamlMini.length);
});

log.ok(`Done! ${minifiedAmount} Total lines minified.`)