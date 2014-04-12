var express = require ('express'),
	fs      = require('fs'),
    expressValidator = require('express-validator'),
	_       = require( 'underscore' );


//*******************************************
//* DATABASE INIT
//*******************************************
var Mongoose = require('mongoose');
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


//*******************************************
//* SPECIAL EVENT LISTENERS
//********************************************
process.on( 'uncaughtException', function ( error ) {
    console.error( error.stack ? error.stack : error );
    process.exit( 1 );
});


//*******************************************
//* GLOBAL VARS (accessible from anywhere)
//********************************************
require( './globals.js' );
require( API_CORE );

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
    	if( CONFIG.environment!='live' ) {
            console.log( 'Binding API Class: ' + libFile );    
        }
    	require( __dirname + '/api/' + libFile ).bind( app );
    }
});

_.filter( modelDir, function( libFile ) {
    if ( !fs.statSync( __dirname + '/models/' + libFile ).isDirectory() && libFile.indexOf( '.js' ) !== -1 && libFile != 'models.js' ) {
        console.log( 'Binding Model Class: ' + libFile );
    }
    require( __dirname + '/models/' + libFile ).bind( app );
});

app.listen( CONFIG.port, function( error ) {
	if ( error ) {
		console.log( error );
	} else {
		console.log('Listening on Port: ' + CONFIG.port);
	}
});
