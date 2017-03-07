var loginApp = angular.module("loginApp", []);

loginApp
		.controller(
				"loginController",
				function($scope, $http) {
					
					$scope.login = function() {
						 if ($scope.userFlag == 'admin') {
								if($scope.email_id == 'admin@cst.com' && $scope.password == 'admin123'){
								window.location.href = "http://localhost:8080/cloudtesting/HTML/AdminDashboard.html";
								}else{
									alert("Please enter the correct credentials for admin login");
								}
							}else{
						$http(
								{
									method : 'POST',
									url : 'http://localhost:8080/cloudtesting/rest/tester/login',
									data : {
										"emailId" : $scope.email_id,
										"password" : $scope.password,
										"userFlag" : $scope.userFlag

									}
								})
								.success(
										function(data, status, headers, config) {
											$scope.message = "Login Successful!!!";
											$scope.response = data;
											if ($scope.userFlag == 'tester') {
												window.location.href = "http://localhost:8080/cloudtesting/HTML/TesterDashboard.html";
											} else if ($scope.userFlag == 'client') {
												window.location.href = "http://localhost:8080/cloudtesting/HTML/ClientDashboard.html";
											}
										})
								.error(
										function(data, status, headers, config) {
											alert("Something went wrong. Please make sure all the fields are correct and try again!");
											window.location.href = "http://localhost:8080/cloudtesting/HTML/Login.html";
										});
					}
					};

					$scope.register = function() {
						window.location.href = "http://localhost:8080/cloudtesting/HTML/Register.html";
					};
				});