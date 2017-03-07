var registerApp = angular.module("registerApp", []);

registerApp.controller("registerAppCtrl", function($scope, $http){
	

						$scope.registerTester = function() {

						if ($scope.password != $scope.confirmPassword) {
							alert("Passwords do not match. Please enter the correct password!!!");
							$scope.password = '';
							$scope.confirmPassword = '';
							return;
						} else {
							$http(
									{
										method : 'POST',
										url : 'http://localhost:8080/cloudtesting/rest/tester/register',
										data : {
											"firstName" : $scope.first_name,
											"lastName" : $scope.last_name,
											"emailId" : $scope.email_id,
											"password" : $scope.password,
											"userFlag" : 'tester',
											"amount" : 25

										},
										headers : {
											'Content-Type' : 'application/json'
										}
									})
									.success(
											function(data, status, headers,
													config) {
												$scope.message = "Congratulations!!!! Registration Successful!!!";
												alert("Congratulations!!!! Registration Successful!!!");
												window.location.href = "http://localhost:8080/cloudtesting/HTML/TesterDashboard.html";
											})
									.error(
											function(data, status, headers,
													config) {
												$scope.error = true;
												alert("Something went wrong while registering the tester. Please try again");
											});
						}

					};
	

						$scope.registerClient = function() {
						if ($scope.password != $scope.confirmPassword) {
							alert("Passwords do not match. Please enter the correct password!!!");
							$scope.password = '';
							$scope.confirmPassword = '';
							return;
						} else {
							$http(
									{
										method : 'POST',
										url : 'http://localhost:8080/cloudtesting/rest/client/register',
										data : {
											"firstName" : $scope.first_name,
											"lastName" : $scope.last_name,
											"emailId" : $scope.email_id,
											"password" : $scope.password,
											"userFlag" : 'client',
											"amount" : 0

										},
										headers : {
											'Content-Type' : 'application/json'
										}
									})
									.success(
											function(data, status, headers,
													config) {
												$scope.message = "Congratulations!!!! Registration Successful!!!";
												alert("Congratulations!!!! Registration Successful!!!");
												window.location.href = "http://localhost:8080/cloudtesting/HTML/ClientDashboard.html";
											})
									.error(
											function(data, status, headers,
													config) {
												$scope.error = true;
												alert("Something went wrong while registering the client. Please try again");
											});
						}
					};
	
});