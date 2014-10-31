/* 
 * The controller is basically just a collection of methods that correspond
 * to routes and are called by the application router.
 */
define([

        // Application.
        'app',
        // Misc.
        'session',

        'views/layout/header',
        
        'collections/categoriaCollection',

        'models/categoria/categoria'

        ], function(
        		App, session, 
        		HeaderView, 
        		CategoriaCollection,
        		Categoria) {
	'use strict';

	return {

		// The main page.
		index: function() {
			console.log('called index-controller');

		},

		// The main page.
		sugestaoCategoria: function() {
			console.log('called sugestaoCategoria-controller');
			// Inject the main layout into the #main region of the page.
//			var layout = new MainView();
//			App.regionMain.show(layout);
		},

		
		listViewShop: function() {
			
//			var positionLayout =  new LayoutEstabelecimentos();
//			App.regionMain.show(positionLayout);
//			
//			console.log('called listshop-controller');
//			var estabelecimentoListView = new ListShopView({collection: new EstabelecimentoCollection() });
//			estabelecimentoListView.bind('renderCompleted:Shops', positionLayout.content.show(estabelecimentoListView.render()), this);
//			estabelecimentoListView.getAll();
		},
		
		// Redirects to the login screen if the user is not logged in.
		isAuthenticated: function() {
			if (!session.authenticated()) {
				App.Router.navigate('login', { trigger: true });
			}
		},


		missao: function(){

		},
		visao: function(){

		},
		valores: function(){

		}
	};

});
