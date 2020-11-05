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
const fs               = require("fs");

const kickless = ["./optional/kickless.checks.yml"];

// Replace all "matrix kick" commands
kickless.forEach(e => {
	console.log(`Removing kick messages for ${e}...`);

	const f = fs.readFileSync("./checks.yml", "utf-8");
	fs.writeFileSync(e, f.replace(/matrix kick %player%/g, "matrix notify %player% would have been kicked:"));
});

console.log("Done! Patched all files.");