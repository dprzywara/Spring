angular.module('app.books').controller('BookEditModalController', function ($scope,$modalInstance) {
    'use strict';

    $scope.ok = function () {
      $modalInstance.close($scope.title);
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
    
    
 
    
    
    
    
    
    
    
});