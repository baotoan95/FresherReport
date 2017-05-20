app.controller('SubjectController', [ '$scope', '$location', 'SubjectService',
		'$stateParams',
		function($scope, $location, subjectService, $stateParams) {

			findAll();

			function findAll() {
				subjectService.fetchAll().then(function(data) {
					$scope.subjects = data;
				}, function(error) {
					console.log(error);
				});
			}
} ]);