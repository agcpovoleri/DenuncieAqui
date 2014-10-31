define(['marionette',
        'text!templates/content/default.html'], 
function(Marionette, defaultViewTemplate){
	'use strict';
	
	var DefaultView = Marionette.ItemView.extend({

		//initialize template
		template:_.template(defaultViewTemplate),
		
		events: {
			
		},
		
		selectNavItem: function(e){
			var self = this;
			e.preventDefault();
			alert($(e.target).attr('href'));
		}

	});
	return DefaultView;
});