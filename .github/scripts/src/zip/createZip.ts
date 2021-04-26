import { JSZip } from "../../deps.ts";
import { addFiles } from "../util/util.ts";
import { getChecksVersion } from "../version/getVersion.ts";

async function createZip(...files: string[]): Promise<void> {
	const version = getChecksVersion();

	const zip = new JSZip();
	addFiles(zip, ...files);

	Deno.writeFile(`MatrixChecks_${version}.zip`, await zip.generateAsync({
		"type": "uint8array",
		"compression": "DEFLATE",
		"compressionOptions": {
			"level": 9
		}
	}));
}

function createZipAll(): void {
	createZip("checks.yml", "config.yml", "language.yml", "license.md");
}

export { createZip, createZipAll }