//*******************************************
//* CATEGORY MODEL
//*******************************************
var mongoose = require('mongoose')
  , Schema = mongoose.Schema;


var CategorySchema = exports.Category = new Schema({
  name:           		{ type: String, default: '' },
  subCategories: 		 [{ type: Schema.ObjectId, ref : 'Category' }]
});

// Required Category validations
CategorySchema.path('name').required(true, 'name cannot be blank');



// Exported MODEL
var Category = module.exports = mongoose.model('Category', CategorySchema, 'categories');