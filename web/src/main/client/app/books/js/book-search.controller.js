angular.module('app.books').controller('BookSearchController', function ($scope, $window, $location, bookService, Flash,$modal,$log) {
'use strict';

    $scope.books = [];
    $scope.gridOptions = { data: 'books' };
    $scope.prefix = '';
    

    var removeBookById = function (bookId) {
        for (var i = 0; i < $scope.books.length; i = i + 1) {
            if ($scope.books[i].id === bookId) {
                $scope.books.splice(i, 1);
                break;
            }
        }
    };
    

    $scope.search = function () {
        bookService.search($scope.prefix).then(function (response) {
            angular.copy(response.data, $scope.books);
        }, function () {
            Flash.create('danger', 'Wyjątek szukanie', 'custom-class');
        });
    };

    $scope.deleteBook = function (bookId) {
        bookService.deleteBook(bookId).then(function () {
            removeBookById(bookId);
            Flash.create('success', 'Książka została usunięta.', 'custom-class');
        });
    };
    
    
    $scope.editModal = function(book) {
		var modalInstance = $modal.open({
			templateUrl : 'books/html/edit-modal.html',
			controller : 'BookEditModalController',
			size : 'sm'

		});
		
		modalInstance.result.then(function(title) {
			book.title=title;
			bookService.save(book).then(
					function() {
						Flash.create('success', 'Książka została edytowana.',
								'custom-class');
						$location.url('/books/book-list');
					}, function() {
						Flash.create('danger', 'Wyjątek edycja', 'custom-class');
					});
		}, function() {
			Flash.create('danger', 'Modal dismissed', 'custom-class');
			$log.info('Modal dismissed at: ' + new Date());
		});
		
    };

    $scope.addBook = function () {
        $location.url('/books/add-book');
    };

    $scope.search();


});




