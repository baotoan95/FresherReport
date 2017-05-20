app.controller('DownloadController', [ 
	'$scope', 
	'$rootScope',
	'DownloadService',
	function($scope, $rootScope, downloadService) {
	$rootScope.namemenu = "Download Templates";
	
	$scope.getListTemplate = function() {
		downloadService.getListTemplate().then(function(data) {
			$scope.listTemplate = data;
		}, function(error) {
			alert('There is no template file on server');
		});
	}
	
	$scope.download = function(fileName) {
		downloadService.download(fileName).then(function(data) {
			// Nothing
		}, function(error) {
			alert('File not found on server');
		});
	}
} ]);