define(['marionette',
        'app',
        'models/denuncia/denuncia',
        'collections/categoriaCollection',
        'text!templates/content/denuncia/insertDenunciaTemplate.html',
        'text!templates/content/denuncia/insertDenunciaCategoriaSelectTemplate.html',
        'backboneValidation'], 
        
function(Marionette, App,
		Denuncia,
		CategoriaCollection,
		insertDenunciaTemplate,
		insertDenunciaCategoriaSelectTemplate){
	
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
	  itemViewContainer: "select"
  });
  
  var InsertCategoriaView = Marionette.ItemView.extend({

	model: Denuncia,
	
	//initialize template
    template:_.template(insertDenunciaTemplate),
   
    events : {
    	'click #getGeolocation' :'getGeolocation',
    	'click #saveDenuncia' 	: 'saveDenuncia',
    	'click #camera-snap' 	:'getPhoto'
	},

	getGeolocation: function(){
		var onSuccess = function(position) {
		    alert('Latitude: '          + position.coords.latitude          + '\n' +
		          'Longitude: '         + position.coords.longitude         + '\n' +
		          'Altitude: '          + position.coords.altitude          + '\n' +
		          'Accuracy: '          + position.coords.accuracy          + '\n' +
		          'Altitude Accuracy: ' + position.coords.altitudeAccuracy  + '\n' +
		          'Heading: '           + position.coords.heading           + '\n' +
		          'Speed: '             + position.coords.speed             + '\n' +
		          'Timestamp: '         + position.timestamp                + '\n');
		};

		// onError Callback receives a PositionError object
		//
		function onError(error) {
		    alert('code: '    + error.code    + '\n' +
		          'message: ' + error.message + '\n');
		}

		navigator.geolocation.getCurrentPosition(onSuccess, onError);
	},
	
	saveDenuncia : function (clickEvent) {
    	clickEvent.preventDefault();
    	
    	$(".form-control").removeClass('errorFormControl');
    	var options = {
    	    success: function () {
    	        alert('Sua denúncia será incaminhada para os responsáveis!');
//    	        App.Router.navigate('rating', { trigger: true });
    	    },
    	    error: function (model, errors) {
    	        console.log('error'+ errors);
    	    }
    	};
    	
    	this.model.save({
            dataRealizacao : ($("#dataRealizacaoInput") !== undefined ? $("#dataRealizacaoInput").val(): null),
            descricao : $("#descricaoInput", this.el).val(),
            idcategoria : ($("select#categoriaSelect").val() !== "" ? parseInt($("select#categoriaSelect").val()): null)
        }, options);
    	return true;
    },
    
    getPhoto: function(e) {
        var $img = this.$el.find('img#camera-image');
        console.info('Taking Photo');
        /*
         | BASED ON: http://stackoverflow.com/a/11928792/633056
         */
        navigator.camera.getPicture(
            function(data) {
                $img.show();
                alert(data);  // <-- success alert
                //img.src = "data:image/jpeg;base64," + data;
                $img.attr('src', "data:image/jpeg;base64," + data);
                $('#camera-status').text("Success");
            },
            function(e) {
                console.log("Error getting picture: " + e);
                $('camera-status').innerHTML = e;
                //dom.byId('camera-image').style.display = "none";
            },
            // must be DATA_URL to return the data for future use
            {quality: 50, destinationType: navigator.camera.DestinationType.DATA_URL, sourceType : navigator.camera.PictureSourceType.CAMERA}
        );
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