angular.module('app.authors').controller('AuthorsController', function ($scope,  authorsService,Flash) {
'use strict';

   $scope.authors=[];
   $scope.prefix='';
   $scope.queryBy='$';
   
   $scope.get = function () {
       authorsService.get().then(function (response) {
           angular.copy(response.data, $scope.authors);
       }, function () {
           Flash.create('danger', 'Wyjątek', 'custom-class');
       });
   };

   $scope.get();
   
  
   $scope.startsWith = function (actual, expected) {
	    var lowerStr = (actual + '').toLowerCase();
	    return lowerStr.indexOf(expected.toLowerCase()) === 0;
	};
   
	
	
	
	
   Object.defineProperty($scope, 'queryFilter', {
	      get: function() {
	          var out = {};
	          out[$scope.queryBy || '$'] = $scope.prefix;
	          return out;
	      }
	  });

});




