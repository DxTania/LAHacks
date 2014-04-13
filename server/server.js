var express = require ('express'),
    mongoose = require('mongoose'),
	fs      = require('fs'),
    expressValidator = require('express-validator'),
	_       = require( 'underscore' );

//*******************************************
//* GLOBAL VARS (accessible from anywhere)
//********************************************
require( './globals.js' );
require( API_CORE );

//*******************************************
//* DATABASE INIT
//*******************************************
var connect = function() {
    var options = { server: { socketOptions: { keepAlive: 1 }}};
    mongoose.connect( CONFIG.db, options );
}
connect();
// Handle errors
mongoose.connection.on( 'error', function(err) {
    console.log(err);
});
// Reconnect on disconnect
mongoose.connection.on( 'disconnected', function(err) {
    connect();
});

// Bootstrap models
var models_path = __dirname + '/models'
fs.readdirSync(models_path).forEach(function (file) {
  if (~file.indexOf('.js')) require(models_path + '/' + file)
});

//*******************************************
//* SPECIAL EVENT LISTENERS
//********************************************
process.on( 'uncaughtException', function ( error ) {
    console.error( error.stack ? error.stack : error );
    process.exit( 1 );
});


// Using UTC time
process.env.TZ = 'UTC';

// Init web server
var app = express();
app.use(express.bodyParser());
app.use(express.cookieParser());
app.use( expressValidator() );

//*******************************************
//* API LIBRARIES & MODELS
//*******************************************
var apiDir = fs.readdirSync( __dirname + '/api' );
var modelDir = fs.readdirSync( __dirname + '/models' );

// Include all api files
_.filter( apiDir, function( libFile ) {
    if ( !fs.statSync( __dirname + '/api/' + libFile ).isDirectory() && libFile.indexOf( '.js' ) !== -1 && libFile != 'API.js' ) {
        console.log( 'Binding API Class: ' + libFile );
    	require( __dirname + '/api/' + libFile ).bind( app );
    }
});


app.listen( CONFIG.port, function( error ) {
	if ( error ) {
		console.log( error );
	} else {
		console.log('Listening on Port: ' + CONFIG.port);
	}
});
