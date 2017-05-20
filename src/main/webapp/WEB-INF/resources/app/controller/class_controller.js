app.controller('ClassController', [
		'$scope',
		'$location',
		'ClassService',
		'$stateParams',
		'$rootScope',
		'PaginationService',
		'$compile',
		'Upload',
		'Config',
		'$timeout',
		function($scope, $location, classService, $stateParams, $rootScope,
				paginationService,$compile, uploadFile, config, $timeout) {
			$scope.fetchByStatus = fetchByStatus;
			$scope.fetchById = fetchById;
			$scope.createClass = createClass;
			$scope.stateParams = $stateParams;
			$scope.pager = {};
			$scope.pageSize = 10;
			$scope.changePageSize = changePageSize;
			$rootScope.namemenu = "Class management";
			$scope.currentClass;
			$scope.currentIssue;
			$scope.currentSolutionId;
			
			$scope.typeShow = 'card';
			
			$scope.listNewSolution = [];
			
			$scope.newClass = {
				site : "",
				plannedStartDate : new Date(),
				plannedEndDate : new Date(),
				plannedLearningTime : "",
				planCountStudent : "",
				plannedExpense : "",
				projectCode : "",
			}

			fetchById();

			fetchByStatus(1);

			function changePageSize() {
				fetchByStatus($scope.pager.currentPage);
			}
			
			function createClass() {
				$scope.newClass.plannedStartDate = new Date($scope.newClass.plannedStartDate);
				$scope.newClass.plannedEndDate = new Date($scope.newClass.plannedEndDate);
				classService.createClass($scope.newClass).then(function(data) {
					$location.path("classes");
				}, function(error) {
					console.log(error);
				});
			}
			
			$scope.updatePlanning = function(){
				classService.updatePlanning($scope.newClass).then(function(data){
					$location.path("classes");
				}, function(error){
					console.log(error);
				});
			}
			
			function fetchById() {
				if ($stateParams.id !== undefined) {
					classService.fetchById($stateParams.id).then(
							function(data) {
								$scope.aClass = data;
								if($scope.aClass.siteClass.status === "Planning") {
									$scope.newClass = $scope.aClass.siteClass;
								}
							}, function(error) {
								console.log(error);
							});
				}
			}

			function fetchByStatus(page) {
				var status = "total";
				var group;
				var fromDate;
				var toDate;
				if ($stateParams.status !== undefined) {
					status = $stateParams.status;
				}
				if ($stateParams.group !== undefined) {
					group = $stateParams.group;
				}
				if ($stateParams.fromDate !== undefined) {
					fromDate = $stateParams.fromDate;
				}
				if ($stateParams.toDate !== undefined) {
					toDate = $stateParams.toDate;
				}
				
				classService.fetchByStatus(group, status, fromDate, toDate).then(
						function(data) {
							if (page < 1 || page > $scope.pager.totalPages) {
								return;
							}
							$scope.pager = paginationService.pager(data.length,
									page, $scope.pageSize);
							$scope.classes = data.slice(
									$scope.pager.startIndex,
									$scope.pager.endIndex + 1);
						}, function(error) {
							console.log(error);
						});
			}
			
			$scope.showTraineeDetail = function(traineeId) {
				$location.path('trainee-detail/'+traineeId)
			}

			$scope.showClassDetail = function(classOne) {
				if(classOne.status === "Planning") {
					if($rootScope.authenticated) {
						$location.path('change-class-planning/' + classOne.id);
					} else {
						alert("Please login to view or update this class");
					}
				} else {
					$location.path('class-detail/' + classOne.id);
				}
			}
			
			$scope.showIssue = function(classOne) {
				classService.fetchById(classOne.id).then(
					function(data) {
						$scope.currentClass = data;
						
					}, function(error) {
						console.log(error);
					});
			}
			
			$scope.createIssue = function(contentNewIssue){
				newIssue = {
						id: $scope.currentClass.siteClass.id+"_"+($scope.currentClass.siteClass.listIssue.length + 1),
						content : contentNewIssue,
						dateCreate: new Date(),
						dateUpdate : new Date(),
						listSolution : []
				};
				
				$scope.currentClass.siteClass.listIssue.push(newIssue);
				classService.createIssue($scope.currentClass.siteClass).then(
				function(data){
					$scope.currentClass.siteClass = data;
					
				},function(error){
					console.log(error);
				});  
			}

		    $scope.addRowSolution = function(issue){
		    	$scope.currentIssue = issue;
		    	$('.add-new-solution-group').each(function(){
		    		$(this).html('');	
		    		if($(this).attr('id') ==  issue.id.replace('.', '_') + "_group_solution"){
		    			angular.element($(this)).html( $compile('<ng-add-solution></ng-add-solution>')($scope));
		    		}
		    	});
			    $scope.listNewSolution.push({id:'temp',content: 'write solution here!'});
			}
		    function checkEqualsIssueId(issue) {
		        return issue.id	 == $scope.currentIssue.id;
		    }
		    function checkEqualsSolutionId(Solution) {
		        return Solution.id	 == $scope.currentSolutionId;
		    }

			$scope.saveSolution = function(newContent){
				var saveSolution = {
						id : "",
						content: "",
						dateCreate: new Date(),
						dateUpdate: new Date()
				};
				if($scope.currentSolutionId == "temp"){
					var lengthListSolution = 1;
					if($scope.currentIssue.listSolution != null){
						lengthListSolution = $scope.currentIssue.listSolution.length + 1;	
					}
					saveSolution.id = $scope.currentIssue.id + "_" + lengthListSolution;
					saveSolution.content = newContent;
					$scope.currentClass.siteClass.listIssue.find(checkEqualsIssueId).listSolution.push(saveSolution);
				}else{
					$scope.currentClass.siteClass.listIssue.find(checkEqualsIssueId).listSolution.find(checkEqualsSolutionId).content = newContent;
				}
				classService.createSolution($scope.currentClass.siteClass).then(function(data){
					$scope.currentClass.siteClass.listIssue.find(checkEqualsIssueId).listSolution = data.listIssue.find(checkEqualsIssueId).listSolution;
				},function(error){
					console.log(error);
				});	
			}
			
			$scope.initEditableSolution = function(solutionId){
				if(!$rootScope.authenticated) {
					return;
				}
				$('#'+solutionId).editable({
					type : 'textarea',
					name : 'comments',
					title : 'Edit Solution',
					mode : 'inline',
					success: function(response, newContent) {
						var content = newContent;
						if($scope.listNewSolution.length > 0){
							$scope.listNewSolution.pop(1);	
						}
						$scope.saveSolution(newContent);
				    }
				}).on('hidden',function (e, reason){
					if(solutionId == "temp" && reason == "cancel"){
						$scope.listNewSolution.pop(1);
						$('#temp').remove();
					}
				}).on("shown", function (element, test) {
				    $(test.input.$input[0]).attr("cols", "70")            
				});;	
			}
			
			$scope.chooseSolution = function(solutionId){
				$scope.currentSolutionId = solutionId;
				console.log($scope.currentSolutionId);
			}
			
			$scope.chooseIssue = function (issue){
				$scope.currentIssue = issue;
				if($scope.listNewSolution.length > 0){
					$scope.listNewSolution.pop(1);	
				}
			}
			
			$scope.updateIssue = function(issue){
				$scope.currentIssue = issue;
				$scope.currentClass.siteClass.listIssue.find(checkEqualsIssueId).content = issue.content;
				classService.updateIssue($scope.currentClass.siteClass).then(function(data){
					$scope.currentClass.siteClass = data;
				},function(error){
					console.log(error);
				});
				$('.issue-fixed-part').css('display','block');
				$('.issue-editable-part').css('display','none');
				$('.icon-save-issue').css('display','none');
				$('.edit-issue-icon').css('display','block');
			}
			
			$scope.showEditableIssue = function(issueId){
				
				$('#'+issueId+'_issue_one .issue-fixed-part').css('display','none');
				$('#'+issueId+'_issue_one .issue-editable-part').css('display','block');
				$('#'+issueId+'_issue_one .icon-save-issue').attr('style','display:block !important');
				$('#'+issueId+'_issue_one .edit-issue-icon').css('display','none');
			}
			
			// For upload head teacher file
			$scope.uploadHeadTeacher = function(file, classId) {
			    file.upload = uploadFile.upload({
			    	url: config.api + 'import-headteacher',
			    	data: {classId: classId, file: file},
			    });

			    file.upload.then(function (response) {
			    	$timeout(function () {
			    		file.result = response.data;
			    		fetchById();
			    	});
			    }, function (response) {
			    	if (response.status > 0) {
			    		$scope.errorMsg = 'Import failed! Please check file and import again';
			      	}
			    }, function (evt) {
			    	// Math.min is to fix IE which reports 200% sometimes
				    file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
			    });
			}
			
			// For upload class info file
			$scope.uploadClassInfo = function(file, classId) {
			    file.upload = uploadFile.upload({
			    	url: config.api + 'import-class-info',
			    	data: {classId: classId, file: file},
			    });

			    file.upload.then(function (response) {
			    	$timeout(function () {
			    		file.result = response.data;
			    		fetchById();
			    	});
			    }, function (response) {
			    	if (response.status > 0 || response.result === false) {
			    		$scope.errorMsg = 'Import failed! Please check file and import again';
			    	}
			    }, function (evt) {
			    	// Math.min is to fix IE which reports 200% sometimes
			    	file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
			    });
			}
			// For upload classes
			$scope.uploadClasses = function(file) {
			    file.upload = uploadFile.upload({
			    	url: config.api + 'import-classes',
			    	data: {file: file},
			    });

			    file.upload.then(function (response) {
			    	$timeout(function () {
			    		file.result = response.data;
			    		$scope.pager = {};
			    		fetchByStatus(1);
			    	});
			    }, function (response) {
			    	if (response.status > 0) {
			    		$scope.errorMsg = 'Import failed! Please check file and import again';
			    	}
			    }, function (evt) {
			    	// Math.min is to fix IE which reports 200% sometimes
			    	file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
			    });
			}
			
			$scope.changeShowType = function(typeShow) {
				$scope.typeShow = typeShow;
			}
			
			// For upload internship
			$scope.uploadInternships = function(file) {
			    file.upload = uploadFile.upload({
			    	url: config.api + 'import-internships',
			    	data: {file: file},
			    });

			    file.upload.then(function (response) {
			    	$timeout(function () {
			    		file.result = response.data;
			    		$scope.pager = {};
			    		fetchByStatus(1);
			    	});
			    }, function (response) {
			    	console.log(response.status);
			    	if (response.status > 0) {
			    		$scope.errorMsg = 'Import failed! Please check file and import again';
			    	}
			    }, function (evt) {
			    	// Math.min is to fix IE which reports 200% sometimes
			    	file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
			    });
			}
			
			// For upload interview result
			$scope.uploadInterviewResult = function(file, classId) {
			    file.upload = uploadFile.upload({
			    	url: config.api + 'import-interview-result',
			    	data: {classId: classId, file: file},
			    });

			    file.upload.then(function (response) {
			    	$timeout(function () {
			    		file.result = response.data;
			    		fetchById();
			    	});
			    }, function (response) {
			    	if (response.status > 0) {
			    		$scope.errorMsg = 'Import failed! Please check file and import again';
			      	}
			    }, function (evt) {
			    	// Math.min is to fix IE which reports 200% sometimes
				    file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
			    });
			}
			
			// For upload attendance
			$scope.uploadAttendance = function(file, classId) {
			    file.upload = uploadFile.upload({
			    	url: config.api + 'import-attendance',
			    	data: {classId: classId, file: file},
			    });

			    file.upload.then(function (response) {
			    	$timeout(function () {
			    		file.result = response.data;
			    		fetchById();
			    	});
			    }, function (response) {
			    	if (response.status > 0) {
			    		$scope.errorMsg = 'Import failed! Please check file and import again';
			      	}
			    }, function (evt) {
			    	// Math.min is to fix IE which reports 200% sometimes
				    file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
			    });
			}
		} ]);