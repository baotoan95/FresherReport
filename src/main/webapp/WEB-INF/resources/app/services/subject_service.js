'use strict';

app.factory('SubjectService', [ '$http', '$q', 'Config', function($http, $q, config) {
	var REST_SERVICE_API = config.api + "rest-subject";

	var service = {
		fetchAll : fetchAll
	}

	return service;

	function fetchAll() {
		var defer = $q.defer();
		$http.get(REST_SERVICE_API).then(function(response) {
			defer.resolve(response.data);
		}, function(error) {
			defer.reject(error);
		});
		return defer.promise
	}
} ])