define(['backbone',
        'appConfig','backboneValidation'],
		function(Backbone,
				AppConfig, backboneValidation){

	var UploadForm = Backbone.Model.extend({

		urlRoot: function(){
			return AppConfig.globalUrl + "/denuncia/imageUpload";
		},

		defaults:{
			files: [],
		},
	});

	return UploadForm;
});

