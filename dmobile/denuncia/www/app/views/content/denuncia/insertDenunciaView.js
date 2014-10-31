define(['marionette','app','vent','appConfig',

        'models/denuncia/denuncia',
        'models/media/geolocation',
        'models/media/uploadForm',
        'collections/categoriaAllCollection',

        'text!templates/content/denuncia/insertDenunciaTemplate.html',
        'text!templates/content/denuncia/insertDenunciaCategoriaSelectTemplate.html',
        'text!templates/content/media/camera/thumbnailCameraTemplate.html'], 

        function(Marionette, App, vent, AppConfig,

        		Denuncia,
        		Geolocation,
        		UploadForm,
        		CategoriaAllCollection,

        		insertDenunciaTemplate,
        		insertDenunciaCategoriaSelectTemplate,
        		thumbnailTemplate){

	var aItemView = Marionette.ItemView.extend({
		template: _.template("<%=nome%>"),
		tagName: "option",
		onRender: function(){
			this.$el.attr('value', this.model.get('id'));
		}
	});

	var aCompositeView = Marionette.CompositeView.extend({
		template: _.template(insertDenunciaCategoriaSelectTemplate),  
		itemViewContainer: "#categoriaSelect",
		itemView: aItemView,  

		onRender: function(){


//			alert('A categoria foi selecionada conforme seleção anterior');

			var self = this;
			$(this.$el).find("select#categoriaSelect").attr('disabled','disabled');
			$(this.$el).find("select#categoriaSelect option:selected").removeAttr('selected');
			var strElem = "select#categoriaSelect option[value="+ this.options.idcategoria +"]";
			$(this.$el).find(strElem).attr('selected','selected');

		}
	});


	var InsertDenunciaView = Marionette.ItemView.extend({

		model: Denuncia,

		quantidadeImagens: 0,

		//initialize template
		template:_.template(insertDenunciaTemplate),

		initialize: function(){

		},

		events : {
			'click #getGeolocation' :'getGeolocation',
			'click #saveDenuncia' 	:'saveDenuncia',
			'click #camera-snap' 	:'takePhoto',
			'click #fileupload-snap':'getPhoto'
		},

		getGeolocation: function(e){
			e.preventDefault();
			var self = this;

			if($("#getGeolocation").hasClass("btn-default")){

				var onSuccess = function(position) {

					var geolocation = new Geolocation({

						latitude: position.coords.latitude,
						longitude: position.coords.longitude,
						altitude: position.coords.altitude,
						accuracy: position.coords.accuracy ,
						altitude_accuracy: position.coords.altitudeAccuracy,
						heading: position.coords.heading ,
						speed: position.coords.speed ,
						timestamp: position.timestamp
					});

					self.model.set({localizacao: geolocation}); 

//					alert('Latitude: '          + position.coords.latitude          + '\n' +
//					'Longitude: '         + position.coords.longitude         + '\n' +
//					'Altitude: '          + position.coords.altitude          + '\n' +
//					'Accuracy: '          + position.coords.accuracy          + '\n' +
//					'Altitude Accuracy: ' + position.coords.altitudeAccuracy  + '\n' +
//					'Heading: '           + position.coords.heading           + '\n' +
//					'Speed: '             + position.coords.speed             + '\n' +
//					'Timestamp: '         + position.timestamp                + '\n');

					$("#getGeolocation").removeClass("btn-default").addClass("btn-info");

				};

				// onError Callback receives a PositionError object
				//
				function onError(error) {
					alert('code: '    + error.code    + '\n' +
							'message: ' + error.message + '\n');
				}

				navigator.geolocation.getCurrentPosition(onSuccess, onError);
			}else{
				$("#getGeolocation").removeClass("btn-info").addClass("btn-default");
			}


		},

		// use an existing photo from the library:
		getPhoto: function(e) {
			this.capture(Camera.PictureSourceType.SAVEDPHOTOALBUM);
		},

		// take a new photo:
		takePhoto: function(e) {
			this.capture(Camera.PictureSourceType.CAMERA);
		},

		// capture either new or existing photo:
		capture: function(sourceType) {
			navigator.camera.getPicture(this.onCaptureSuccess, this.onCaptureFail, {
				destinationType: Camera.DestinationType.FILE_URI,
				sourceType: sourceType,
				correctOrientation: true
			});
		},

		// if photo is captured successfully, then upload to server:
		onCaptureSuccess: function(imageURI) {
			var fail, ft, options, params, win;
			// callback for when the photo has been successfully uploaded:
			success = function(response) {
				alert("Your photo has been uploaded!");
			};
			// callback if the photo fails to upload successfully.
			fail = function(error) {
				alert("An error has occurred: Code = " + error.code);
			};
			options = new FileUploadOptions();
			// parameter name of file:
			options.fileKey = "imgData";
			// name of the file:
			options.fileName = imageURI.substr(imageURI.lastIndexOf('/') + 1);
			// mime type:
			options.mimeType = "text/plain";
			params = {
					val1: "some value",
					val2: "some other value"
			};
			options.params = params;
			ft = new FileTransfer();
			ft.upload(imageURI, AppConfig.globalUrl + "/denuncia/image", success, fail, options);
		},

		// there was an error capturing the photo:
		onCaptureFail: function(message) {
			alert("Failed because: " + message);
		},


		saveDenuncia : function (clickEvent) {
			clickEvent.preventDefault();

			$(".form-control").removeClass('errorFormControl');
			var options = {
					success: function () {
//						alert('Obrigado por sua participação!! Sua denúncia vai ser importante para mapearmos os problemas das cidades!! Encaminharemos estes dados aos responsáveis!');
						App.Router.navigate('agradecimentoParticipacao', { trigger: true });
					},
					error: function (model, errors) {
						console.log('error'+ errors);
					}
			};

			var images = this.$el.find('div#imagensUpload img.img-thumbnail');
			if (images != undefined && $(images).size()>0){
				var imgsUpload = [];

				var x = $(this).attr('src');


//				$.ajax({
//				type: "POST",
//				datatype: 'json',
//				data: imgData,
//				url: "/Archives/SaveImage.html",
//				success: function(data) {
//				alert("successfully uploaded");
//				},
//				error: function (request, status, error) {
//				alert("failure");
//				}
//				});


//				$.ajax({ 
//				type: "POST",
//				contentType:attr( "enctype", "multipart/form-data" ),
//				url: AppConfig.globalUrl + "/denuncia/imageUpload", 
//				data: dados, 
//				success: function( data ) 
//				{ 
//				alert( data ); 
//				} 
//				});  


			}

			this.model.save({
				dataRealizacao : ($("#dataRealizacaoInput") !== undefined ? $("#dataRealizacaoInput").val(): null),
				descricao : $("#descricaoInput", this.el).val(),
				idcategoria : ($("select#categoriaSelect").val() !== "" ? parseInt($("select#categoriaSelect").val()): null)
			}, options);
			return true;
		},

//		getPhoto: function(e) {
//		e.preventDefault();
//		var self = this;

//		console.info('Getting Photo');
//		/*
//		| BASED ON: http://stackoverflow.com/a/11928792/633056
//		*/
//		try {
//		navigator.camera.getPicture(
//		function(data) {

//		var $img = self.createThumbnail();
//		var srcdata = "data:image/jpeg;base64," + data;  // <-- success alert
//		//img.src = "data:image/jpeg;base64," + data;
//		$img.attr('src', srcdata);
//		$('#camera-status').text("Success");
//		alert("Success");
////		var image = new AttachedImageView({model: new Image({
////		data: srcdata,
////		filename: 'teste'
////		})});
////		$(this.$el).find('#imagensUpload').append(image.render());


//		},
//		function(e) {
//		console.log("Error getting picture: " + e);
//		$('camera-status').innerHTML = e;
//		throw "Error getting picture: " + e;
//		//dom.byId('camera-image').style.display = "none";
//		},
//		// must be DATA_URL to return the data for future use
//		{
//		quality: 50, 
//		destinationType: navigator.camera.DestinationType.DATA_URL, 
//		sourceType : Camera.PictureSourceType.PHOTOLIBRARY,
//		encodingType: navigator.camera.EncodingType.PNG, 
//		allowEdit : true,
//		}
//		);
//		}
//		catch(err) {
//		this.removeLastThumbnail();
//		alert(err);
//		}

//		},

//		takePhoto: function(e) {
//		e.preventDefault();
//		var self = this;
//		console.info('Taking Photo');
//		/*
//		| BASED ON: http://stackoverflow.com/a/11928792/633056
//		*/
//		try {
//		navigator.camera.getPicture(
//		function(data) {

//		var $img = self.createThumbnail();
//		var srcdata = "data:image/jpeg;base64," + data;  // <-- success alert
//		//img.src = "data:image/jpeg;base64," + data;
//		$img.attr('src', srcdata);
//		$('#camera-status').text("Success");

////		var image = new AttachedImageView({model: new Image({
////		data: srcdata,
////		filename: 'teste'
////		})});
////		$(this.$el).find('#imagensUpload').append(image.render());


//		},
//		function(e) {
//		console.log("Error getting picture: " + e);

//		$('camera-status').innerHTML = e;
//		throw "Error getting picture: " + e;
//		//dom.byId('camera-image').style.display = "none";
//		},
//		// must be DATA_URL to return the data for future use
//		{
//		quality: 50, 
//		destinationType: navigator.camera.DestinationType.DATA_URL, 
//		sourceType : navigator.camera.PictureSourceType.CAMERA,
////		Camera.PictureSourceType.PHOTOLIBRARY
//		encodingType: navigator.camera.EncodingType.PNG, 
//		allowEdit : true,
//		}
//		);
//		}
//		catch(err) {
//		this.removeLastThumbnail();
//		alert(err);
//		}

//		},

		createThumbnail: function(){
			console.log('Creating thumbnail');
			this.quantidadeImagens++;
			this.$el.find('div#imagensUpload').append(_.template(thumbnailTemplate, {id: this.quantidadeImagens })); 
			return this.$el.find('div#imagensUpload img.img-thumbnail').last();

		},

		removeLastThumbnail: function(){
			this.$el.find('.thumbImageDiv').last().remove();

		},

//		showErrors: function(errors) {
//		_.each(errors, function (error) {
//		var controlGroup = this.$('.' + error.name);
//		controlGroup.addClass('error');
//		controlGroup.find('.help-inline').text(error.message);
//		}, this);
//		},

//		hideErrors: function () {
//		this.$('.form-group').removeClass('error');
//		this.$('.help-inline').text('');
//		}

		onShow: function(){

			var acollection = new CategoriaAllCollection();
			acollection.fetch();
			var compositeView = new aCompositeView({collection : acollection, el : "#selectDivCategoria", idcategoria: this.options.idcategoria});
			compositeView.render();

		},


		onRender: function(){

			Backbone.Validation.bind(this,{
				valid: function(view, attr) {
					console.log(view + " "+ attr);
				},
				invalid: function(view, attr, error) {
					$("input[name='"+attr+"'], select[name='"+attr+"'], textarea[name='"+attr+"']").addClass('errorFormControl');
					console.log(error);
				}
			});
		}

	});




	return InsertDenunciaView;
});