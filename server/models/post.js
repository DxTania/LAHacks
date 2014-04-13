//*******************************************
//* POST MODEL
//*******************************************
var mongoose = require('mongoose')
  , Schema = mongoose.Schema
  , imager = require('imager');


// Validator for the status enumeration
var validStatuses = {
  values: ['open', 'waiting', 'closed'],
  message: 'enum validator failed for path `{PATH}` with value `{VALUE}`'
};

// Post Meeting type validator
var validMeetType = {
  values: ['public', 'delivery', 'pickup'],
  message: 'enum validator failed for path `{PATH}` with value `{VALUE}`'
};

var PostSchema = new Schema({
  title:              { type: String, default: '', trim : true },
  description:        { type: String, default: '', trim : true },
  obo:                { type: Boolean, default: false },
  price:              { type: Number, default: null },
  curPrice:           { type: Number, default: null },
  meetTypes:         [{ type: String, enum : validMeetType }],
  pickupLoc:          { type: [Number], index: '2dsphere', default : null },             // Only if meetTypes includes pickup
  user:               { type: Schema.ObjectId, ref : 'User' },
  userName:           { type: String, default : '' },
  categories:         [{ type: String, default : '' }],
  bids:              [{ type: Schema.ObjectId, ref : 'transaction' }],
  status:             { type: String, enum: validStatuses, default : 'open' },
  sale:               { type: Schema.ObjectId, ref : 'transaction', default : null },
  images:            [{ uri:  String, files: []}],
  createdAt:          { type: Date, default: Date.now },
  moddedAt:           { type: Date, default: null },
  soldAt:             { type: Date, default: null },
  likes:             [{ type: Schema.ObjectId, ref : 'like' }],
  follows:           [{ type: Schema.ObjectId, ref : 'follow' }]
});

// Required Post validations
PostSchema.path('title').required(true, 'title cannot be blank');
PostSchema.path('user').required(true, 'user cannot be blank');
PostSchema.path('categories').required(true, 'categories cannot be blank');
// PostSchema.path('images').required(true, 'images cannot be blank');

PostSchema.methods = {

  // Save post and upload images
  // param: [array] images
  // private

  // uploadAndSave: function( images, cb ) {
  //   if (!images || !images.length) return this.save(cb)


  // }

}

// Exported MODEL
mongoose.model('Post', PostSchema, 'posts');

