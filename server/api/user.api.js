var mongoose = require('mongoose'),
		api = require(API_CORE),
    User = mongoose.model('User'),
    Post = mongoose.model('Post');

//*******************************************
//* Validation data
//*******************************************
function validateSignupParams( request ) {
		request.checkBody('firstName', 'firstName is required.').notEmpty();
		request.checkBody('firstName', 'firstName must be alpha only.').isAlpha();
		request.checkBody('lastName', 'lastName is required.').notEmpty();
		request.checkBody('email', 'email is required.').notEmpty();
		request.checkBody('email', 'email must be a valid address.').isEmail();
		request.checkBody('fbAuth', 'fbAuth is required.').notEmpty();
		request.checkBody('avatar', 'avatar is required.').notEmpty();
		var errors = request.validationErrors();
		if ( errors ) {
			console.log( errors );
			api.JsonResponse( errors, response, 400 );
		}
}


//*******************************************
//* API CALLS
//*******************************************
exports.bind = function( app ) {
	// Register
	app.post('/api/user/signup', function( request, response ) {
	  validateSignupParams( request );

	  var newUser = new User({
		  firstName:           request.body.firstName,
		  lastName:            request.body.lastName,
		  email:               request.body.email,
		  fbAuth:              request.body.fbAuth,
		  fbUserId:            request.body.fbUserId,
		  avatar:              request.body.avatar
	  });

    User.findOne({ email: newUser.email})
    .or({fbAuth: newUser.fbAuth})
    .or({fbUserId: newUser.fbUserId},
      function( error, existingUser ) {
        if ( error ) {
          console.log( error );
          JsonResponse( error, response, 500 );
          return;
        }
        if ( existingUser ) {
          console.log( 'User exists' );
          JsonResponse( 'Previously registered user.', response, 400 );
          return;
        }
        newUser.authToken = newUser.GetAuthToken();

        newUser.save( function( error, user ) {
          if ( error ) {
            console.log( error );
            JsonResponse( error, response, 500 );
            return;
          }
          JsonResponse( user, response, 200 );
        });
      });
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