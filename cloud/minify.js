/*
     ___
 __ /   \    _____  ___
/   \___/   |     ||  _|
\___/   \   | |_| || |_
    \___/   |_| |_||___|
            MatrixChecks
*/

// This file doesn't include anything for the end-user.
// It is only for development purposes.

// Define and require modules
const yaml             = require("js-yaml");
const fs               = require("fs");

// Defaults
let args = process.argv.splice(2, process.argv.length + 1);
if (args.length === 0) args = ["../checks.yml"];

// Minify files
console.log(`Files to minify: ${args.join(", ")}`);
let minifiedAmount = 0;

args.forEach(e => {
	const filePath = e.split("/");
	const fileName = `${filePath.pop()}`;

	console.log(`Minifying ${e}...`);

	const yamlFile = fs.readFileSync(e, "utf-8");
	const yamlMini = yaml.safeDump(yaml.safeLoad(yamlFile), { "flowLevel": 0 }).trim();
	fs.writeFileSync(`${filePath.join("/")}/${fileName}`, yamlMini);

	console.log(`Minified ${e}! Before: ${yamlFile.length} | After: ${yamlMini.length}\n`);
	minifiedAmount = minifiedAmount + (yamlFile.length - yamlMini.length);
});

console.log(`Done! ${minifiedAmount} Total lines minified.`);