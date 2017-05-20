'use strict';

var app = angular.module('app', ['ui.router', 'angular-loading-bar','chart.js', 
	'ADM-dateTimePicker','countTo','ngFileUpload', 'angular-storage']);

app.config(['$stateProvider', '$urlRouterProvider', 'cfpLoadingBarProvider', 
            function($stateProvider, $urlRouterProvider, cfpLoadingBarProvider) {
	// Default state
	$urlRouterProvider.otherwise("/page-not-found");
	
	// Routing configuration
	$stateProvider.state('page-not-found', {
		url: "/page-not-found",
		templateUrl: "resources/app/templates/page-not-found.html",
	}).state('/', {
		url: "/",
		templateUrl: "resources/app/templates/dashboard.html",
		controller: "GroupController"
	}).state('classesByStatus', {
		url: "/classes/:group/:status/:fromDate/:toDate",
		templateUrl: "resources/app/templates/classes.html",
		controller: "ClassController"
	}).state('classes', {
		url: "/classes",
		templateUrl: "resources/app/templates/classes.html",
		controller: "ClassController"
	}).state('class-detail', {
		url: '/class-detail/:id',
		templateUrl: 'resources/app/templates/class-detail.html',
		controller: "ClassController"
	}).state('createclass',{
		url:'/create-class',
		templateUrl: 'resources/app/templates/create-class.html',
		controller: "ClassController"
	}).state('trainees', {
		url: '/trainees',
		controller:"TraineeController",
		templateUrl: 'resources/app/templates/trainees.html'
	}).state('traineesByStatus',{
		url:'/trainees/:status',
		controller:'TraineeController',
		templateUrl:'resources/app/templates/trainees.html'
	}).state('subjects', {
		url: '/subjects',
		controller:"SubjectController",
		templateUrl: 'resources/app/templates/subjects.html'
	}).state('report', {
		url: '/report',
		controller:'ReportController',
		templateUrl: 'resources/app/templates/report.html'
	}).state('trainee-detail', {
		url:'/trainee-detail/:id',
		templateUrl: 'resources/app/templates/trainee-detail.html',
		controller: "TraineeController"
	}).state('list-templates',{
		url:'/list-templates',
		templateUrl:'resources/app/templates/download-templates.html',	
		controller:"DownloadController"
	}).state('login',{
		url:'/login',
		templateUrl: 'resources/app/templates/login.html',
		controller:"LoginController"
	}).state('change-class-planning',{
		url:'/change-class-planning/:id',
		templateUrl: 'resources/app/templates/create-class.html',
		controller: "ClassController"
	});
	
	// Loading bar configuration
	cfpLoadingBarProvider.includeSpinner = true;
	cfpLoadingBarProvider.includeBar = true;
	cfpLoadingBarProvider.latencyThreshold = 0;
}]);


// Init after config
app.run(function($rootScope, $location) {
	$rootScope.range = range;
	$rootScope.namemenu = "Dashboard";
	$location.path('/');
});

// Common functions
function range(numb) {
	return new Array(numb);
}

//Create directives
app.directive('ngTopBar', function() {
	return {
		restrict: "E",
		templateUrl: "resources/app/templates/top-bar.html"
	}
})
.directive('ngSideBar', function() {
	return {
		restrict: "E",
		templateUrl: "resources/app/templates/side-bar.html"
	}
})
.directive('ngTraineeOneCard', function() {
	return {
		restrict: "E",
		templateUrl: "resources/app/templates/trainee-one-card.html"
	}
})
.directive('ngTraineeOneRow', function() {
	return {
		restrict: "E",
		templateUrl: "resources/app/templates/trainee-one-row.html"
	}
})
.directive('ngAddSolution',function(){
	return{
		restrict:"E",
		templateUrl:"resources/app/templates/add-solution.html"
	}
}).directive('ngIssueSolutionPopup',function(){
	return{
		restrict:"E",
		templateUrl:"resources/app/templates/issue-solution-popup.html"
	}
});;
