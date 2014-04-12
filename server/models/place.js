//*******************************************
//* PLACE MODEL
//*******************************************
var mongoose = require('mongoose')
  , Schema = mongoose.Schema;

// Place Meeting type validator
var validMeetType = {
  values: ['public', 'deliver', 'pickup'],
  message: 'enum validator failed for path `{PATH}` with value `{VALUE}`'
};

var PlaceSchema = new Schema({
  meetTypes:           [{ type: String, enum: validMeetType }],     // Guess location type home/public by most often occurred
  rating:              { type: Number, default: 0 },
  // TODO: Decide how/if we're saving places
  // TODO: Check syntax of Mongoose GeoJSON fields
  // Note that you should always store longitude first!
  loc:                 [ ],
  // TODO: Incorporate google places API data
  // place:
  // placeCat:
});