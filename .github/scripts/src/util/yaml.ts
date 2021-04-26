import { yamlParse } from "../../deps.ts";

function parseYAML(file: string): unknown {
	const stream = Deno.readTextFileSync(file);
	return yamlParse(stream);
}

export { parseYAML };