
import { createZipAll } from "./src/zip/createZip.ts";
import { minifyAll } from "./src/patch/minify.ts";

// Create ZIP
console.log("Creating a zip of all important repository files...");
createZipAll();
console.log("Done!");

// Minify files
console.log("Minifying all important YAML files...");
minifyAll();
console.log("Done!");

// Generate the share ID
/*
import { startServer } from "./src/share/startServer.ts";
const server = startServer();
console.log(server.status());
*/