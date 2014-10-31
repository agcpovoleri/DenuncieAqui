define(['marionette',
        'text!templates/content/categoria/categoriasTemplate.html',
        'text!templates/content/categoria/itemCategoriaTemplate.html'], 
function(Marionette, 
		categoriasViewTemplate, itemCategoriaTemplate){
	'use strict';
	
	var ItemCategoriaView = Marionette.ItemView.extend({
		//initialize template
		template: _.template(itemCategoriaTemplate),
		
		
	});


	var CategoriasView = Marionette.CompositeView.extend({

		itemView: ItemCategoriaView,

		// specify a jQuery selector to put the itemView instances in to
		itemViewContainer: "ul#content_list",

		template: _.template(categoriasViewTemplate),

		update: function(){
			//set callback of the event "fetchCompleted:Books" 
			this.collection.bind('renderCompleted:Categorias', this.render, this);
			this.collection.fetch();
		},

		getAll: function(){
			//set callback of the event "fetchCompleted:Books" 
			//this.collection.bind('fetchCompleted:Shops', this.render, this);
			var self = this;
			this.collection.fetch({
				success: function(){
					console.log('CategoriaView: Success fetching estabelecimentos');
					self.trigger("renderCompleted:Categorias",this);
				},
				error: function(collection, xhr, options){
					alert('Error fetching Categorias:' + xhr.responseText);
					console.log(xhr.responseText);
				},
				complete: function(xhr, textStatus) {
					console.log(textStatus);
				}
			});
		},


		onRender: function(){
			
		},
		
		onShow: function(){
//			$.listview('refresh');

		}
	});
	return CategoriasView;
});