define(['marionette',
        'app',
        'models/categoria/categoria',
        'collections/categoriaCollection',
        'text!templates/content/categoria/insertCategoriaTemplate.html',
        'text!templates/content/categoria/insertCategoriaSelectTemplate.html',
        'backboneValidation'], 
function(Marionette, App,
		Categoria,
		CategoriaCollection,
		insertCategoriaTemplate,
		insertCategoriaSelectTemplate){
	
	'use strict';
	
	var aItemView = Marionette.ItemView.extend({
	    template: _.template("<%=nome%>"),
	    tagName: "option",
	    onRender: function(){
	        this.$el.attr('value', this.model.get('id'));
	    }
	});
  
  var aCompositeView = Marionette.CompositeView.extend({
	  template: _.template(insertCategoriaSelectTemplate),  
	  itemView: aItemView,  
	  itemViewContainer: "select"
  });
  
  var InsertCategoriaView = Marionette.ItemView.extend({

	model: Categoria,
	
	//initialize template
    template:_.template(insertCategoriaTemplate),
   
    events : {
    	'click #saveCategoria' 	: 'saveCategoria',
    	"click .rbTipoCategoria": "radioNivelCategoriaSelected"
		
	},

	radioNivelCategoriaSelected : function(e) {
		var self = this;
		//console.log("Events handler for delivery choices");
		switch($(e.target).attr('value'))
		{
		case "principal":
			//Não possui categoria pai
			$("#selectDivCategoria").removeClass('shown').addClass('hidden');
			
			var text1 = "";
			$("select#categoriaPaiSelect option").filter(function() {
			    //may want to use $.trim in here
			    return $(this).val().trim() == text1; 
			}).prop('selected', true);
			
			break;
		case "secundaria":
			//Possui categoria pai
			$("#selectDivCategoria").removeClass('hidden').addClass('shown');
			break;
		default:
			alert("No radio element comparation with attribute value = "+$(e.target).attr('value'));
		}

	},
    
    saveCategoria : function (clickEvent) {
    	clickEvent.preventDefault();
    	
    	$(".form-control").removeClass('errorFormControl');
    	var me = this;
    	var options = {
    	    success: function () {
    	        alert('Thanks for your participation!');
    	        App.Router.navigate('categorias', { trigger: true });
    	        
    	    },
    	    error: function (model, errors) {
    	        console.log('error'+ errors);
    	    }
    	};
    	
    	var key = $('input[name=tipoCategoria]:checked').val();
    	var idpaiselect = null;
    	if (key === "secundaria"){
    		var valueSelectIdpai = $("select#categoriaPaiSelect").val();
    		if ($("select#categoriaPaiSelect").val() !== ""){
    			idpaiselect = parseInt(valueSelectIdpai);
    		}
    	}
		
    	this.model.save({
            nome : $("#nomeInput", this.el).val(),
            descricao : $("#descricaoInput", this.el).val(),
            anonimato : ($("#anonimatoChckbx input[type='checkbox']", this.el).attr('checked')? true: false),
            idpai : ($("select#categoriaPaiSelect").val() !== "" ? parseInt($("select#categoriaPaiSelect").val()): null)
        }, options);
    	return true;
    },
    
//    showErrors: function(errors) {
//        _.each(errors, function (error) {
//            var controlGroup = this.$('.' + error.name);
//            controlGroup.addClass('error');
//            controlGroup.find('.help-inline').text(error.message);
//        }, this);
//    },
//
//    hideErrors: function () {
//        this.$('.form-group').removeClass('error');
//        this.$('.help-inline').text('');
//    }
    
    onShow: function(){
    	var acollection = new CategoriaCollection();
    	acollection.fetch();
    	var compositeView = new aCompositeView({collection : acollection, el : "#selectDivCategoria"});
    	compositeView.render();
    },
    
    onRender: function(){
    	
    	Backbone.Validation.bind(this,{
    		valid: function(view, attr) {
	          console.log(view + " "+ attr);
	        },
	        invalid: function(view, attr, error) {
	        	$("input[name='"+attr+"']").addClass('errorFormControl');
	        	console.log(error);
	        }
	      });
    }
    
  });
  
  
  
  
  return InsertCategoriaView;
});