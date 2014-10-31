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

      '' 								:'index',
      'categoria/new':'adicionarCategoria',
      'categorias':'categorias',
//      'categoria/:categoriaId/denuncia':'adicionarDenuncia'
      'denuncia/new':'adicionarDenuncia'
    },

  });

  return new Router({
	  controller: mainController
  })
});