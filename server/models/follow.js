//*******************************************
//* LIKE MODEL
//*******************************************
var mongoose = require('mongoose')
  , Schema = mongoose.Schema;


var validTargets = {
  values: ['post', 'user'],
  message: 'enum validator failed for path `{PATH}` with value `{VALUE}`'
}

var FollowSchema = exports.Follow = new Schema({
  targType:   { type: String, enum : validTargets },
  // Either targUser or targPost is empty
  // NOTE: target intended to be a User or a Post
  target: 	  { type: Schema.Types.Mixed, default: null },
  user:  			{ type: Schema.ObjectId, ref : 'User' },
  userName:   { type: String, default : ''}
});

// Required Follow validations
FollowSchema.path('user').required(true, 'user cannot be blank');

// Exported MODEL
var Follow = module.exports = mongoose.model('Follow', FollowSchema, 'follows');