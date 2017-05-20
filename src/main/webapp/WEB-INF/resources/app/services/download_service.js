'use strict';

app.factory('DownloadService', [
		'$http',
		'$q',
		'Config',
		'$timeout',
		function($http, $q, config, $timeout) {
			var REST_SERVICE_API = config.api + "download";

			var service = {
				getListTemplate : getListTemplate,
				download : download
			}

			return service;

			function getListTemplate() {
				var defer = $q.defer();
				$http.get(REST_SERVICE_API + '/list-templates').then(
						function(response) {
							defer.resolve(response.data);
						}, function(error) {
							defer.reject(error);
						});
				return defer.promise;
			}

			function download(fileName) {
				var defer = $q.defer();
				$timeout(function() {
					var downloadPath = REST_SERVICE_API + '/' + fileName;
					window.open(downloadPath, '_parent', '');
				}, 10).then(function() {
					defer.resolve('success');
				}, function() {
					defer.reject('error');
				});
				return defer.promise;
			}
		} ]);