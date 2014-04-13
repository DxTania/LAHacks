var mongoose = require('mongoose'),
    api = require(API_CORE),
    Transaction = mongoose.model('Transaction');


//*******************************************
//* API CALLS
//*******************************************
exports.bind = function( app ) {

	// Create Transaction
	app.post('/api/transaction/create', function( request, response ) {

	});

  // Edit Transaction
  app.post('/api/transaction/edit', function( request, response ) {

  });

  // Transaction sold
  app.post('/api/transaction/success', function( request, response ) {

  });

  // Transaction (offer) accepted
  app.post('/api/transaction/accept', function( request, response ) {

  });

  // Remove a transaction
  app.post('/api/transaction/delete', function( request, response ) {

  });

};