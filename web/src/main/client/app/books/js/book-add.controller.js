angular.module('app.books').controller(
		'BookAddController',
		function($scope, $window, $location, bookService, $modal, Flash, $log) {
			'use strict';

			$scope.book = {
				title : undefined,
				authors : []
			};
			

			$scope.addAuthor = function() {
				var modalInstance = $modal.open({
					templateUrl : 'books/html/book-modal.html',
					controller : 'BookModalController',
					size : 'sm'

				});

				modalInstance.result.then(function(author) {
					$scope.book.authors.push(author);
					Flash.create('success', 'Autor dodany.', 'custom-class');
				}, function() {
					$log.info('Modal dismissed at: ' + new Date());
					Flash.create('danger', 'Wyjatek - dodawanie autora',
					'custom-class');
				});

			};

			$scope.saveBook = function(book) {
				
				if($scope.AddBookForm.$invalid===true){
					if($scope.book.title===''){
						Flash.create('danger', 'Książka musi mieć tytuł.',
						'custom-class');	
					}
					if($scope.book.authors.lenth===0){
					Flash.create('danger', 'Książka musi mieć co najmniej jednego autora.',
					'custom-class');
					}
				}
				else{
					
				
				bookService.newBook(book).then(
						function() {
							Flash.create('success', 'Książka została dodana.',
									'custom-class');
							$location.url('/books/book-list');
						}, function() {
							Flash.create('danger', 'Wyjątek-dodawanie ksiazki', 'custom-class');
						});
				}
			};
			
			$scope.deleteAuthor = function (author) {
		        for (var i = 0; i < $scope.book.authors.length; i = i + 1) {
		            if ($scope.book.authors[i] === author) {
		                $scope.book.authors.splice(i, 1);
		                break;
		            }
		        }
		    };
			

		});