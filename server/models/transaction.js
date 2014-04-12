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
  locationRating:      { type: Number, default: null },           // Later used to determine safety of locations, maybe remove this field, move to Place
  overallRating:       { type: Number, default: null },           // Same value that should be used to calculate the user rating
  placeRating:         { type: Number, default: null },           // This transaction's specific location rating
  place:               { type: Schema.ObectId, ref: "Place" },
  meetType:            { type: String, enum: validMeetType },
  createdAt:           { type: Date, default: Date.now},
  closedAt:            { type: Date, default: null}
});

TransactionSchema.methods = {

};

/**
 * Validations
 */
