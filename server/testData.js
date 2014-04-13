var server = require('./server.js'),
    mongoose = require('mongoose'),
    fs = require('fs'),
    _ = require ('underscore'),
    Category = mongoose.model('Category'),
    Comment = mongoose.model('Comment'),
    Follow = mongoose.model('Follow'),
    Like = mongoose.model('Like'),
    Place = mongoose.model('Place'),
    Post = mongoose.model('Post'),
    Transaction = mongoose.model('Transaction'),
    User = mongoose.model('User');

require('./globals.js');

// mongoose.connect( CONFIG.db );
// // Handle errors
// mongoose.connection.on( 'error', function(err) {
//     console.log(err);
// });

mongoose.set('debug', true);
function saveReport( object ) {
  object.save( function( err ) {
    if ( err ) {
      console.log( err );
    }
  })
}

// Test user setup

var testUser1 = {
	firstName: "Tania",
	lastName: "Depasquale",
	email: "rawrtan@gmail.com",
	rating: 5,
  numRatings: 100,
	fbAuth: 'randomstuff',
	avatar: 'https://fbcdn-profile-a.akamaihd.net/hprofile-ak-ash3/t1.0-1/p320x320/1925248_473960559382783_1046490155_n.jpg'
},  testUser2 = {
  firstName: "Tylor",
  lastName: "Louis",
  email: "tylorlouis@ucla.edu",
  rating: 5,
  numRatings: 3,
  fbAuth: 'randomstuff',
  avatar: 'sleepy.png'
}, testUser3 = {
  firstName: "Sean",
  lastName: "Zarringhalam",
  email: "sean@sean.com",
  rating: 5,
  numRatings: 80,
  fbAuth: 'randomstuff',
  avatar: 'sleepy.png'
};

var tania = new User(testUser1);
saveReport(tania);
var tylor = new User(testUser2);
saveReport(tylor);
var sean = new User(testUser3);
saveReport(sean);


// Test category setup


var testCategorySW = {
  name:                 'software'
},  testCategoryJ = {
  name:                 'junk'
};

var CategorySW = new Category(testCategorySW);
saveReport(CategorySW);
var CategoryJ = new Category(testCategoryJ);
saveReport(CategoryJ);

var testPost1 = {
  title:              "LAHacks sourcecode.",
  description:        "Selling this brand new LAHacks sourcecode! It's a bit young, so handle with care.",
  obo:                true,
  price:              5499,
  curPrice:           null,
  meetTypes:         ["public", "deliver"],
  status:             'open',
  sale:               null,
  // images:            ['http://www.gadgetfactory.net/wp-content/uploads/2013/07/binary.jpg', 'http://static2.businessinsider.com/image/509802cb69bedd6209000009/nicolas-cage-will-be-in-the-expendables-3.jpg' ]
}, testPost2 = {
  title:              "LAHacks Trophy.",
  description:        "It was shiny so I got it, but it's kinda ugly so I'm over it. Pickup only because it's huge.",
  obo:                false,
  price:              .99,
  curPrice:           null,
  meetTypes:         ["pickup"],
  // user
  // categories
  // transactions
  status:             'open',
  sale:               null,
  // images:            ['http://static2.businessinsider.com/image/509802cb69bedd6209000009/nicolas-cage-will-be-in-the-expendables-3.jpg', 'http://www.gadgetfactory.net/wp-content/uploads/2013/07/binary.jpg' ],
  // Likes
  // follows
};

var appPost = new Post(testPost1);
var trophyPost = new Post(testPost2);

var testComment = {
  targetType:          "post",
  // user
  // target
  userName:             "Tylor Louis",
  // avatar:               "http://driph.com/words/wp-content/uploads/2008/04/e.gif",
  comment:              "Trololol... Hi everyone :D"
};
var trollComment = new Comment(testComment);

var testFollow = {
  targType:             "user",
  // target
  // user
  userName:             "Tylor Louis"
};
var aFollow = new Follow(testFollow);


var testLike = {
  // post
  // user
  userName:             "Sean Zarringhalam"
};
var aLike = new Like(testLike);

var testPlaceB = {
  meetTypes:        ['public', 'deliver', 'pickup'],
  rating:           5.0,
  loc:              [-118.4440560, 34.0702640],
  name:             'Boelter Hall'
}, testPlaceP = {
  meetTypes:          ['pickup'],
  rating:             3.0,
  loc:                [-118.4452700, 34.0737030],
  name:               'Pauley Pavillion'
};
var Boelter = new Place(testPlaceB);
saveReport(Boelter);
var Pauley = new Place(testPlaceP);
saveReport(Pauley);

var testTransaction = {
  // post: testPost2
  amount: 99,
  status: 'open',
  // place:    testPlaceP,
  meetType: 'pickup'
};
var aTrans = new Transaction(testTransaction);


// Linking
aFollow.target = tania;
aFollow.user = tylor;
saveReport(aFollow);

trollComment.user = tylor;
trollComment.target = trophyPost;
saveReport(trollComment);


aTrans.bidUser = tania;
aTrans.post = trophyPost;
aTrans.place = Pauley;
saveReport(aTrans);

aLike.user = sean;
aLike.post = trophyPost;
saveReport(aLike);

appPost.user = tania;
appPost.userName = tania.firstName + " " + tania.lastName;
appPost.categories = [CategorySW];
saveReport(appPost);
var secondAppPost = appPost;
saveReport(secondAppPost);

trophyPost.user = tylor;
trophyPost.userName = tylor.firstName + " " + tylor.lastName;
trophyPost.categories = [CategoryJ];
trophyPost.transactions = [aTrans];
trophyPost.likes = [aLike];
console.log(trophyPost);
saveReport(trophyPost);

console.log("Successfully created dataz!");
