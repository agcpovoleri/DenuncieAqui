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
        'views/content/categoria/categoriasView',
        'views/content/categoria/insertCategoriaView',
        'views/content/denuncia/insertDenunciaView',
        
        'collections/categoriaCollection',

        'models/categoria/categoria',
        'models/denuncia/denuncia'

        ], function(
        		App, session, 
        		HeaderView,
        		
        		CategoriasView,
        		InsertCategoriaView,
        		InsertDenunciaView,
        		
        		CategoriaCollection,
        		Categoria,
        		Denuncia) {
	'use strict';

	return {

		// The main page.
		index: function() {
			console.log('called index-controller');

		},

		categorias: function(){
			
			var categoriasView = new CategoriasView({collection: new CategoriaCollection() });
			categoriasView.bind('renderCompleted:Categorias', App.regionMain.show(categoriasView.render()), this);
			categoriasView.getAll();
			
		},
		
		// The main page.
		adicionarCategoria: function() {
			console.log('called adicionar-controller');
			// Inject the main layout into the #main region of the page.
			var layout = new InsertCategoriaView({ model: new Categoria() });
			App.regionMain.show(layout);
			
		},

		
		adicionarDenuncia: function() {
			
			var layout = new InsertDenunciaView({ model: new Denuncia() });
			App.regionMain.show(layout);

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
