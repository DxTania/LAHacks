//*******************************************
//* POST MODEL
//*******************************************
var mongoose = require('mongoose')
  , Schema = mongoose.Schema;


// Validator for the status enumeration
var validStatuses = {
  values: ['open', 'waiting', 'closed'],
  message: 'enum validator failed for path `{PATH}` with value `{VALUE}`'
};

var PostSchema = new Schema({
  title:              { type: String, default: '', trim : true },
  description:        { type: String, default: '', trim : true },
  obo:                { type: Boolean, default: false },
  price:              { type: Number, default: null },
  curPrice:           { type: Number, default: null },
  user:               { type: Schema.ObjectId, ref : 'User' },
  categories:        [{ type: Schema.ObectId, ref : 'Category' }],
  offers:            [{ type: Schema,ObjectId, ref : 'transaction' }],
  status:             { type: String, enum: validStatuses },
  sale:              [{ type: Schema,ObjectId, ref : 'transaction' }],
  images:            [{ uri:  String, files: []}],
  createdAt:          { type: Date, default: Date.now},
  moddedAt:           { type: Date, default: null},
  soldAt:             { type: Date, default: null},
  likes:             [{ type: Schema,ObjectId, ref : 'like' }],
  follows:           [{ type: Schema,ObjectId, ref : 'follow' }]
});