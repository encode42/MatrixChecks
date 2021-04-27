import { parse } from "encoding/yaml.ts";

function parseYAML(file: string): unknown {
	const stream = Deno.readTextFileSync(file);
	return parse(stream);
}

export { parseYAML };