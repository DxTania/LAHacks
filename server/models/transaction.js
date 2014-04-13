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
  post:           		 { type: Schema.ObjectId, ref : 'Post' },
  amount:      			   { type: Number, default: 0 },
  status:              { type: String, enum: validStatuses },
  place:               { type: Schema.ObjectId, ref: "Place" },
  placeRating:         { type: Number, min: 0, max: 5, default: null },           // This transaction's specific location rating
  overallRating:       { type: Number, min: 0, max: 5, default: null },           // Same value that should be used to calculate the user rating
  meetType:            { type: String, enum: validMeetType },
  createdAt:           { type: Date, default: Date.now},
  closedAt:            { type: Date, default: null}
});

// Required Post validations
TransactionSchema.path('post').required(true, 'post cannot be blank');
TransactionSchema.path('amount').required(true, 'amount cannot be blank');
TransactionSchema.path('place').required(true, 'place cannot be blank');




// Exported MODEL
var Transactions = module.exports = mongoose.model('Transactions', TransactionSchema, 'transactions');