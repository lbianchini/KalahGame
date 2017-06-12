'use strict';

var kalah = angular.module('kalah', [ 'ngRoute' ]);

kalah.config([ '$routeProvider', '$sceProvider',

function($routeProvider, $sceProvider) {
	$routeProvider.when('/start', {templateUrl : '/KalahGame-Frontend/html/home.html', controller : 'kalahController'})
		.otherwise({
			redirectTo : '/start'
		});
	
	$sceProvider.enabled(false);
	
} ]);

kalah.controller('kalahController', function($scope, $http, $window) {

	// Function responsible to created a new game
	$http.get('/KalahGame-Backend/services/new/Leandro/Bianchini').success(function(data) {
		$scope.board = data;
	});

	// Function that is called when a pit is clicked
	$scope.move = function(playerNumber, pit) {
		$http.get('/KalahGame-Backend/services/move/' + playerNumber + '/' + pit).success(function(data) {
					$scope.board = data;
				});
	}

	// Function that is called when a pit is clicked
	$scope.pitType = function(type) {
		if (type == 'House') {
			return 'pit house';
		} else {
			return 'pit store';
		}
	}

});
