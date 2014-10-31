define(['marionette','app',
        'text!templates/content/denuncia/agradecimentoParticipacaoTemplate.html'], 
        
function(Marionette, App,
		agradecimentoParticipacaoTemplate){
	
	
  
  var AgradecimentoParticipacaoView = Marionette.ItemView.extend({

	//initialize template
    template:_.template(agradecimentoParticipacaoTemplate),
   
    events : {
    	'click #voltar' :'voltarPaginaPrincipal'
	},

	voltarPaginaPrincipal: function(){
		
		var r = confirm("Deseja realizar outra den&uacute;ncia?");
		if (r == true) {
			App.Router.navigate('categoria/new', { trigger: true });
		} else {
			App.Router.navigate('', { trigger: true });
		}
		
	}
  });
  
  
  
  
  return AgradecimentoParticipacaoView;
});