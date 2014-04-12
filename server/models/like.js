//*******************************************
//* LIKE MODEL
//*******************************************
var mongoose = require('mongoose')
  , Schema = mongoose.Schema;


var LikeSchema = new Schema({
  post: 			{ type: Schema.ObjectId, ref : 'Post' },
  user:  			{ type: Schema.ObjectId, ref : 'User' },
  userName:   { type: String, default : ''}
});

// Required Like validations
LikeSchema.path('user').required(true, 'user cannot be blank');