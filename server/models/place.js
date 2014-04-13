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
  meetTypes:          [{ type: String, enum: validMeetType }],     // Guess location type home/public by most often occurred
  rating:              { type: Number, min: 0, max: 5, default: 5 },
  // TODO: Decide how/if we're saving places
  // TODO: Check syntax of Mongoose GeoJSON fields
  // Note that you should always store longitude first!
  loc:                { type: [Number], index: '2dsphere'},
  name:               { type: String, default: '' }
  // TODO: Incorporate google places API data
  // place:
  // placeCat:
});

// Required Place validations
PlaceSchema.path('loc').required(true, 'loc cannot be blank');


// Exported MODEL
mongoose.model('Place', PlaceSchema, 'places');
