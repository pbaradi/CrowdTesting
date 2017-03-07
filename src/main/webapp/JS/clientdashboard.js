var clientDashboardApp = angular.module("clientDashboardApp",[]);

clientDashboardApp.controller("clientDashboardAppCtrl", function($scope, $http){
	
	$http({
		method : 'GET',
		url : 'http://localhost:8080/cloudtesting/rest/client/getProjectUsers/1'
	}).success(function(data, status, headers,config) {
												 $scope.userProjects = data;
											}).error(
												function(data, status, headers,
												config) {
												alert("Something went wrong while listing the projects. Please try again");
											});
	
	$scope.dashboard = function(){
		window.location.href = "http://localhost:8080/cloudtesting/HTML/Login.html";
	};
	
	$scope.profile = function(){
		window.location.href = "http://localhost:8080/cloudtesting/HTML/UploadProject.html";
	};
	
	$scope.logout = function(){
		window.location.href = "http://localhost:8080/cloudtesting/HTML/Login.html";
	};
});