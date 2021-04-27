import { JSZip } from "jszip/mod.ts";

function addFiles(zip: JSZip, ...files: string[]): void {
	for (const file of files) {
		zip.addFile(file, Deno.readFileSync(`${Deno.cwd()}/../../${file}`));
	}
}

export { addFiles };