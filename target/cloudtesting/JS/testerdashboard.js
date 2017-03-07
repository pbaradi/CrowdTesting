var testerDashboardApp = angular.module('testerDashboardApp', []);
    // Get All Projects
testerDashboardApp.controller("testerDashboardController", function ($scope, $http) {
    $http.get('http://localhost:8080/cloudtesting/rest/tester/projects')
         .success(function (data,status,headers,config) 
        {
          $scope.responses = data;
        })
         .error(function(data,status,headers,config)
         {
        	 alert("Something went wrong while listing the projects. Please try again");
         });
    $scope.download = function(projectId){
    	 $http.get('http://localhost:8080/cloudtesting/rest/tester/download?projectId='+projectId)
         .success(function (data,status,headers,config) 
        {
        	 alert("Downloaded successfully to downloads");
        	 alert("data : " +data);
          $scope.responses = data;
        })
         .error(function(data,status,headers,config)
         {
        	 alert("Something went wrong while listing the projects. Please try again");
         });
    };
    $scope.submitBugs = function(){
    	window.location.href = "http://localhost:8080/cloudtesting/HTML/AddBug.html";
    };
    $scope.logout = function(){
    	window.location.href = "http://localhost:8080/cloudtesting/HTML/Login.html";
    };
});