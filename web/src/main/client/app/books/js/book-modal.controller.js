angular.module('app.books').controller('BookModalController', function ($scope,$modalInstance) {
    'use strict';

    $scope.author={firstname:undefined,lastname:undefined};


    $scope.ok = function () {
      $modalInstance.close($scope.author);
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
});