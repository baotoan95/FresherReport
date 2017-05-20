'use strict';

app.factory('ClassService', ['$http', '$q', 'Config', function($http, $q, config) {
	var REST_SERVICE_API = config.api + "rest-class";
	
	var service = {
			fetchByStatus: fetchByStatus,
			fetchById: fetchById,
			createClass: createClass,
			createIssue: createIssue,
			createSolution: createSolution,
			updateIssue: updateIssue,
			updatePlanning: updatePlanning,
	}
	
	return service;
	
	function createSolution(siteClass){
		var defer = $q.defer();
		$http.post(REST_SERVICE_API+"/create-solution", siteClass).then(
		function(response){
			defer.resolve(response.data);
		},function(error){
			defer.reject(error);
		});
		return defer.promise;
	}
	function updateIssue(siteClass){
		var defer = $q.defer();
		$http.post(REST_SERVICE_API+"/update-issue", siteClass).then(
		function(response){
			defer.resolve(response.data);
		},function(error){
			defer.reject(error);
		});
		return defer.promise;
	}
	function createIssue(siteClass){
		var defer = $q.defer();
		$http.post(REST_SERVICE_API+"/create-issue", siteClass).then(
		function(response){
			defer.resolve(response.data);
		},function(error){
			defer.reject(error);
		});
		return defer.promise;
	}
	function createClass(newClass){
		var defer = $q.defer();
		$http.post(REST_SERVICE_API + "/create-class", newClass).then(
				function(response){
					defer.resolve(response.data);
				},function(error){
					defer.reject(error);
				});
		return defer.promise;
	}
	function updatePlanning(newClass){
		var defer = $q.defer();
		$http.post(REST_SERVICE_API + "/update-planning", newClass).then(
				function(response){
					defer.resolve(response.data);
				},function(error){
					defer.reject(error);
				});
		return defer.promise;
	}
	
	function fetchByStatus(group, status, fromDate, toDate) {
		var defer = $q.defer();
		$http.get(REST_SERVICE_API + "/classes/" + group +"/"+ status+"/"+formatDate(fromDate)+"/"+formatDate(toDate))
		.then(
			function(response) {
				defer.resolve(response.data);
			}, function(error) {
				defer.reject(error);
			}
		);
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
	
	function fetchById(id) {
		var defer = $q.defer();
		$http.get(REST_SERVICE_API + "/class/" + id)
		.then(
			function(response) {
				defer.resolve(response.data);
			}, function(error) {
				defer.reject(error);
			}
		);
		return defer.promise;
	}
}]);