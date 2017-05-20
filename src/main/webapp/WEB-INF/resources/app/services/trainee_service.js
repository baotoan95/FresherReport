'use strict';

app.factory('TraineeService', [ '$http', '$q', 'Config', function($http, $q, config) {
	var REST_SERVICE_API = config.api + "rest-trainee";

	var service = {
		fetchAll : fetchAll,
		fetchById: fetchById,
		fetchByStatus: fetchByStatus 
	}

	return service;

	function fetchAll() {
		var defer = $q.defer();
		$http.get(REST_SERVICE_API).then(function(response) {
			defer.resolve(response.data);
		}, function(error) {
			defer.reject(error);
		});
		return defer.promise;
	}
	
	function fetchById(id) {
		var defer = $q.defer();
		$http.get(REST_SERVICE_API + "/trainee/" + id).then(function(response) {
			defer.resolve(response.data);
		}, function(error) {
			defer.reject(error);
		});
		return defer.promise;
	}
	function fetchByStatus(status){
		var defer = $q.defer();
		$http.get(REST_SERVICE_API+"/trainees/"+status).then(function(response){
			defer.resolve(response.data)
		},function(error){
			defer.reject(error);
		});
		return defer.promise;
	}
} ])