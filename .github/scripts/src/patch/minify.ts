import { yamlDump, ensureFileSync } from "../../deps.ts";
import { parseYAML } from "../util/yaml.ts";

const rootDir = `${Deno.cwd()}/../../cloud/`;

function minifyAll(): void {
	minify(
		"checks.yml", "config.yml", "language.yml",
		"variations/fake-error/language.yml", "variations/kickless/language.yml", "variations/unknown-reason/language.yml"
	);
}

function minify(...files: string[]): void {
	for (const file of files) {
		const yamlFile = parseYAML(`${Deno.cwd()}/../../${file}`);

		const minified = yamlDump(yamlFile as Record<string, unknown>, {
			"flowLevel": 0
		}).replace(/'(\d*)'/g, "$1") // Remove quotes around numbers
			.replace(/, /g, ",")     // Remove space after comma
			.trim();

		ensureFileSync(`${rootDir}${file}`);

		Deno.writeTextFileSync(`${rootDir}${file}`, minified);
	}
}

export { minify, minifyAll };