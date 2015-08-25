angular.module('app.books').controller('BookModalController',function($scope, $modalInstance, Flash) {
'use strict';

					$scope.author = {
						firstName : undefined,
						lastName : undefined
					};
			

					$scope.ok = function() {
						if($scope.AddAuthorForm.$invalid===true){
							Flash.create('danger','imie i nazwisko jest wymagane','custom-class');
						}
						
						if ($scope.author.firstName.length > 50 || $scope.author.lastName.length > 50) {
							Flash.create('danger','imie i nazwisko nie moze być dłuższe niż 50 znaków ','custom-class');
						} else {
							$modalInstance.close($scope.author);
						}
					};

					$scope.cancel = function() {
						$modalInstance.dismiss('cancel');
					};

				});