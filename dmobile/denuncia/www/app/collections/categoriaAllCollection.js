define(['backbone','models/categoria/categoria','appConfig'],

		function (Backbone, Categoria, AppConfig){
	'use strict';

	var CategoriaAllCollection = Backbone.Collection.extend({
		
		model: Categoria,
		
		url: function(){
			return AppConfig.globalUrl + "/categoria/all";
		}
		
	});
	
	return CategoriaAllCollection;
});


