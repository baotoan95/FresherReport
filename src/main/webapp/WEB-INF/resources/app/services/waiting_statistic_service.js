'use strict';

app.factory('WaitingStatisticService', ['$http', '$q', 'Config', function($http, $q, config) {
	var REST_SERVICE_API = config.api + "waiting-statistic";
	
	var service = {
		addStatistic: addStatistic
	}
	
	return service;
	
	function addStatistic(waitingStatistic) {
		var defer = $q.defer();
		$http.post(REST_SERVICE_API + "/add-statistic", waitingStatistic).then(function(response) {
			defer.resolve(response.data);
		}, function(error) {
			defer.reject(error);
		});
		return defer.promise;
	}
}]);