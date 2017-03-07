var IntroApp = angular.module('IntroApp',[]);

IntroApp.controller("IntroAppController", function($scope,$http) {
	
	$scope.signIn = function(){
		window.location.href = "http://localhost:8080/cloudtesting/HTML/Login.html";
	};
	
	$scope.signUp = function(){
		window.location.href = "http://localhost:8080/cloudtesting/HTML/Register.html";

	};
	
});