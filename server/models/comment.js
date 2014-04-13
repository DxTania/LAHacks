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

var CommentSchema = exports.Comment = new Schema({
  targetType:         { type: String, enum: validTargets },
  target:             { type: Schema.Types.Mixed, default: null },                                // Rating or a Post
  user:               { type: Schema.ObjectId, ref : 'User', default: null },
  userName:           { type: String, default : '' },
  avatar:             { trype: String, default : '' },
  comment:            { type: String, default : '' },
  status:             { type: String, enum: validStatuses}
});


// Required Comment validations
CommentSchema.path('user').required(true, 'user cannot be blank');
CommentSchema.path('comment').required(true, 'comment cannot be blank');


// Exported MODEL
mongoose.model('Comment', CommentSchema, 'comments');