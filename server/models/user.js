//*******************************************
//* USER MODEL
//*******************************************
var mongoose = require('mongoose')
  , Schema = mongoose.Schema
  , crypto = require('crypto')
  , utils = require('../lib/utils');

var UserSchema = new Schema({
  firstName:           { type: String, default: '' },
  lastName:            { type: String, default: '' },
  email:               { type: String, default: '' },
  rating:              { type: Number, min: 0, max: 5, default: 5 },
  fbAuth:              { type: String, default: '' },
  fbUserId:            { type: String, default: '' },
  authToken:           { type: String, default: '' },
  avatar:              { type: String, default: '' },
  interests:          [{ type: Schema.ObjectId, ref : 'Category' }],    // List of category names
  sellTypes:          [{ type: String, default: '' }],                                // List of most often sold categories
  wishlist:           [{ type: Schema.ObjectId, ref : 'Post' }],
  following:          [{ type: Schema.ObjectId, ref : 'User' }]
});

// Required User validations
UserSchema.path('email').required(true, 'email cannot be blank');
UserSchema.path('fbAuth').required(true, 'fbAuth cannot be blank');
UserSchema.path('avatar').required(true, 'avatar cannot be blank');
UserSchema.path('firstName').required(true, 'firstName cannot be blank');


UserSchema.methods = {

};

/**
 * Validations
 */


// Exported MODEL
var User = module.exports = mongoose.model('User', UserSchema, 'users');