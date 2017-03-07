var addBugApp = angular.module("addBugApp", []);

addBugApp.controller("addBugAppCtrl", function($scope, $http){
	
	$scope.addBug = function(){
	 $http({
			method : 'POST',
			url : 'http://localhost:8080/cloudtesting/rest/tester/addBug',
			data : { 
				
					"user": {
						"userId": 1
					},
					"project": {
						"projectId": 1
					},
					"bugDescription": $scope.bugDesc,
					"bugType": $scope.bugType
				
				},
			headers : {
				'Content-Type' : 'application/json'
			}
			
		}).success(function(data, status, headers,config) {
			 alert("Bug added Successfully");
			 window.location.href = "http://localhost:8080/cloudtesting/HTML/TesterDashboard.html" ;
		}).error(
			function(data, status, headers,config) {
				alert("Something went wrong while adding the bug. Please try again");
		}) ;
	};
	$scope.dashboard = function(){
		window.location.href = "http://localhost:8080/cloudtesting/HTML/TesterDashboard.html";
	};
	
	$scope.logout = function(){
		window.location.href = "http://localhost:8080/cloudtesting/HTML/Login.html";
	};
});