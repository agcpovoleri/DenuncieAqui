define(['backbone'],
		function(Backbone){

	var Geolocation = Backbone.Model.extend({
		
		defaults: {
			id: null,
			latitude: undefined,
			longitude: undefined,
			altitude: undefined,
			accuracy: undefined,
			altitude_accuracy: undefined,
			heading: undefined,
			speed: undefined,
			timestamp: undefined,
		}	
	});

	return Geolocation;
});

