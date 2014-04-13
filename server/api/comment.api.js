var mongoose = require('mongoose'),
    Comment = mongoose.model('Comment');



//*******************************************
//* API CALLS
//*******************************************
exports.bind = function( app ) {

	// Create Comment
	app.post('/api/comment/create', function( request, response ) {

	});

  // Create Comment
  app.post('/api/comment/edit', function( request, response ) {

  });

  // Create Comment
  app.post('/api/comment/delete', function( request, response ) {

  });


};