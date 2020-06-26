$(document).ready(function(){
	$('#insertForm').bootstrapValidator({
		feedbackIcons: {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields: {
			nome: {
				container : '#infoNome',
				validators : {
					notEmpty : {
						message : 'Il campo nome non pu&ograve; essere vuoto'
					},
					regexp : {
						regexp : /^[a-zA-Z ]{1,30}$/,
						message : 'Solo lettere minuscole e maiuscole, massimo 30 caratteri.'
					}
				}
			},
			cognome: {
				container : '#infoCognome',
				validators : {
					notEmpty : {
						message : 'Il campo cognome non pu&ograve; essere vuoto'
					},
					regexp : {
						regexp : /^[a-zA-Z ]{1,30}$/,
						message : 'Solo lettere minuscole e maiuscole, massimo 30 caratteri.'
					}
				}
			}
		}		
	});
	
	$('#userform').bootstrapValidator({
		feedbackIcons: {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields: {
			username: {
				container : '#infoUsername',
				validators : {
					notEmpty : {
						message : 'Il campo nome non pu&ograve; essere vuoto'
					},
					regexp : {
						regexp : /^[a-zA-Z0-9]{4,10}$/,
						message : 'Da 4 a 10 caratteri. (Lettere e numeri accettati)'
					}
				}
			},
			password: {
				container : '#infoPassword',
				validators : {
					notEmpty : {
						message : 'Il campo cognome non pu&ograve; essere vuoto'
					},
					regexp : {
						regexp : /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{4,20}$/,
						message : 'Da 4 a 10 caratteri. (Lettere e numeri accettati)'
					}
				}
			}
		}		
	});
	
	
});