'use strict';

app.controller('LoginController', ['$scope', '$rootScope', 'LoginService', '$location',
	function($scope, $rootScope, loginService, $location) {
	$scope.user = {}
	$rootScope.namemenu = "Login";
	
	$scope.login = function() {
		loginService.login($scope.formAdata).then(function(data) {
			$rootScope.authenticated = true;
			$location.path('/');
		}, function(error) {
			// Unauthentication: 401
			$rootScope.authenticated = false;
		});
	}
	
	$scope.logout = function() {
		loginService.logout().then(function(data) {
			$rootScope.authenticated = false;
		}, function(error) {
			$rootScope.authenticated = true;
		});
	}
	
	$scope.checkAuthentication = function() {
		loginService.checkAuthentication().then(function(data) {
			$rootScope.authenticated = data;
		}, function(error) {
			$rootScope.authenticated = false;
		});
	}
}]);