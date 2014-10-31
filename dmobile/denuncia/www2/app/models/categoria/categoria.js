define(['backbone','appConfig'],
	function(Backbone, AppConfig){

	var Categoria = Backbone.Model.extend({
    
		urlRoot: function(){
			return AppConfig.globalUrl + "/categoria";
		},
		
		idAttribute: "id",

		//default attributes 
		defaults:{
			id:null,
			nome:'',
			descricao:null,
			anonimato: false,
			idPai: null
		}
	});

	return Cardapio;
});

