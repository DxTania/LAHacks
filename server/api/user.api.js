var mongoose = require('mongoose'),
		api = require(API_CORE),
    User = mongoose.model('User'),
    Post = mongoose.model('Post');

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
		Post.find({},
			function( err, posts ) {
				if ( err ) {
					console.log( err );
					api.JsonResponse( err, response, 500 );
					return;
				}

				api.JsonResponse( posts, response, 200 );
			});
	});

	// User profile
  app.post('/api/user/profile', function( request, response ) {
  	User.findOne({
  		firstName: 'Tania'
		}, 'avatar firstName lastName rating'
		, function( err, user ) {
			if ( err ) {
					console.log( err );
					api.JsonResponse( err, response, 500 );
					return;
			}

			Post.find({
				user: user._id
			}, function( err, posts ) {
				if ( err ) {
					console.log( err );
					api.JsonResponse( err, response, 500);
					return;
				}

				retUser = api.PureCloneObj( user );
				retUser.posts = api.PureCloneObj( posts );
				api.JsonResponse( retUser, response, 200 );
			});
		});
  });

  // Follow a user
  app.post('/api/user/follow', function( request, response ) {

  });

  // Edit a user
  app.post('/api/user/edit', function( request, response ) {

  });

};