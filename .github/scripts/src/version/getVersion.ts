import { parseYAML } from "../util/yaml.ts";

interface versionFile {
	checks?: string,
	matrix?: string
}

const version: versionFile = {};

function getVersion(): versionFile {
	const yaml = parseYAML("../version.yml") as versionFile;

	version.checks = yaml.checks;
	version.matrix = yaml.matrix;

	return version;
}

function getChecksVersion(): string {
	if (version.checks === undefined) {
		getVersion();
	}

	return version.checks as string;
}

function getMatrixVersion(): string {
	if (version.matrix === undefined) {
		getVersion();
	}

	return version.matrix as string;
}

export { getChecksVersion, getMatrixVersion };