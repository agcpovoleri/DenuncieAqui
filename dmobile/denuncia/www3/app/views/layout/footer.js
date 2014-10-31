define(['marionette',
        'text!templates/layout/footer.html'], 
function(Marionette, footerViewTemplate){
	'use strict';
	var FooterView = Marionette.ItemView.extend({

		//initialize template
		template:_.template(footerViewTemplate)

	});
	return FooterView;
});