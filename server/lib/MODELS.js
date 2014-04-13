var utils = exports.utils = require( LIB_PATH + 'utils.js' ),
	  fs = require( 'fs' ),
	  _ = require( 'underscore' );

var models = {};
var modelDir = fs.readdirSync( MODEL_PATH );
_.filter( modelDir, function( modelFile ) {
    if ( !fs.statSync( MODEL_PATH + modelFile ).isDirectory() && modelFile.indexOf( '.js' ) !== -1 && modelFile != 'models.js' ) {
        console.log( 'Loading Model Class: ' + modelFile );
        var model = require( MODEL_PATH + modelFile );
        models[ modelFile.split('.js')[0] ] = model;
    }
});
module.exports = models;