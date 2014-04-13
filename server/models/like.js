//*******************************************
//* LIKE MODEL
//*******************************************
var mongoose = require('mongoose')
  , Schema = mongoose.Schema;


var LikeSchema = exports.Like = new Schema({
  post: 			{ type: Schema.ObjectId, ref : 'Post' },
  user:  			{ type: Schema.ObjectId, ref : 'User' },
  userName:   { type: String, default : ''}
});

// Required Like validations
LikeSchema.path('user').required(true, 'user cannot be blank');



// Exported MODEL
mongoose.model('Like', LikeSchema, 'likes');