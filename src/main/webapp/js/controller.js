var anthesiteObj = new anthesite();

var booksApp = angular.module('booksApp', ['ngRoute']);

booksApp.filter('range', function() {
	return function(input, total) {
		total = parseInt(total);

		for (var i=0; i<total; i++) {
			input.push(i);
		}

		return input;
	};
});

// ***** Home ***** \\
booksApp.controller('HomeController', function($scope, $http) {
	$scope.books = {};
	$scope.nbBooks = 0;
	$scope.error = {};
	
	$http.get('rest/livres?l=3&order=-createdAt').then(function(response) {
		$scope.books = response.data;
	}, function(response) {
		$scope.error = response.data;
	});
	
	$http.get('rest/livres?count=true').then(function(response) {
		$scope.nbBooks = response.data.count;
	}, function(response) {
		$scope.error = response.data;
	});
	
	selectMenu('#li-home');
});

// ***** Recherche ***** \\
booksApp.controller('AjouterController', function($scope, $http) {
	$scope.recherche = { 
			isbn : "", 
			libre: "",
			auteur: "",
			titre: ""
	};
	
	$scope.recherche_result = null;
	$scope.recherche_error = false;
	$scope.isbn_pattern = "^(97(8|9))?\\d{9}(\\d|X)$";
	
	$scope.rechercher = function() {
		anthesiteObj.ajaxLoading(true);
		
		var url = "https://www.googleapis.com/books/v1/volumes?q=";
		var hasSearchTerm = false;

		if ($scope.recherche.libre.length) {
			url += $scope.recherche.libre;
			hasSearchTerm = true;
		}
		
		if ($scope.recherche.isbn.length) {
			url += (hasSearchTerm ? "+" : "") + "isbn:" + $scope.recherche.isbn;
			hasSearchTerm = true;
		}
		
		if ($scope.recherche.auteur.length) {
			url += (hasSearchTerm ? "+" : "") + "inauthor:" + $scope.recherche.auteur;
			hasSearchTerm = true;
		}
		
		if ($scope.recherche.titre.length) {
			url += (hasSearchTerm ? "+" : "") + "intitle:" + $scope.recherche.titre;
			hasSearchTerm = true;
		}
		
	    $http.get(url).then(function(response) {
	    	$scope.recherche_error = false;
	    	$scope.recherche_result = response.data;
	    	anthesiteObj.ajaxLoading(false);
	    }, function(response) {
	    	$scope.recherche_error = true;
	    });
	};
	
	selectMenu('#li-ajouter');
});

booksApp.controller('ExplorerController', function($scope, $http) {
	
	$scope.displayScope = 'all-books';
	$scope.livres = [];
	$scope.sagas = [];
	$scope.auteurs = [];
	$scope.selectedAuteur = {};
	$scope.selectedSaga = {};
	$scope.error = {};
	$scope.pagination = {
			pageSize: 12,
			currentPage: 0,
			totalCount: 0,
			nbPage: 0
	};
	
	$scope.sort = {
			prop : 'titre',
			reverse : false
	};
	
	$scope.getPage = function(page) {
		$scope.pagination.currentPage = page;
		$scope.display($scope.displayScope);
	};
	
	$scope.sorting = function(prop) {
		$scope.sort.prop = prop;
		$scope.sort.reverse = !$scope.sort.reverse;
		
		if ($scope.displayScope == 'all-books') {
			$scope.display($scope.displayScope);
		}
	};
	
	$http.get('rest/livres?count=true').then(function(response) {
		$scope.pagination.totalCount = response.data.count;
		$scope.pagination.nbPage = Math.floor($scope.pagination.totalCount / $scope.pagination.pageSize);
		
		if ($scope.pagination.totalCount % $scope.pagination.pageSize != 0) {
			$scope.pagination.nbPage += 1;
		}
	}, function(response) {
		$scope.error = response.data;
	});
	
	$scope.display = function(displayScope) {
		$scope.displayScope = displayScope;
		anthesiteObj.ajaxLoading(true);
		
		if ($scope.displayScope == 'all-books') {
			$scope.loadLivres('p=' + $scope.pagination.currentPage + '&l=' 
					+ $scope.pagination.pageSize + '&order=' + ($scope.sort.reverse ? '-' : '') + $scope.sort.prop);
		} else if ($scope.displayScope == 'by-auteurs') {
			if ($scope.selectedAuteur.id) {
				$http.get('rest/auteurs/' + $scope.selectedAuteur.id + '/livres').then(function(response) {
					$scope.livres = response.data;
					anthesiteObj.ajaxLoading(false);
				}, function(response) {
					anthesiteObj.ajaxLoading(false);
					$scope.error = response.data;
				});
			} else {
				anthesiteObj.ajaxLoading(false);
				$scope.livres = [];
			}
			
		} else if ($scope.displayScope == 'by-sagas') {
			if ($scope.selectedSaga.id) {
				$http.get('rest/sagas/' + $scope.selectedSaga.id + '/livres').then(function(response) {
					$scope.livres = response.data;
					anthesiteObj.ajaxLoading(false);
				}, function(response) {
					anthesiteObj.ajaxLoading(false);
					$scope.error = response.data;
				});
			} else {
				anthesiteObj.ajaxLoading(false);
				$scope.livres = [];
			}
		} else if ($scope.displayScope == 'to-read') {
			$scope.loadLivres('f=lu:false');
		} else if ($scope.displayScope == 'to-get') {
			$scope.loadLivres('f=possede:false');
		} else {
			$scope.livres = [];
		}
		
		selectExploreScope(displayScope);
	};
	
	$scope.loadLivres = function(queryParams) {
		$http.get('rest/livres' + (queryParams.length > 0 ? ('?' + queryParams) : '')).then(function(response) {
			$scope.livres = response.data;
			anthesiteObj.ajaxLoading(false);
		}, function(response) {
			anthesiteObj.ajaxLoading(false);
			$scope.error = response.data;
		});
	};
	
	$http.get('rest/auteurs').then(function(response) {
		$scope.auteurs = response.data;
	}, function(response) {
		$scope.error = response.data;
	});
	
	$http.get('rest/sagas').then(function(response) {
		$scope.sagas = response.data;
	}, function(response) {
		$scope.error = response.data;
	});
	
	$scope.display($scope.displayScope);
	
	selectMenu('#li-explorer');
});

// ***** Livres ***** \\
booksApp.controller('LivresListController', function($scope, $http, $location) {
	$scope.livres = {};
	$scope.error = {};
	$scope.pagination = {
			pageSize: 10,
			currentPage: 0,
			totalCount: 0,
			nbPage: 0
	};
	
	$scope.getPage = function(page) {
		$scope.pagination.currentPage = page;
		$scope.loadLivres();
	};

	$scope.del = function(id) {
		$location.path('/livres/' + id + '/delete');
	};
	
	$scope.edit = function(id) {
		$location.path('/livres/' + id + '/edit');
	};
	
	$scope.loadLivres = function() {
		$http.get("rest/livres?order=titre&p=" + $scope.pagination.currentPage + '&l=' + $scope.pagination.pageSize).then(function(response) {
			$scope.error = {};
			$scope.livres = response.data;
		}, function(response) {
			$scope.error = response.data;
			$scope.livres = {};
		});
	};
	
	$http.get('rest/livres?count=true').then(function(response) {
		$scope.pagination.totalCount = response.data.count;
		$scope.pagination.nbPage = Math.floor($scope.pagination.totalCount / $scope.pagination.pageSize);
		
		if ($scope.pagination.totalCount % $scope.pagination.pageSize != 0) {
			$scope.pagination.nbPage += 1;
		}
	}, function(response) {
		$scope.error = response.data;
	});
	
	$scope.loadLivres();
	
	selectMenu('#li-livres');
});

booksApp.controller('LivresFormController', function($scope, $http, $location, $routeParams) {
	$scope.isbn_pattern = "^(97(8|9))?\\d{9}(\\d|X)$";
	$scope.sagas = [];
	$scope.auteurs = [];
	$scope.livre = {
			saga : {
				id: ""
			},
			auteurs: []
	};
	$scope.saga = {}; // Saga quick add form.
	$scope.auteur = {}; // Auteur quick add form.
	$scope.error = {};
	
	var originalAuteur = {}; // Saving auteurs already bind to the book for update.
	
	
	$http.get("rest/sagas").then(function(response) {
    	$scope.sagas = response.data;
    	$scope.error = {};
    }, function(response) {
    	$scope.error = response.data;
    	$scope.sagas = [];
    });
	
	$http.get("rest/auteurs?order=nom,prenom").then(function(response) {
    	$scope.auteurs = response.data;
    	
    	if ($routeParams.fromid) {
    		$http.get('https://www.googleapis.com/books/v1/volumes/' + $routeParams.fromid).success(function(data) {
    			for (var i = 0; i < data.volumeInfo.industryIdentifiers.length; i++) {
    				if (data.volumeInfo.industryIdentifiers[i].type == 'ISBN_13') {
    					$scope.livre.isbn = data.volumeInfo.industryIdentifiers[i].identifier;
    					break;
    				}
    			}
    			
    			$scope.livre.titre = data.volumeInfo.title;
    			if (data.volumeInfo.subtitle) {
    				$scope.livre.titre += ' - ' + data.volumeInfo.subtitle;
    			}
    			
    			$scope.livre.titreOriginal = $scope.livre.titre;
    			$scope.livre.editeur = data.volumeInfo.publisher;
    			$scope.livre.resume = data.volumeInfo.description;
    			$scope.livre.format = 'POCHE';
    			$scope.livre.langue = 'FRENCH';
    			$scope.livre.nbPage = data.volumeInfo.pageCount;
    			if (data.volumeInfo.imageLinks && data.volumeInfo.imageLinks.smallThumbnail) {
    				$scope.livre.imagePath = data.volumeInfo.imageLinks.smallThumbnail;
    			}
    			$scope.livre.possede = true;
    			$scope.livre.lu = false;
    			
    			var auteurToCreate = [];
    			for (var i = 0; i < data.volumeInfo.authors.length; i++) {
    				var auteur = data.volumeInfo.authors[i];
    				var auteurParts = auteur.split(" ");
    				var auteurNom = "";
    				var auteurPrenom = "";
    				
    				if (auteurParts.length == 1) {
    					auteurNom = auteurParts[0];
    				} else if (auteurParts.length == 2) {
    					auteurPrenom = auteurParts[0];
    					auteurNom = auteurParts[1];
    				} else if (auteurParts.length == 3) {
    					auteurPrenom = auteurParts[0] + " " + auteurParts[1];
    					auteurNom = auteurParts[2];
    				} else {
    					auteurPrenom = auteurParts[0] + " " + auteurParts[1];
    					auteurNom = auteurParts[2] + " " + auteurParts[3];
    				}
    				
    				var auteurExists = false;
    				for (var j = 0; j < $scope.auteurs.length; j++) {
    					if ($scope.auteurs[j].nom.toUpperCase() == auteurNom.toUpperCase() 
    							&& $scope.auteurs[j].prenom.toUpperCase() == auteurPrenom.toUpperCase()) {
    						$scope.livre.auteurs.push($scope.auteurs[j]);
    						auteurExists = true;
    						break;
    					}
    				}
    				
    				if (!auteurExists) {
    					auteurToCreate.push({
    						nom: auteurNom,
    						prenom : auteurPrenom
    					});
    				}
    			}
    				
    			if (auteurToCreate.length > 0) {
    				for (var ii = 0; ii < auteurToCreate.length; ii++) {
    					$http.post('rest/auteurs', auteurToCreate[ii]).success(function(data, status, headers, config) {
    						var auteurId = headers('Location').substring(headers('Location').lastIndexOf('/') + 1);
    						$http.get('rest/auteurs/' + auteurId).success(function(data) {
    							$scope.livre.auteurs.push(data);
    							$scope.auteurs.push(data);
    						})
    						$scope.error = {};
    				    }).error(function(data, status) {
    				    	$scope.error = data;
    				    });
    				}
    			}
    		}).error(function(response) {
    			$scope.error = response.data;
    	    });
    	}
    }, function(response) {
    	$scope.error = response.data;
    });
	
	if ($routeParams.isbn) {
		$http.get('rest/livres/' + $routeParams.isbn).then(function(response) {
			$scope.error = {};
			$scope.livre = response.data;
			originalAuteur = $scope.livre.auteurs; 
		}, function(response) {
	    	$scope.error = response.data;
	    	$scope.livre = {};
	    });
	};
	
	$scope.validateSaga = function() {
		$http.post('rest/sagas', $scope.saga).success(function(data, status, headers, config) {
			var sagaId = headers('Location').substring(headers('Location').lastIndexOf('/') + 1);
			var newSaga = { id: sagaId, nom: $scope.saga.nom };
			$scope.livre.saga = newSaga;
			$scope.sagas.push(newSaga);
			$scope.error = {};
			$scope.saga = {}
			$scope.form_saga.$setPristine();
			$scope.form_saga.$setUntouched();
	    }).error(function(data, status) {
	    	$scope.error = data;
	    	$scope.saga = {};
	    });
	};
	
	$scope.validateAuteur = function() {
		$http.post('rest/auteurs', $scope.auteur).success(function(data, status, headers, config) {
			var auteurId = headers('Location').substring(headers('Location').lastIndexOf('/') + 1);
			var newAuteur = { id: auteurId, nom: $scope.auteur.nom, prenom: $scope.auteur.prenom };
			$scope.livre.auteurs.push(newAuteur);
			$scope.auteurs.push(newAuteur);
			$scope.error = {};
			$scope.auteur = {}
			$scope.form_auteur.$setPristine();
			$scope.form_auteur.$setUntouched();
	    }).error(function(data, status) {
	    	$scope.error = data;
	    	$scope.form_auteur.$setPristine();
			$scope.form_auteur.$setUntouched();
	    });
	};
	
	$scope.validate = function() {
		if ($routeParams.isbn) {
			$http.put('rest/livres/' + $scope.livre.isbn, $scope.livre).success(function(data, status, headers, config) {
				// Looking for auteurs binding.
				var auteursToRemove = [];
				var auteursToAdd = [];
				for (var i = 0; i < originalAuteur.length; i++) {
					var found = false;
					for (var j = 0; j < $scope.livre.auteurs.length; j++) {
						if (originalAuteur[i].id == $scope.livre.auteurs[j].id) {
							found = true;
							break;
						}
					}
					
					if (!found) {
						auteursToRemove.push(originalAuteur[i]);
					}
				}
				
				for (var i = 0; i < $scope.livre.auteurs.length; i++) {
					var found = false;
					for (var j = 0; j < originalAuteur.length; j++) {
						if ($scope.livre.auteurs[i].id == originalAuteur[j].id) {
							found = true;
							break;
						}
					}
					
					if (!found) {
						auteursToAdd.push($scope.livre.auteurs[i]);
					}
				}
				
				for (var i = 0; i < auteursToAdd.length; i++) {
					$http.post('rest/livres/' + $scope.livre.isbn + '/auteurs', auteursToAdd[i]);
				}
				
				for (var i = 0; i <auteursToRemove.length; i++) {
					$http.delete('rest/livres/' + $scope.livre.isbn + '/auteurs/' + auteursToRemove[i].id);
				}
				
				anthesiteObj.showAndHideAfter('app-confirm', 3000, 'fade');
				$location.path('/livres');
		    }).error(function(data, status) {
		    	$scope.error = data;
		    });
		} else {
			$http.post('rest/livres', $scope.livre).success(function(data, status, headers, config) {
				var livreId = headers('Location').substring(headers('Location').lastIndexOf('/') + 1);
				for (var i = 0; i < $scope.livre.auteurs.length; i++) {
					$http.post('rest/livres/' + livreId + '/auteurs', {id : $scope.livre.auteurs[i].id });
				}
				anthesiteObj.showAndHideAfter('app-confirm', 3000, 'fade');
				$location.path('/livres');
		    }).error(function(data, status) {
		    	$scope.error = data;
		    });
		}
	};
	
	selectMenu('#li-livres');
});

booksApp.controller('LivresDeleteController', function($scope, $http, $routeParams, $location) {
	$scope.error = {};
	$scope.del = function() {
		$http.delete('rest/livres/' + $routeParams.isbn).success(function() {
			$location.path('/livres');
			anthesiteObj.showAndHideAfter('app-confirm', 3000, 'fade');
		}).error(function(response) {
			$scope.error = response.data;
		});
	}
	
	selectMenu('#li-livres');
});

// ***** Auteurs ***** \\
booksApp.controller('AuteursListController', function($scope, $http, $location) {
	$scope.auteurs = {};
	$scope.error = {};
	$scope.pagination = {
			pageSize: 10,
			currentPage: 0,
			totalCount: 0,
			nbPage: 0
	};
	
	$scope.getPage = function(page) {
		$scope.pagination.currentPage = page;
		$scope.loadAuteurs();
	};

	$scope.del = function(id) {
		$location.path('/auteurs/' + id + '/delete');
	};
	
	$scope.edit = function(id) {
		$location.path('/auteurs/' + id + '/edit');
	};
	
	$scope.loadAuteurs = function() {
		$http.get("rest/auteurs?order=nom,prenom&p=" + $scope.pagination.currentPage + '&l=' + $scope.pagination.pageSize).then(function(response) {
			$scope.error = {};
	    	$scope.auteurs = response.data;
	    }, function(response) {
	    	$scope.error = response.data;
	    	$scope.auteurs = {};
	    });
	};
	
	$http.get('rest/auteurs?count=true').then(function(response) {
		$scope.pagination.totalCount = response.data.count;
		$scope.pagination.nbPage = Math.floor($scope.pagination.totalCount / $scope.pagination.pageSize);
		
		if ($scope.pagination.totalCount % $scope.pagination.pageSize != 0) {
			$scope.pagination.nbPage += 1;
		}
	}, function(response) {
		$scope.error = response.data;
	});
	
	$scope.loadAuteurs();
	
	selectMenu('#li-auteurs');
});

booksApp.controller('AuteursFormController', function($scope, $http, $routeParams, $location) {
	$scope.auteur = {};
	$scope.error = {};
	
	if ($routeParams.id) {
		$http.get('rest/auteurs/' + $routeParams.id).then(function(response) {
	    	$scope.auteur = response.data;
	    	$scope.error = {};
	    }, function(response) {
	    	$scope.error = response.data;
	    	$scope.auteur = {};
	    });
	}
	
	$scope.validate = function() {
		if ($routeParams.id) {
			$http.put('rest/auteurs/' + $routeParams.id, $scope.auteur).then(function() {
				$location.path('/auteurs');
				anthesiteObj.showAndHideAfter('app-confirm', 3000, 'fade');
		    }, function(response) {
		    	$scope.error = response.data;
		    });
		} else {
			$http.post('rest/auteurs', $scope.auteur).then(function() {
				$location.path('/auteurs');
				anthesiteObj.showAndHideAfter('app-confirm', 3000, 'fade');
		    }, function(response) {
		    	$scope.error = response.data;
		    });
		}
	}
	
	selectMenu('#li-auteurs');
});

booksApp.controller('AuteursDeleteController', function($scope, $http, $routeParams, $location) {
	$scope.error = {};
	$scope.del = function() {
		$http.delete('rest/auteurs/' + $routeParams.id).then(function() {
			$location.path('/auteurs');
			anthesiteObj.showAndHideAfter('app-confirm', 3000, 'fade');
		}, function(response) {
			$scope.error = response.data;
		});
	}
	
	selectMenu('#li-auteurs');
});

// ****** Sagas ***** \\
booksApp.controller('SagasListController', function($scope, $http, $location) {
	$scope.sagas = {};
	$scope.error = {};
	$scope.pagination = {
			pageSize: 20,
			currentPage: 0,
			totalCount: 0,
			nbPage: 0
	};
	
	$scope.getPage = function(page) {
		$scope.pagination.currentPage = page;
		$scope.loadSagas();
	};

	$scope.del = function(id) {
		$location.path('/sagas/' + id + '/delete');
	};
	
	$scope.edit = function(id) {
		$location.path('/sagas/' + id + '/edit');
	};
	
	$scope.loadSagas = function() {
		$http.get("rest/sagas?order=nom&p=" + $scope.pagination.currentPage + '&l=' + $scope.pagination.pageSize).then(function(response) {
			$scope.error = {};
	    	$scope.sagas = response.data;
	    }, function(response) {
	    	$scope.error = response.data;
	    	$scope.sagas = {};
	    });
	};
	
	$http.get('rest/sagas?count=true').then(function(response) {
		$scope.pagination.totalCount = response.data.count;
		$scope.pagination.nbPage = Math.floor($scope.pagination.totalCount / $scope.pagination.pageSize);
		
		if ($scope.pagination.totalCount % $scope.pagination.pageSize != 0) {
			$scope.pagination.nbPage += 1;
		}
	}, function(response) {
		$scope.error = response.data;
	});
	
	$scope.loadSagas();
	
	selectMenu('#li-sagas');
});

booksApp.controller('SagasFormController', function($scope, $http, $routeParams, $location) {
	$scope.saga = {};
	$scope.error = {};
	
	if ($routeParams.id) {
		$http.get('rest/sagas/' + $routeParams.id).then(function(response) {
	    	$scope.saga = response.data;
	    	$scope.error = {};
	    }, function(response) {
	    	$scope.saga = {};
	    	$scope.error = response.data;
	    });
	}
	
	$scope.validate = function() {
		if ($routeParams.id) {
			$http.put('rest/sagas/' + $routeParams.id, $scope.saga).then(function() {
				$location.path('/sagas');
				anthesiteObj.showAndHideAfter('app-confirm', 3000, 'fade');
		    }, function(response) {
		    	$scope.error = response.data;
		    });
		} else {
			$http.post('rest/sagas', $scope.saga).then(function() {
				$location.path('/sagas');
				anthesiteObj.showAndHideAfter('app-confirm', 3000, 'fade');
		    }, function(response) {
		    	$scope.error = response.data;
		    });
		}
	}
	
	selectMenu('#li-sagas');
});

booksApp.controller('SagasDeleteController', function($scope, $http, $routeParams, $location) {
	$scope.error = {};
	
	$scope.del = function() {
		$http.delete('rest/sagas/' + $routeParams.id).then(function() {
			$location.path('/sagas');
			anthesiteObj.showAndHideAfter('app-confirm', 3000, 'fade');
		}, function(response) {
			$scope.error = response.data;
		});
	}
	
	selectMenu('#li-sagas');
});

// ***** Routing ***** \\
booksApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'partials/home-partial.html',
		controller : 'HomeController'
	}).when('/ajouter', {
		templateUrl : 'partials/ajouter-partial.html',
		controller : 'AjouterController'
	}).when('/explorer', {
		templateUrl : 'partials/explorer/explorer-partial.html',
		controller : 'ExplorerController'
	}).when('/livres', {
		templateUrl : 'partials/livres/livres-list.html',
		controller : 'LivresListController'
	}).when('/livres/add', {
		templateUrl : 'partials/livres/livres-form.html',
		controller : 'LivresFormController'
	}).when('/livres/add/:fromid', {
		templateUrl : 'partials/livres/livres-form.html',
		controller : 'LivresFormController'
	}).when('/livres/:isbn/edit', {
		templateUrl : 'partials/livres/livres-form.html',
		controller : 'LivresFormController'
	}).when('/livres/:isbn/delete', {
		templateUrl : 'partials/livres/livres-delete.html',
		controller : 'LivresDeleteController'
	}).when('/auteurs', {
		templateUrl : 'partials/auteurs/auteurs-list.html',
		controller : 'AuteursListController'
	}).when('/auteurs/:id/delete', {
		templateUrl : 'partials/auteurs/auteurs-delete.html',
		controller : 'AuteursDeleteController'
	}).when('/auteurs/:id/edit', {
		templateUrl : 'partials/auteurs/auteurs-form.html',
		controller : 'AuteursFormController'
	}).when('/auteurs/add', {
		templateUrl : 'partials/auteurs/auteurs-form.html',
		controller : 'AuteursFormController'
	}).when('/sagas', {
		templateUrl : 'partials/sagas/sagas-list.html',
		controller : 'SagasListController'
	}).when('/sagas/:id/delete', {
		templateUrl : 'partials/sagas/sagas-delete.html',
		controller : 'SagasDeleteController'
	}).when('/sagas/:id/edit', {
		templateUrl : 'partials/sagas/sagas-form.html',
		controller : 'SagasFormController'
	}).when('/sagas/add', {
		templateUrl : 'partials/sagas/sagas-form.html',
		controller : 'SagasFormController'
	}).otherwise({
		redirectTo : '/'
	});
} ]);

var selectMenu = function(menuId) {
	$("#ul-menu li").removeClass("active");
	$(menuId).addClass("active");
};

var selectExploreScope = function(displayScope) {
	$('#display-scope-button button').removeClass("btn-primary");
	$('#display-scope-button button').addClass("btn-default");
	$('#' + displayScope).addClass("btn-primary");
};