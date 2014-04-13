var utils = exports.utils = require( LIB_PATH + 'utils.js' ),
		fs = require( 'fs' );


//*******************************************
//* HTTP API RESPONSE
//********************************************

// Generic JSON Response
var JsonResponse = exports.JsonResponse = function( params, response, code ) {
	var code = code && code !== 200 ? code : 200;
	var responseObject = {
		status: code === 200 ? 'success' : 'error',
		time: new Date(),
		message: params
	};
	response.json( responseObject, code );
};


// Censored JSON Response; used to hide user passwords, etc
var CensoredResponse = exports.CensoredResponse = function( params, response, censored, conditionalCensor ) {
    params = params.dataValues || params;
	var censoredParams = utils.Security.Censor( params, censored, conditionalCensor );
	JsonResponse( censoredParams, response, 200 );
};
