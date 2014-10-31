/* 
 * The controller is basically just a collection of methods that correspond
 * to routes and are called by the application router.
 */
define([

        // Application.
        'app','session',

        'views/layout/header',
        'views/content/default',
        'views/content/categoria/categoriasView',
        'views/content/categoria/insertCategoriaView',
        'views/content/denuncia/insertDenunciaView',
        'views/content/denuncia/agradecimentoParticipacaoView',
        
        'collections/categoriaCollection',
        'collections/categoriaFilterCollection',

        'models/categoria/categoria',
        'models/denuncia/denuncia'

        ], function(
        		App, session, 
        		HeaderView,
        		
        		DefaultView,
        		CategoriasView,
        		InsertCategoriaView,
        		InsertDenunciaView,
        		AgradecimentoParticipacaoView,
        		
        		CategoriaCollection,
        		CategoriaFilterCollection,
        		
        		Categoria,
        		Denuncia) {
	'use strict';

	return {

		// The main page.
		index: function() {
			console.log('called index-controller');
			
			var default_layout = new DefaultView({collection: new CategoriaCollection() });
			App.regionMain.show(default_layout.render());
		},

		categorias: function(){
			
			var categoriasView = new CategoriasView({collection: new CategoriaCollection() });
			categoriasView.bind('renderCompleted:Categorias', App.regionMain.show(categoriasView.render()), this);
			categoriasView.getAll();
			
		},
		
		filtrarCategorias: function(idcategoria){
			var categoriasView = new CategoriasView({collection: new CategoriaFilterCollection({idcategoria: idcategoria}) });
			App.regionMain.show(categoriasView.render());
		},
		
		// The main page.
		adicionarCategoria: function() {
			console.log('called adicionar-controller');
			// Inject the main layout into the #main region of the page.
			var layout = new InsertCategoriaView({ model: new Categoria() });
			App.regionMain.show(layout);
			
		},

		
		adicionarDenuncia: function(idcategoria) {
			console.log('called adicionar-denuncia');
			
			if (idcategoria !== undefined){
				var layout = new InsertDenunciaView({ model: new Denuncia(), idcategoria: idcategoria });
			}else{
				var layout = new InsertDenunciaView({ model: new Denuncia() });
			}
			App.regionMain.show(layout);

		},
		
		agradecimentoParticipacao: function() {
			
			var layout = new AgradecimentoParticipacaoView();
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
