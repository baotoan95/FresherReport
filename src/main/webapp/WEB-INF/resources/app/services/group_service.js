'use strict'
app.factory('GroupService', [ '$http', '$q', 'Config', function($http, $q, config) {
	var REST_SERVICE_API = config.api + "dashboard";

	var service = {
		fetchStatistic : fetchStatistic
	}
	return service;

	function fetchStatistic(fromDate,toDate) {
		var defer = $q.defer();
		$http.get(REST_SERVICE_API+"?fromDate="+formatDate(fromDate)+"&toDate="+formatDate(toDate)).then(function(response) {
			defer.resolve(response.data);
		}, function(error) {
			defer.reject(error);
		});
		return defer.promise;
	}
	
	function formatDate(date) {
	    var d = new Date(date),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear();

	    if (month.length < 2) month = '0' + month;
	    if (day.length < 2) day = '0' + day;
	    var strResult = [day, month, year].join('-');
	    return strResult;
	}
} ]);