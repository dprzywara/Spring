angular.module('app.books').controller('BookEditModalController', function ($scope,$modalInstance,Flash) {
    'use strict';

    $scope.ok = function () {
    	if($scope.EditForm.$valid===true ){
    		$modalInstance.close($scope.title);
			}
		else{
			Flash.create('danger','Tytul jest wymagany','custom-class');
				}
      
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
    
    
 
    
    
    
    
    
    
    
});