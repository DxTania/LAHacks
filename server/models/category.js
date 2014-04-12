//*******************************************
//* CATEGORY MODEL
//*******************************************
var mongoose = require('mongoose')
  , Schema = mongoose.Schema;


var CategorySchema = new Schema({
  name:           		{ type: String, default: '' },
  subCategories: 		 [{ type: Schema.ObectId, ref : 'Category' }]
});

