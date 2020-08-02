// Define and require modules
const winston = require("winston");

// Make the Winston logger
const levels = {
	"levels": {
		"ok": 0,
		"info": 1,
		"warning": 2,
		"error": 3
	},
	"colors": {
		"ok": "green",
		"info": "blue",
		"warning": "yellow",
		"error": "red"
	}
};

const format = {
	"base": winston.format.combine(
		winston.format.timestamp({ "format": "M/D/YY h:mm:ss A" }),
		winston.format.printf(({ level, message, label, timestamp }) => {return `${timestamp} | ${level}: ${message}`})
	),
	get "console"() {
		return winston.format.combine(
			winston.format.colorize(),
			this.base
		);
	},
};

// eslint-disable-next-line new-cap
const log = new winston.createLogger({
	"level":	   "error",
	"levels":	   levels.levels,
	"exitOnError": false,
	"transports":  [new winston.transports.Console({ "format": format.console })]
});
winston.addColors(levels.colors);

module.exports.log = log;