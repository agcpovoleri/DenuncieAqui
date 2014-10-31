define(['backbone','models/categoria/categoria','appConfig'],

		function (Backbone, Categoria, AppConfig){
	'use strict';

	/*
	 * Representa o carrinho de compras com o calculo dos valores 
	 */
	var CategoriaCollection = Backbone.Collection.extend({
		
		
		model: Categoria,
		
		url: function(){
			return AppConfig.globalUrl + "/categoria";
		},
		
		parse: function(response) {
			console.log(response);
			return response;
		},
		
//		getById:function(idEstabecimento){
//			var self = this;
//			var nShop = new Estabelecimento({id: idEstabecimento});
//			$.when(new Estabelecimento({id: idEstabecimento})).then(function(nShop){
//				self.trigger("fetchCompleted:InfoShop");
//				return nShop;
//			});
//		},

//		initialize: function () {
//			_.bindAll(this, "totalPrice");
//		},
//
//		// calculate total price
//		totalPrice: function () {
//			// underscore.js's **reduce** function is used
//			return this.reduce(function(memo, value) { 
//				return memo + (value.get("quantidade") * value.get("valor")) }, 0);
//		}
	});
	
	return CategoriaCollection;
});


