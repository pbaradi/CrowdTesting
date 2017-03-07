var bugListApp = angular.module("bugListApp", []);

bugListApp.controller("bugListAppCtrl", function($scope, $http){

$scope.editBug = function(bug) {
  if (bug == 'new') {
    $scope.edit = true;
    //$scope.incomplete = true;
    } else {
    $scope.edit = false;
    $scope.s = bug;
     
  }
};
		
		$http({
			method : 'GET',
			url : 'http://localhost:8080/cloudtesting/rest/admin/bugprojectid/1'
		}).success(function(data, status, headers,config) {
													 $scope.bugs = data;
													
												}).error(
													function(data, status, headers,
													config) {
													alert("Something went wrong while retrieving the bugs. Please try again");
												});
		$scope.logout = function(){
			window.location.href = "http://localhost:8080/cloudtesting/HTML/Login.html";
		};
		$scope.dashboard = function(){
			window.location.href = "http://localhost:8080/cloudtesting/HTML/AdminDashboard.html";
		};
						
		});

