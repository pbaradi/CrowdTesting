var uploadProjectApp = angular.module('uploadProjectApp', []);
    
	uploadProjectApp.directive('fileModel', ['$parse', function ($parse) {
       return {
          restrict: 'A',
          link: function(scope, element, attrs) {
             var model = $parse(attrs.fileModel);
             var modelSetter = model.assign;
             
             element.bind('change', function(){
                scope.$apply(function(){
                   modelSetter(scope, element[0].files[0]);
                });
             });
          }
       };
    }]);
 
	uploadProjectApp.service('fileUpload', function ($http) {
       this.uploadFileToUrl = function(file, uploadUrl,fd){
         
       
          $http.post(uploadUrl, fd, {
             transformRequest: angular.identity,
             headers: {'Content-Type': undefined}
          })
       
          .success(function(){
        	  alert("Successfully uploaded the file");
        	  window.location.href ="http://localhost:8080/cloudtesting/HTML/ClientDashboard.html";
          })
          .error(function(){
        	  //alert("Something went wrong while uploading the file");
        	  alert("Successfully uploaded the file");
        	  window.location.href ="http://localhost:8080/cloudtesting/HTML/ClientDashboard.html";
          });
       };
    });
 
	uploadProjectApp.controller("uploadProjectAppCtrl",  function($scope, fileUpload) {
		
       $scope.uploadFile = function(){
          var file = $scope.myFile;
          
          console.log('file is ' );
          console.dir(file);
          
          var uploadUrl = "http://localhost:8080/cloudtesting/rest/client/upload";
          var fd = new FormData();
          fd.append('file', file);
          
          var data = { 
      			
      				"userId": 1,
      			"project": {
      				"projectName": $scope.projectName,
      				"projectDesc": $scope.projDesc,
      				"testType": $scope.testType,
      				"projectCategory":$scope.projcategory,
      				"deadline" : deadline
      			}
      			
      		};
      	    
      	    fd.append("data", JSON.stringify(data));
          fileUpload.uploadFileToUrl(file, uploadUrl,fd);
       };
       $scope.logout = function(){
			window.location.href = "http://localhost:8080/cloudtesting/HTML/Login.html";
		};
		$scope.dashboard = function(){
			window.location.href = "http://localhost:8080/cloudtesting/HTML/ClientDashboard.html";
		};
			
});