/*
* The router defines routes and their corresponding methods in the controller.
*/
/*jslint browser: true*/
define([

  // Libraries.
  'marionette',

   // Modules.
  'controllers/mainController'
  
], function(Marionette, mainController) {
  'use strict';

  var Router = Marionette.AppRouter.extend({

    appRoutes: {

      '' 					:'index',
      'sugestaoCategoria'	:'sugestaoCategoria'
      
    },

  });

  return new Router({
	  controller: mainController
  })
});