angular.module('app.books').controller('BookAddController', function ($scope, $window, $location, bookService, $modal,Flash) {
    'use strict';

   $scope.book={title:undefined,authors:[]};
 
    $scope.addAuthor = function () {
    	//var modalInstance = 
    		$modal.open({
            templateUrl: 'books/html/book-modal.html',
            controller: 'BookModalController',
            size: 'sm'

//            resolve: {
//              items: function () {
//                return $scope.items;
//              }
            
            
//            modalInstance.result.then(function (author) {
//          	  Flash.create('success', 'Author dodany.', 'custom-class');
////              $scope.book.authors.push(author);
//            }, function () {
//              $log.info('Modal dismissed at: ' + new Date());
//            });
//            }
          });
    };

         
    
   
    $scope.saveBook = function () {

    	 bookService.newBook($scope.book).then(function () {
             Flash.create('success', 'Książka została dodana.', 'custom-class');
             $location.url('/books/book-list');
         }, function () {
             Flash.create('danger', 'Wyjątek', 'custom-class');
         });
    	
    };
   
    
});