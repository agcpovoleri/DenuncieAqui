define(['backbone','appConfig','backboneValidation'],
		function(Backbone, AppConfig, backboneValidation){

	var Categoria = Backbone.Model.extend({

		urlRoot: function(){
			return AppConfig.globalUrl + "/categoria";
		},

		idAttribute: "id",

		//default attributes 
		defaults:{
			id:null,
			nome: undefined,
			descricao: undefined,
			anonimato:false,
			idpai: undefined
			
		},
		validation: {
            nome: {
                required: true,
                msg: 'Please enter a "nome"'
            },
            descricao: {
                required: true,
                msg: 'Please enter a "description"'
            }
        },

		initialize: function(){
			console.log('This model has been initialized.');
			
			this.on("invalid", function(model, error){
				console.log(error);
			});
		}
	});

	return Categoria;
});

