var adminDashboardApp = angular.module("adminDashboardApp",[]);

adminDashboardApp
.controller("adminDashboardController", function($scope, $http){
		$http({
			method : 'GET',
			url : 'http://localhost:8080/cloudtesting/rest/tester/projects'
		}).success(function(data, status, headers,config) {
													 $scope.projects = data;
												}).error(
													function(data, status, headers,
													config) {
													alert("Something went wrong while listing the projects. Please try again");
												});
												
		$http({
			method : 'GET',
			url : 'http://localhost:8080/cloudtesting/rest/tester/testers'
		}).success(function(data, status, headers,config) {
													 $scope.testers = data;
												}).error(
													function(data, status, headers,
													config) {
													alert("Something went wrong while listing the testers. Please try again");
												});
												
		$http({
			method : 'GET',
			url : 'http://localhost:8080/cloudtesting/rest/client'
		}).success(function(data, status, headers,config) {
													 $scope.clients = data;
												}).error(
													function(data, status, headers,
													config) {
													alert("Something went wrong while listing the clients. Please try again");
												});
												
	$scope.logout = function(){
			window.location.href = "http://localhost:8080/cloudtesting/HTML/Login.html";
		};
		
		$scope.bugList = function(){
			alert("buglist");
		};
						
		});