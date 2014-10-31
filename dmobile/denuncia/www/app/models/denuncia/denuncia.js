define(['backbone',
        'models/media/geolocation',
        'appConfig','backboneValidation'],
		function(Backbone, 
				Geolocation,
				AppConfig, backboneValidation){

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
			idcategoria: undefined,
			localizacao: Geolocation
			
		},
		validation: {
            descricao: {
                required: true,
                msg: 'Por favor selecione uma descricao'
            },
            idcategoria: {
                required: true,
                msg: 'Por favor selecione uma categoria'
            }
        },

		initialize: function(){
//			console.log("This model 'Denuncia' has been initialized.");
			this.on("invalid", function(model, error){
				console.log(error);
			});
		}
	});

	return Denuncia;
});

