app.controller('GroupController', [
		'$scope',
		'$location',
		'GroupService',
		'$rootScope',
		'$stateParams',
		'WaitingStatisticService',
		function($scope, $location, groupService, $rootScope, $stateParams, waitingStatisticService) {
			var ctrl = this;
			$rootScope.namemenu = "Dashboard";
			
			var waitingStatistic = {};
			
			$scope.showStatistic = function() {
				if (ctrl.fromDate == undefined && ctrl.toDate == undefined) {
					ctrl.fromDate = new Date("1/1/1");
					ctrl.toDate = new Date();
				}
				if (ctrl.fromDate == "" && ctrl.toDate == "") {
					ctrl.fromDate = new Date("1/1/1");
					ctrl.toDate = new Date();
				}
				
				groupService.fetchStatistic(ctrl.fromDate, ctrl.toDate)
						.then(
								function(data) {
									$scope.groups = data;
									$scope.labels = [ "Waiting", "Release",
											"Running" ];
									$scope.options = {
										legend : {
											display : true
										}
									};
									$scope.colors = [ '#DAA520', '#fb404b',
											'#1dc7ea' ];
								}, function(error) {
									console.log(error);
								});
			}
			
			$scope.showListIssue = function(list, groupNameSelected) {
				$scope.listClassIssueSelected = list;
				$scope.groupNameSelected = groupNameSelected;
			}
			
			/*
			 * type: 'class' or 'person'
			 * */
			$scope.updateStatistic = function(groupId, type, event) {
				if(!$rootScope.authenticated) {
					alert("Please login to update statistic");
					return;
				}
				if($.isNumeric(event.target.innerHTML)) {
					waitingStatistic = {
							group: {id: groupId},
							type: type,
							value: event.target.innerHTML
					};
					
					waitingStatisticService.addStatistic(waitingStatistic).then(function(data) {
						if(data === true) {
							$scope.showStatistic();
						} else {
							alert('Update error')
						}
					}, function(error) {
						console.log(error);
					});
				} else {
					alert('Please input a number');
				}
			}
		} ]);