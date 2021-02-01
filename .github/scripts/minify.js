/*
┌─────┐┌───╮
│     ││  ┌╯
│ │─│ ││  └╮
└─┘ └─┘└───╯
MatrixChecks
*/

const yaml = require("js-yaml"),
	  fs   = require("fs");

// Files to minify
const files = [
	"./checks.yml",
	"./free.checks.yml",
	"./language.yml",
	"./config.yml",
	"./optional/kickless.language.yml",
	"./optional/error.language.yml",
	"./optional/unknown.language.yml"
];

// Run through each file
let minifiedAmount, totalAmount;
minifiedAmount = totalAmount = 0;
files.forEach(e => {
	if (!fs.existsSync(e)) return console.log(`${e} cannot be found!`);

	// Get the path and destination
	const filePath = e.split("/");
	const fileName = `./cloud/${filePath.pop()}`;

	console.log(`Minifying ${e}...`);

	// Minify the file
	const file     = fs.readFileSync(e, "utf-8");
	const minified = yaml.safeDump(yaml.safeLoad(file), { "flowLevel": 0 })
		.replace(/'(\d*)'/g, "$1") // Remove quotes around numbers
		.replace(/, /g, ",")       // Remove space after comma
		.trim();

	fs.writeFileSync(`${fileName}`, minified);

	console.log(`Done! (${file.length} → ${minified.length} characters)`);

	minifiedAmount += minified.length;
	totalAmount    += file.length;
});

console.log(`Finished! ${totalAmount - minifiedAmount} total characters saved.`);