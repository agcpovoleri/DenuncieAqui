define(['backbone','models/categoria/categoria'],

		function (Backbone, Categoria){
	'use strict';

	/*
	 * Representa o carrinho de compras com o calculo dos valores 
	 */
	var CategoriaCollection = Backbone.Collection.extend({
		
		model: Categoria,

		initialize: function () {
			_.bindAll(this, "totalPrice");
		},

		// calculate total price
		totalPrice: function () {
			// underscore.js's **reduce** function is used
			return this.reduce(function(memo, value) { 
				return memo + (value.get("quantidade") * value.get("valor")) }, 0);
		}
	});
	
	return CategoriaCollection;
});


