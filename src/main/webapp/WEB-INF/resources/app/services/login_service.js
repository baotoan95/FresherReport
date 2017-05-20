'use strict';

app.factory('LoginService', [ '$http', '$q', 'Config',
		function($http, $q, config) {
			var REST_SERVICE_API = config.api;

			var service = {
				login: login,
				logout: logout,
				checkAuthentication: checkAuthentication
			}

			return service;

			function login(formAdata) {
				var defer = $q.defer();
				$http({
                    url: REST_SERVICE_API + "login",
                    method: "POST",
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                    data: $.param(formAdata)
                }).success(function(data, status, headers, config) {
                    defer.resolve(status);
                }).error(function(data, status, headers, config) {
                	defer.reject(status);
                });
				return defer.promise;
			}
			
			function logout() {
				var defer = $q.defer();
				$http.get(REST_SERVICE_API + "logout").then(function(response) {
					defer.resolve(response);
				}, function(error) {
					defer.reject(error);
				});
				return defer.promise;
			}
			
			function checkAuthentication() {
				var defer = $q.defer();
				$http.get(REST_SERVICE_API + "authenticated").then(function(response) {
					defer.resolve(response.data);
				}, function(error) {
					defer.reject(error);
				});
				return defer.promise;
			}
		} ]);