describe('book controller', function () {
    'use strict';

    beforeEach(function () {
        module('app.main');
        module('flash');
        module('app.books');
    });

    var $scope;
    beforeEach(inject(function ($rootScope) {
        $scope = $rootScope.$new();
    }));

    it('search is defined', inject(function ($controller) {
        // when
        $controller('BookSearchController', {$scope: $scope});
        // then
        expect($scope.search).toBeDefined();
    }));

    it('delete book should call bookService.deleteBook', inject(function ($controller, $q, bookService, Flash) {
        // given
        $controller('BookSearchController', {$scope: $scope});

        var bookId = 1;
        $scope.books = [{id: bookId, title: 'test'}];
        var deleteDeferred = $q.defer();
        spyOn(bookService, 'deleteBook').and.returnValue(deleteDeferred.promise);
        spyOn(Flash, 'create');
        // when
        $scope.deleteBook(bookId);
        deleteDeferred.resolve();
        $scope.$digest();
        // then
        expect(bookService.deleteBook).toHaveBeenCalledWith(bookId);
        expect(Flash.create).toHaveBeenCalledWith('success', 'Książka została usunięta.', 'custom-class');
        expect($scope.books.length).toBe(0);
    }));
//    it('search book should call bookService.search', inject(function ($controller, $q, bookService) {
//    	// given
//    	$controller('BookSearchController', {$scope: $scope});
//    	//angular.copy(response.data, $scope.books);
//    	var prefix = 'p';
//    	$scope.prefix=prefix;
////    	$scope.books = [{id: bookId, title: 'test'}];
//    	$scope.booksResult = [{id: '1', title: 'ptest'},{id: '2', title: 'ptest2'}];
//    	var searchDeferred = $q.defer();
//    	spyOn(bookService, 'search').and.returnValue(searchDeferred.promise);
//    	spyOn(angular, 'copy').and.callThrough();
//    	//.and.returnValue($scope.booksResult);
////    	spyOn(Flash, 'create');
//    	// when
//    	$scope.search();
//    	searchDeferred.resolve();
//    	$scope.$digest();
//    	// then
//    	expect(bookService.search).toHaveBeenCalledWith(prefix);
////    	expect(Flash.create).toHaveBeenCalledWith('success', 'Książka została usunięta.', 'custom-class');
//    	expect($scope.books.length).toBe(1);
//    }));
});
