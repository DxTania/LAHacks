//*******************************************
//* COMMENT MODEL
//*******************************************
var mongoose = require('mongoose')
  , Schema = mongoose.Schema;


// Validator for the status enumeration
var validStatuses = {
  values: ['open', 'deleted'],
  message: 'enum validator failed for path `{PATH}` with value `{VALUE}`'
};

// Validator for the comment target enumeration
var validTargets = {
  values: ['post', 'rating'],
  message: 'enum validator failed for path `{PATH}` with value `{VALUE}`'
}

var CommentSchema = new Schema({
  targetType:         { type: String, enum: validTargets },
  targPost:           { type: Schema.ObjectId, ref : 'Post' },
  targRating:         { type: Schema.ObjectId, ref : 'Rating', defulat: null },
  user:               { type: Schema.ObjectId, ref : 'User', default: null },
  userName:           { type: String, default : '' },
  avatar:             { trype: String, default : '' },
  comment:            { type: String, default : '' },
  status:             { type: String, enum: validStatuses}
});