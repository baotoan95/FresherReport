app.controller('TraineeController', [ '$scope', '$location', 'TraineeService',
		'$stateParams', '$rootScope', 'PaginationService',
		function($scope, $location, traineeService, $stateParams, $rootScope, paginationService) {
		$scope.getPage = getPage;
		$scope.pager = {};
		var traineeController = this;
		traineeController.pageSize = 10;
		$scope.changePageSize = changePageSize;
		$rootScope.namemenu = "Trainee management";
		
		findById();
		
		function changePageSize() {
			getPage($scope.pager.currentPage);
		}

		function getPage(page) {
			traineeService.fetchAll().then(function(data) {
				if(page < 1 || page > $scope.pager.totalPages) {
					return;
				}
				$scope.pager = paginationService.pager(data.length, page, traineeController.pageSize);
				
				$scope.trainees = data.slice($scope.pager.startIndex, $scope.pager.endIndex + 1);
			}, function(error) {
				console.log(error);
			});
		}
		
		function findById() {
			if($stateParams.id !== undefined) {
				traineeService.fetchById($stateParams.id).then(function(data) {
					$scope.trainee = data;
				}, function(error) {
					console.log(error);
				});
			} else {
				getPage(1);
			}
		}
		function findByStatus(){
			traineeService.fetchByStatus($stateParams.status).then(function(data){
				$scope.trainee = data;
			},function(error){
				console.log(error);
			});
		}

} ]);
