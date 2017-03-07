testerBuglistApp = angular.module("testerBuglistApp", []);

testerBuglistApp.controller("testerBuglistAppCtrl", function($scope, $http){
	$scope.critical = 10;
	$scope.high = 7;
	$scope.low = 3;
	
	$http({
method : 'GET',
url : 'http://localhost:8080/cloudtesting/rest/admin/buguserid/1'
}).success(function(data, status, headers,config) {
		 $scope.users = data;
		
	}).error(
		function(data, status, headers,
		config) {
		alert("Something went wrong while retrieving the bugs. Please try again");
	});
	
	$scope.calculateIcentives = function(){
		$scope.total=0;
		for(var i=0; i<$scope.users.length;i++){
			if($scope.users[i].bugType =='critical'){
				$scope.total= $scope.total+10;
			}else if($scope.users[i].bugType =='high'){
				$scope.total= $scope.total+7;
			}else if($scope.users[i].bugType =='low'){
				$scope.total= $scope.total+3;
			}else{
				$scope.total= $scope.total+0;
			}
		 
		}	
	};
	
	$scope.logout = function(){
		window.location.href = "http://localhost:8080/cloudtesting/HTML/Login.html";
	};
	$scope.dashboard = function(){
		window.location.href = "http://localhost:8080/cloudtesting/HTML/AdminDashboard.html";
	};
	
});