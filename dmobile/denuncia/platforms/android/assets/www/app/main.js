require.config({

	baseUrl: 'lib',
	//path mappings for module names not found directly under baseUrl
	paths: {

		app:					'../app/app',
		router:					'../app/router',

		jquery:     			'jquery/jquery-2.0.2',
		jqueryui:				'jquery/jquery-ui-1.10.4.custom.min',		
		jquerymask:				'jquery/mask/jquery.inputmask',
		
		jquery_picker:			'jquery/template/jquery.fs.picker',
		jquery_scroller:		'jquery/template/jquery.fs.scroller',
		jquery_selecter:		'jquery/template/jquery.fs.selecter',
		jquery_refine_slide:	'jquery/template/jquery.refineslide',
		jquery_raty:			'jquery/raty/jquery.raty',
		
		cordovaFunctions:		'cordova/appCordova',
		
		jqmconfig:				'jqm/jqm-config',
		jqm:     				'jqm/jquery.mobile-1.4.2.min',
		
		bootstrap:				'bootstrap/bootstrap',
		bootbox:				'bootstrap/bootbox',

//		jqvalidator: 			'jquery/jquery.validate.min', 
		jcookie: 				'jquery/jquery.cookie',

		underscore: 			'underscore/underscore-1.6.0',
		text:       			'require/text-2.0.10',
		// Alias for CSS plugin (RequireCSS)
		css:					'require/css',	
			
		backbone:   			'backbone/backbone-1.1.2',
		marionette:   			'backbone/marionette/backbone.marionette',
		wreqr :  				'backbone/marionette/backbone.wreqr',
		babysitter: 			'backbone/marionette/backbone.babysitter',
		backboneValidation:		'backbone/validation/backbone-validation-amd-min',
		analytics:				'backbone/analytics/backbone.analytics',
//		backbone_model_file_upload:	'backbone/fileupload/backbone-model-file-upload',
		
		
		vent:					'backbone/vent',

		controllers:      		'../app/controllers',
		models:      			'../app/models',
		collections:			'../app/collections',
		views:					'../app/views',
		templates:				'../app/templates',
		styles:					'../css',
		
		session:      			'../app/utils/session',
		appConfig:				'../app/utils/config'
				
			
//			basic:			'../lib/api/default',

	},
	shim: {
		underscore: {
			exports: "_"
		},
		backbone: {
			//These script dependencies should be loaded before loading
			//backbone.js
			deps: ['jquery', 'jqueryui', 'underscore'],
			//Once loaded, use the global 'Backbone' as the
			//module value.
			exports: 'Backbone'
		},
		marionette: {
			deps: ['backbone','wreqr','babysitter'],
			exports: 'Backbone.Marionette'
		},
		
//		backbone_model_file_upload: {
//			deps: ['backbone'],
//			exports: 'Backbone'
//		},
		bootstrap: {
			deps: ['jquery'],
            exports: "bootstrap"
        },
        
        bootbox: {
        	deps: ["bootstrap"],
        	exports: "bootbox"
        },
        jquerymask: ['jquery'],
        
        
        jquery_picker: ['jquery'],
        jquery_scroller: ['jquery'],
        jquery_selecter: {
			exports: "$",
			deps: ['jquery']
		},
        jquery_refine_slide: ['jquery'],
        jquery_raty: ['jquery'],
        
		jqueryui: {
			exports: "$",
			deps: ['jquery']
		},
		
		jqmconfig: ['jquery'],
		jqm: ['jquery','jqmconfig']
//		,backboneAnalytics: ['underscore','backbone']
		
		
	},
	config: {
		text: {
			useXhr: function (url, protocol, hostname, port) {
				// allow cross-domain requests
				// remote server allows CORS
				return true;
			}
		}
	},
	waitSeconds: 15,
});

define([
        // Libraries.
        'jquery',
        // The application.
        'app',
        // Misc.
        'router',
        'session'
        ], function($, App, router, session) {
	'use strict';

	// Use jquery's document ready function to start the app as soon as the DOM
	// was fully loaded.
	$(function() {

		App.Router = router;
		App.start();

	});

});