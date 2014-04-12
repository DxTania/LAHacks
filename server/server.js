var express = require ('express'),
	fs      = require('fs'),
    expressValidator = require('express-validator'),
	_       = require( 'underscore' );


//*******************************************
//* DATABASE INIT
//*******************************************
// var Mongoose = require('mongoose');
// var db = Mongoose.createConnection('localhost', 'LAHacksYard');

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
prcess.env.TZ = 'UTC';

// Init web server
var app = express();
app.use(express.bodyParser());
app.use(express.cookieParser());
app.use( expressValidator() );

//*******************************************
//* API LIBRARIES & CONTROLLERS
//*******************************************
var apiDir = fs.readdirSync( __dirname + '/api' );

// Include all api files
_.filter( apiDir, function( libFile ) {
    if ( !fs.statSync( __dirname + '/api/' + libFile ).isDirectory() && libFile.indexOf( '.js' ) !== -1 && libFile != 'API.js' ) {
    	if( CONFIG.environment!='live' ) {
            console.log( 'Binding API Class: ' + libFile );    
        }
    	require( __dirname + '/api/' + libFile ).bind( app );
    }
});


app.listen( CONFIG.port, function( error ) {
	if ( err ) {
		console.log( err );
	} else {
		console.log('Listening on Port: ' + CONFIG.port);
	}
});
