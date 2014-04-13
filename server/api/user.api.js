var mongoose = require('mongoose'),
    User = mongoose.model('User');

//*******************************************
//* API CALLS
//*******************************************
exports.bind = function( app ) {

	// Register
	app.post('/api/user/signup', function( request, response ) {

	});

	// Signin
	app.post('/api/user/signin', function( request, response ) {

	});

	// Rate a User
	app.post('/api/user/rate', function( request, response ) {

	});

	// Generate user's feed
	app.post('/api/user/feed', function( request, response ) {

	});

  // Follow a user
  app.post('/api/user/follow', function( request, response ) {

  });

  // Edit a user
  app.post('/api/user/edit', function( request, response ) {

  });

};