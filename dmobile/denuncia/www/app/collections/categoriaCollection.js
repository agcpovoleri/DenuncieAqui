define(['backbone','models/categoria/categoria','appConfig'],

		function (Backbone, Categoria, AppConfig){
	'use strict';

	
	var CategoriaCollection = Backbone.Collection.extend({
		
		
		model: Categoria,
		
		url: function(){
			return AppConfig.globalUrl + "/categoria";
		},
		
		parse: function(response) {
			console.log(response);
			return response;
		},
		
	});
	
	return CategoriaCollection;
});


