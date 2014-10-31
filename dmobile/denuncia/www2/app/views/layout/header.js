define(['marionette',
        'text!templates/layout/header.html'], 
function(Marionette, headerViewTemplate){
	'use strict';
	var HeaderView = Marionette.ItemView.extend({

		//initialize template
		template:_.template(headerViewTemplate),
		
		events: {
			'click ul.navbar-nav.navItem' : 'selectNavItem'
		},
		
		selectNavItem: function(e){
			var self = this;
			e.preventDefault();
			alert($(e.target).attr('href'));
		}

	});
	return HeaderView;
});