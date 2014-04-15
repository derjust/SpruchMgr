var sayingApp = angular.module('sayingApp', ['chieffancypants.loadingBar', 'ngAnimate']);

sayingApp.controller('SayingCtrl', // ['$scope', '$routeParams', '$http',
function($scope, /* $routeParams, */$http) {
	$scope.start = 0;
	$scope.update = function() {
		$http.get('services/rest/spruch?start=' + $scope.start + '&length=15')
				.success(function(data) {
					$scope.sayings = data;
				});
	};

	$scope.update();

	$scope.first = function() {
		$scope.start = 0;
		$scope.update();
	};
	
	$scope.next = function() {
		$scope.start += 15;
		$scope.update();
	};

	$scope.previous = function() {
		$scope.start -= 15;
		$scope.update();
	};

	$scope.last = function() {
		$http.get('services/rest/spruch/count')
		.success(function(data) {
			$scope.start = Math.floor(data / 15) * 15;
			$scope.update();
		});
		
		$scope.update();
	};

	// } ]
});