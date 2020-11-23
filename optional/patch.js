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

const fs = require("fs");

// Files where "matrix kick" is replaced
const kickless = ["./optional/kickless.checks.yml"];

// Replace all "matrix kick" commands
kickless.forEach(e => {
	console.log(`Removing kick messages for ${e}...`);

	const f = fs.readFileSync("./checks.yml", "utf-8");
	fs.writeFileSync(e, f.replace(/matrix kick %player%/g, "matrix notify %player% would have been kicked:"));
});

console.log("Done! Patched all files.");