//*******************************************
//* TRANSACTION MODEL
//*******************************************

var mongoose = require('mongoose')
  , Schema = mongoose.Schema
  , utils = require('../lib/utils');

// Transaction status validator
var validStatuses = {
  values: ['open', 'waiting', 'closed', 'success', 'fail'],
  message: 'enum validator failed for path `{PATH}` with value `{VALUE}`'
};

// Transaction Meeting type validator
var validMeetType = {
  values: ['public', 'deliver', 'pickup'],
  message: 'enum validator failed for path `{PATH}` with value `{VALUE}`'
};

var TransactionSchema = new Schema({
  post:           		 { type: Schema.ObectId, ref : 'Post' },
  amount:      			   { type: Number, default: 0 },
  status:              { type: String, enum: validStatuses },
  place:               { type: Schema.ObectId, ref: "Place" },
  placeRating:         { type: Number, min: 0, max: 5, default: null },           // This transaction's specific location rating
  overallRating:       { type: Number, min: 0, max: 5, default: null },           // Same value that should be used to calculate the user rating
  meetType:            { type: String, enum: validMeetType },
  createdAt:           { type: Date, default: Date.now},
  closedAt:            { type: Date, default: null}
});

// Required Post validations
PostSchema.path('post').required(true, 'post cannot be blank');
PostSchema.path('amount').required(true, 'amount cannot be blank');
PostSchema.path('place').required(true, 'place cannot be blank');
PostSchema.path('images').required(true, 'images cannot be blank');



TransactionSchema.methods = {

};