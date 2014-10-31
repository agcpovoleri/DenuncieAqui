define(['backbone','appConfig','backboneValidation'],
		function(Backbone, AppConfig, backboneValidation){

	var Denuncia = Backbone.Model.extend({

		urlRoot: function(){
			return AppConfig.globalUrl + "/denuncia";
		},

		idAttribute: "id",

		//default attributes 
		defaults:{
			id:null,
			data: undefined,
			descricao: undefined,
			idcategoria: undefined
			
		},
		validation: {
            descricao: {
                required: true,
                msg: 'Please enter a "description"'
            },
            idcategoria: {
                required: true,
                msg: 'Please set a "category"'
            }
        },

		initialize: function(){
			console.log("This model 'Denuncia' has been initialized.");
			
			this.on("invalid", function(model, error){
				console.log(error);
			});
		}
	});

	return Denuncia;
});

