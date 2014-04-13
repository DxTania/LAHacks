var mongoose = require('mongoose'),
    api = require(API_CORE),
    Post = mongoose.model('Post');

function validatePostParams( request, response ) {
    request.checkBody('title', 'title is required.').notEmpty();
    request.checkBody('description', 'description is required.').notEmpty();
    request.checkBody('obo', 'obo is required.').notEmpty();
    request.checkBody('price', 'price is required.').notEmpty();
    request.checkBody('meetTypes', 'meetTypes is required.').notEmpty();
    request.checkBody('user', 'user is required.').notEmpty();
    request.checkBody('userName', 'userName is required.').notEmpty();
    request.checkBody('categories', 'categories is required.').notEmpty();
    // request.checkBody('images', 'images is required.').notEmpty();
    var errors = request.validationErrors();
    if ( errors ) {
      console.log( errors );
      api.JsonResponse( errors, response, 400 );
    }
    return errors || null;
}

//*******************************************
//* API CALLS
//*******************************************
exports.bind = function( app ) {

	// Create Post
	app.post('/api/post/create', function( request, response ) {
    console.log(request);
    console.log(request.body);
    var testData = {
      title:        request.params('title',
      description:  request.params('description',
      obo:          request.params('obo',
      price:        request.params('price',
      meetTypes:    request.params('meetTypes',
      user:         request.params('user',
      userName:     request.params('userName',
      categories:   request.params('categories',
      images:       request.params('images'
    }
    console.log("test data");
    console.log(testData);

    if ( validatePostParams( request, response ) ) return;
    var newPost = new Post({
      title:        request.body.title,
      description:  request.body.description,
      obo:          request.body.obo,
      price:        request.body.price,
      meetTypes:    request.body.meetTypes,
      user:         request.body.user,
      userName:     request.body.userName,
      categories:   request.body.categories,
      images:       request.body.images
    });

    newPost.save( function( error, post ) {
        if ( error ) {
          console.log( error );
          api.JsonResponse( error, response, 500 );
          return;
        }
        api.JsonResponse( post, response, 200 );
    });
	});

// curl --data "title=PostedPost&description=SooooMeta&obo=true&price=50&meetTypes=['public']&user=534a66b105957a57474cfc1f&userName=Tylor Louis&categories=['junk']&images=['heyo.jpg']"

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