<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<title>Fresh Report</title>

<link rel="icon" type="image/png"
	href="resources/themes/lbd/assets/img/favicon.ico">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
	name='viewport' />
<meta name="viewport" content="width=device-width" />
<!-- Bootstrap core CSS     -->
<link
	href="<c:url value='/resources/bower_components/bootstrap/dist/css/bootstrap.min.css'/>"
	rel="stylesheet" />
<!-- Animation library for notifications   -->
<link
	href="<c:url value='/resources/themes/lbd/assets/css/animate.min.css'/>"
	rel="stylesheet" />
<!--  Light Bootstrap Table core CSS    -->
<link
	href="<c:url value='/resources/themes/lbd/assets/css/light-bootstrap-dashboard.css'/>"
	rel="stylesheet" />
<link href="<c:url value='/resources/app/css/my-css.css'/>"
	rel="stylesheet" />
<!--  CSS for Demo Purpose, don't include it in your project     -->
<link rel="stylesheet"
	href="<c:url value='/resources/bower_components/angular-loading-bar/build/loading-bar.css'/>" />
<!-- <link rel="stylesheet" -->
<link rel="stylesheet"
	href="<c:url value='/resources/bower_components/jquery-ui/jquery-ui.css'/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/bower_components/angular-datepicker/dist/angular-datepicker.min.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/bower_components/angular-bootstrap/ui-bootstrap-csp.css'/>">
<!--     Fonts and icons     -->
<link
	href="<c:url value='/resources/bower_components/font-awesome/css/font-awesome.min.css'/>"
	rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300'
	rel='stylesheet' type='text/css'>
<link
	href="<c:url value='/resources/themes/lbd/assets/css/pe-icon-7-stroke.css'/>"
	rel="stylesheet" />
<!-- ADM datetimepicker -->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/bower_components/ADM-dateTimePicker-master/dist/ADM-dateTimePicker.css'/>">
<!-- editable bootstrap -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/bower_components/bootstrap3-editable-1.5.1/bootstrap3-editable/css/bootstrap-editable.css"/>">
	
</head>

<body ng-app="app">
	<div class="wrapper">
		<ng-side-bar></ng-side-bar>
		<div class="main-panel">
			<ng-top-bar></ng-top-bar>
			<!-- start to loading application -->
			<div class="content" ui-view></div>
		</div>
	</div>
</body>

<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script src="resources/bower_components/jquery/dist/jquery.js"></script>
<script src="resources/bower_components/jquery-ui/jquery-ui.js"></script>
<script src="resources/bower_components/bootstrap/dist/js/bootstrap.js"></script>
<script src="resources/bower_components/angular/angular.js"></script>
<script src="//cdn.rawgit.com/auth0/angular-storage/master/dist/angular-storage.js"></script>
<script src="resources/bower_components/ng-file-upload/ng-file-upload-shim.min.js"></script>
<script src="resources/bower_components/ng-file-upload/ng-file-upload.min.js"></script> 
<script
	src="resources/bower_components/angular-ui-router/release/angular-ui-router.js"></script>
<script
	src="resources/bower_components/angular-loading-bar/build/loading-bar.min.js"></script>
<script type="text/javascript"
	src="resources/bower_components/angular-datepicker/dist/angular-datepicker.min.js"></script>

<script type="text/javascript"
	src="resources/bower_components/js/jquery.fileupload.js"></script>
<script type="text/javascript"
	src="resources/bower_components/js/jquery.iframe-transport.js"></script>
<script type="text/javascript"
	src="resources/bower_components/js/jquery.ui.widget.js"></script>

<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.js"></script>
<script type="text/javascript"
	src="resources/bower_components/angular-chart/angular-chart.js"></script>
<script type="text/javascript"
	src="resources/bower_components/angular-chart/angular-chart.min.js"></script>
<script type="text/javascript"
	src="resources/bower_components/js/update_progress_bar_for_progressbar.js"></script>
<script type="text/javascript"
	src="resources/bower_components/angular-bootstrap/ui-bootstrap.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.3/underscore-min.js"></script>

<script type="text/javascript" src="resources/app/app.js"></script>
<script type="text/javascript" src="resources/app/config.js"></script>
<script type="text/javascript"
	src="resources/app/services/pagination_service.js"></script>
<script type="text/javascript"
	src="resources/app/services/class_service.js"></script>
<script type="text/javascript"
	src="resources/app/controller/class_controller.js"></script>
<script type="text/javascript"
	src="resources/app/services/waiting_statistic_service.js"></script>
<script type="text/javascript"
	src="resources/app/services/group_service.js"></script>
<script type="text/javascript"
	src="resources/app/controller/group_controller.js"></script>
<script type="text/javascript"
	src="resources/app/services/trainee_service.js"></script>
<script type="text/javascript"
	src="resources/app/controller/trainee_controller.js"></script>
<script type="text/javascript"
	src="resources/app/services/subject_service.js"></script>
<script type="text/javascript"
	src="resources/app/controller/subject_controller.js"></script>
<script type="text/javascript"
	src="resources/app/controller/report_controller.js"></script>
<script type="text/javascript"
	src="resources/app/services/download_service.js"></script>
<script type="text/javascript"
	src="resources/app/controller/download_controller.js"></script>
<script type="text/javascript"
	src="resources/app/services/login_service.js"></script>
<script type="text/javascript"
	src="resources/app/controller/login_controller.js"></script>
<!-- ADM date timepicker -->
<script type="text/javascript"
	src="resources/bower_components/ADM-dateTimePicker-master/dist/ADM-dateTimePicker.min.js"></script>
<!-- counter angular -->
<script type="text/javascript"
	src="resources/bower_components/angular-count/angular-count-to.min.js"></script>
	<!-- editable bootstrap -->
<script type="text/javascript"
	src="resources/bower_components/bootstrap3-editable-1.5.1/bootstrap3-editable/js/bootstrap-editable.js"></script>
<script type="text/javascript"
	src="resources/bower_components/bootstrap3-editable-1.5.1/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
	
</html>
