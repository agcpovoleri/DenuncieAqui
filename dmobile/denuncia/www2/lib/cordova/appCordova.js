define([], function() {
	'use strict';
	var cordovaFunctions = {
//			Application Constructor
			initialize: function(str) {
				this.bindEvents(str);
			},
//			Bind Event Listeners

//			Bind any events that are required on startup. Common events are:
//			'load', 'deviceready', 'offline', and 'online'.
			bindEvents: function(str) {
//				alert('bindingEvents');
				if (str != undefined){
					switch (str) {
					case 'connection':
						document.addEventListener('deviceready', this.checkConnection, false);
						break;
					case 'geolocation':
						document.addEventListener('deviceready', this.checkGPS_position, false);
						break;
					default:
						break;
					}
				}else
					document.addEventListener('deviceready', this.onDeviceReady, false);
			},
//			deviceready Event Handler

//			The scope of 'this' is the event. In order to call the 'receivedEvent'
//			function, we must explicity call 'app.receivedEvent(...);'
			onDeviceReady: function() {
				alert('onDeviceReady');
				app.receivedEvent('deviceready');
			},
//			Update DOM on a Received Event
			receivedEvent: function(id) {
				this.checkConnection();
				this.checkGPS_position();
//				var parentElement = document.getElementById(id);
//				var listeningElement = parentElement.querySelector('.listening');
//				var receivedElement = parentElement.querySelector('.received');
//
//				listeningElement.setAttribute('style', 'display:none;');
//				receivedElement.setAttribute('style', 'display:block;');

				console.log('Received Event: ' + id);
			},
			
			checkConnection: function() {
		        var networkState = navigator.connection.type;

		        var states = {};
		        states[Connection.UNKNOWN]  = 'Unknown connection';
		        states[Connection.ETHERNET] = 'Ethernet connection';
		        states[Connection.WIFI]     = 'WiFi connection';
		        states[Connection.CELL_2G]  = 'Cell 2G connection';
		        states[Connection.CELL_3G]  = 'Cell 3G connection';
		        states[Connection.CELL_4G]  = 'Cell 4G connection';
		        states[Connection.NONE]     = 'No network connection';

		        alert("Connection type: You're using: " + states[networkState]);
		    },
		    
		    checkGPS_position: function(){
		    	
		    	var onSuccess = function(position) {
			        alert('success');
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

			    navigator.geolocation.getCurrentPosition(onSuccess, onError, {maximumAge:3000,timeout:5000,enableHighAccuracy:true});
		    }
		    
		    

			
	};
	return cordovaFunctions;
});