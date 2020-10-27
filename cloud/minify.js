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
const files            = ["./checks.yml", "./optional/kickless.checks.yml"];
const fs               = require("fs");

// Minify each file
let minifiedAmount = 0;
files.forEach(e => {
	const filePath = e.split("/");
	const fileName = `./cloud/${filePath.pop()}`;

	console.log(`Minifying ${e}...`);

	const yamlFile = fs.readFileSync(e, "utf-8");
	const yamlMini = yaml.safeDump(yaml.safeLoad(yamlFile), { "flowLevel": 0 }).trim();
	fs.writeFileSync(`${filePath.join("/")}/${fileName}`, yamlMini);

	console.log(`Minified ${e}! Before: ${yamlFile.length} | After: ${yamlMini.length}\n`);
	minifiedAmount = minifiedAmount + (yamlFile.length - yamlMini.length);
});

console.log(`Done! ${minifiedAmount} Total lines minified.`);