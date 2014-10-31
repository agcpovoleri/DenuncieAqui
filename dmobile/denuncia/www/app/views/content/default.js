define(['marionette',
        'app','vent',
        'text!templates/content/default.html',
        'text!templates/content/denuncia/insertDefaultCategoriaSelectTemplate.html'
        ], 
function(Marionette, 
		App, vent,
		defaultViewTemplate, 
		insertDenunciaCategoriaSelectTemplate){
	'use strict';
	
	var aItemView = Marionette.ItemView.extend({
		template: _.template("<%=nome%>"),
		tagName: "option",
		onRender: function(){
			this.$el.attr('value', this.model.get('id'));
		}
	});

	var aCompositeView = Marionette.CompositeView.extend({
		template: _.template(insertDenunciaCategoriaSelectTemplate),  
		itemView: aItemView,  
		itemViewContainer: 'select#categoriaSelect',
	});
	
	var DefaultView = Marionette.ItemView.extend({

		//initialize template
		template:_.template(defaultViewTemplate),
		
		initialize: function(){
			var self = this;
			vent.on("vent:defaultView:filtrarCategoria", function(params){
				self.changePage(params.idcategoria);
			});
			
			this.collection.fetch({
				success: function(){
					
				},
				error: function(model, xhr, options){
					alert('Verifique sua conexão com a internet!')
					console.log(xhr.responseText);
				}
			}).then(function(){
				var compositeView = new aCompositeView({collection : self.collection, el: "#divCategoriaSelect"});
		    	compositeView.render();
			});
		},
		
		events: {
			'click #categoriaFilter' : 'filtrarCategorias'
		},
		
		changePage: function(idcategoria){
			var App = require('app');
			
			var url = 'filtrarCategoria/'+idcategoria;
			App.Router.navigate(url, { trigger: true });
			
		},
		
		filtrarCategorias: function(e){
			e.preventDefault();
			
			var idcategoria = ($("select#categoriaSelect").val() !== "" ? parseInt($("select#categoriaSelect").val()): undefined);
			if (idcategoria !== undefined){
				vent.trigger("vent:defaultView:filtrarCategoria",{idcategoria: idcategoria});
			}else
				return false;
		},
		
		selectNavItem: function(e){
			var self = this;
			e.preventDefault();
			alert($(e.target).attr('href'));
		},
		
		
		
	});
	return DefaultView;
});