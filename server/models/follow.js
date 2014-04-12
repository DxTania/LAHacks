//*******************************************
//* LIKE MODEL
//*******************************************
var mongoose = require('mongoose')
  , Schema = mongoose.Schema;


var validTargets = {
  values: ['post', 'user'],
  message: 'enum validator failed for path `{PATH}` with value `{VALUE}`'
}

var FollowSchema = new Schema({
  targType:   { type: String, enum : validTargets },
  // Either targUser or targPost is empty
  // TODO: Learn how to ACTUALLY implement this sort of "polymorphism" in mongoose
  targUser: 	{ type: Schema.ObjectId, ref : 'User', default: null },
  targPost: 	{ type: Schema.ObjectId, ref : 'Post', default: null },
  user:  			{ type: Schema.ObjectId, ref : 'User' },
  userName:   { type: String, default : ''},
});