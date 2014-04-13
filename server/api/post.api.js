var models = require(MODEL_CORE);




//*******************************************
//* API CALLS
//*******************************************
exports.bind = function( app ) {

	// Create Post
	app.post('/api/post/create', function( request, response ) {

	});

	// Edit Post
	app.post('/api/post/edit', function( request, response ) {

	});

  // Add image to Post
  app.post('/api/post/edit/images/add', function( request, response ) {

  });

  // Add image to Post
  app.post('/api/post/edit/images/remove', function( request, response ) {

  });

  // Post was sold
  app.post('/api/post/sold', function( request, response ) {

  });

  // Follow Post TODO: later for deletion of posts
  app.post('/api/post/close', function( request, response ) {

  });

  // Bid on Post
  app.post('/api/post/bid', function( request, response ) {

  });

	// Follow Post
	app.post('/api/post/follow', function( request, response ) {

	});

  // Like Post
  app.post('/api/post/like', function( request, response ) {

  });



};