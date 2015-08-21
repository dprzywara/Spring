angular.module('app.books').controller('BookModalController', function ($scope,$modalInstance,Flash) {
    'use strict';

    $scope.author={firstname:undefined,lastname:undefined};


    $scope.ok = function () {
    	if($scope.author.firstName.length >50 || $scope.author.lastName.length >50  ){
			Flash.create('danger', 'imie i nazwisko nie moze być dłuższe niż 50 znaków ', 'custom-class');
		}else{
      $modalInstance.close($scope.author);
		}
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
  
    	
    
});