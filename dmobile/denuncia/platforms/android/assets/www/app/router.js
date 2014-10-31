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

      '' 							:'index',
      'home' 						:'index',
//      'categoria/new'				:'adicionarCategoria',
      'categoria/new'				:'adicionarCategoria',
      
      'categorias'					:'categorias',
      
      'filtrarCategoria/:idcategoria':'filtrarCategorias',
      
//      'categoria/:categoriaId/denuncia':'adicionarDenuncia'
      'denuncia/new(/:idcategoria)'	:'adicionarDenuncia',
      'agradecimentoParticipacao'	:'agradecimentoParticipacao'
    },

  });

  return new Router({
	  controller: mainController
  })
});