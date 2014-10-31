define(['backbone','models/categoria/categoria','appConfig'],

		function (Backbone, Categoria, AppConfig){
	'use strict';

	/*
	 * Representa o carrinho de compras com o calculo dos valores 
	 */
	var CategoriaFilterCollection = Backbone.Collection.extend({
		
		
		model: Categoria,
		
		initialize: function(options) {
	        
			var ids = options.idcategoria || [];
	        if (ids.length > 0) { 
	            this.fetchByIds(ids);
	        }
	    },
	    
	    fetchByIds: function(ids) {
	        // Save a reference to the existing url
	        var baseUrl = this.url;
	        // Assign our batch url to the 'url' property and call the server
	        this.url = AppConfig.globalUrl + "/categoria/filter?idCategoria=" + ids;
	        
	        this.fetch();

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
	
	return CategoriaFilterCollection;
});


