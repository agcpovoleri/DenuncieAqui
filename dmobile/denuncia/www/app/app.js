define([
        // Libraries.
        'marionette',
        
        'views/content/default',
        'views/layout/header',
        'views/layout/footer',
        
        'collections/categoriaCollection'], 
        function(Marionette,
        		
        		DefaultView,
        		HeaderView,
        		FooterView,

        		CategoriaCollection
        ) {
	'use strict';
	

	/* ======================================================================== */

	// Creates a new Marionette application.
	var App = new Backbone.Marionette.Application();

	function isMobile() {
        var ua = (navigator.userAgent || navigator.vendor || window.opera, window, window.document);
        return (/iPhone|iPod|iPad|Android|BlackBerry|Opera Mini|IEMobile/).test(ua);
    }

    App.mobile = isMobile();
    //alert("Running in a "+((App.mobile)?"Mobile ":"Desktop ") + "device");
	
	// Add the main region, that will hold the page layout.
	App.addRegions({
		regionHeader: 	'#headerContent',
		regionMain: 	'#mainContent',
		regionFooter: 	'#footerContent'
	});

	// Adds any methods to be run after the app was initialized.
	App.addInitializer(function() {
		this.initAppLayout();
	});

	// Start backbone's history for hash navigation after the app was initialized.
	App.on('initialize:after', function() {
		
		$.ajaxSetup({ cache: true });
		Backbone.history.start({silent:false});
		
//		this.initAppLayout();
	})

	// The main initializing function sets up the basic layout and its regions.
	App.initAppLayout = function() {
		
//		var AppLayout = Backbone.Marionette.Layout.extend({
//			
//			template: _.template(defaultTemplate),	
//			regions: {
//				regionError: '#error', // Contains any error messages.
//				regionUserInfo: '#userInfo', // Will contain any user controls (login/logout).
//				regionContent: '#content' // Will contain the page content.
//			}
//		});
		
		var headerViewLayout = new HeaderView();
		headerViewLayout.render();
		App.regionHeader.show(headerViewLayout);
		
		// Inject the main layout into the #main region of the page.
		var default_layout = new DefaultView({collection: new CategoriaCollection()});
		default_layout.render();
		App.regionMain.show(default_layout);

		var footerTemplateView = new FooterView();
		footerTemplateView.render();
		App.regionFooter.show(footerTemplateView);

		// All links with the role attribute set to nav-main will navigate through
		// the application's router.
		$('a[role=nav-main]').click(function(e) {
			e.preventDefault();
			App.Router.navigate($(this).attr('href'), {
				trigger: true
			});
		});
	}

	return App;
});

