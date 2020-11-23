/*
     ___
 ___╱   ╲   ┌─────┐┌───╮
╱   ╲___╱   │     ││  ┌╯
╲___╱   ╲   │ │─│ ││  └╮
    ╲___╱   └─┘ └─┘└───╯
            MatrixChecks

        Tools for developers
  This file isn't related to Matrix.
*/

const yaml = require("js-yaml");
const fs   = require("fs");

// Files that get minified
const files = [
	"./checks.yml",
	"./optional/kickless.checks.yml",
	"./optional/error.language.yml",
	"./optional/unknown.language.yml"
];

// Minify each file
let minifiedAmount = 0;
files.forEach(e => {
	// Get the path and destination
	const filePath = e.split("/");
	const fileName = `./cloud/${filePath.pop()}`;

	console.log(`Minifying ${e}...`);

	// Read and write the file
	const yamlFile = fs.readFileSync(e, "utf-8");
	const yamlMini = yaml.safeDump(yaml.safeLoad(yamlFile), { "flowLevel": 0 }).trim();
	fs.writeFileSync(`${fileName}`, yamlMini);

	console.log(`Minified ${e}! Before: ${yamlFile.length} | After: ${yamlMini.length}\n`);
	minifiedAmount = minifiedAmount + (yamlFile.length - yamlMini.length);
});

console.log(`Done! ${minifiedAmount} Total lines minified.`);