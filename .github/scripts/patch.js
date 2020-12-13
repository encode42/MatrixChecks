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


// Not used due to redundancy


const fs = require("fs");

// Files where "matrix kick" is replaced
const kickless = [
	"./checks.yml",
	"./free.checks.yml"
];

// Replace all "matrix kick" commands
kickless.forEach(e => {
	if (!fs.existsSync(e)) return console.log(`${e} does not exist! Skipping...`);

	// Get the path and destination
	const filePath = e.split("/");
	const fileName = `./optional/kickless.${filePath.pop()}`;

	console.log(`Removing kick messages in ${e}...`);

	// Replace kick messages and write the file
	const checksFile = fs.readFileSync(e, "utf-8");
	fs.writeFileSync(`${fileName}`, checksFile.replace(/matrix kick %player%/g, "matrix notify %player% would have been kicked:"));

	console.log(`Removed kicks in ${e}!`);
});

console.log("Done! Patched all files.");