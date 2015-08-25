angular.module('app.books').controller('BookAddAuthorModalController',function($scope, $modalInstance, Flash) {
'use strict';

					$scope.author = {
						firstName : undefined,
						lastName : undefined
					};
			

					$scope.ok = function() {
						if($scope.AddAuthorForm.$valid===true ){
							if($scope.author.firstName.length < 50 && $scope.author.lastName.length < 50) {
							$modalInstance.close($scope.author);}
						else{
							Flash.create('danger','Imie i Nazwisko nie moze być dłuższe niż 50 znaków ','custom-class');
								}
						}
						else{
							
							if($scope.author.lastName===undefined && $scope.author.firstName===undefined){
								Flash.create('danger','Imie i Nazwisko sa wymagane','custom-class');
							}
							else{
								if ($scope.author.firstName===undefined ){
									Flash.create('danger','Imie jest wymagane','custom-class');
								}
								else {
									Flash.create('danger','Nazwisko jest wymagane','custom-class');
								}
							}

						}
						
						
					};

					$scope.cancel = function() {
						$modalInstance.dismiss('cancel');
					};

				});