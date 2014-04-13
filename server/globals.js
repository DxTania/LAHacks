global.LIB_PATH = __dirname + '/lib/';
global.API_CORE = __dirname + '/lib/API.js';
global.MODEL_PATH = __dirname + '/models/';
global.CACHE = {};
global.CONFIG = require( './config.js' );


try {
	global.CONFIG = require( './server.config.js' );
}
catch( e ) {
	global.CONFIG = require( './config.js' );
}