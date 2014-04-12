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
  fbAuth:              { type: String, default: '' },
  rating:              { type: Number, default: 0 },
  fbAuth:              { type: String, default: '' },
  avatar:              { type: String, default: '' },
  interest:           [{ type: String, default: '' }],    // List of category names
  sellTypes:          [{ type: String, default: '' }],    // List of most often sold categories
  fbAuth:              { type: String, default: '' },
  wishlist:           [{ type: Schema.ObectId, ref : 'Post' }],
  following:          [{ type: Schema.ObectId, ref : 'User' }]
});

UserSchema.methods = {

};

/**
 * Validations
 */
