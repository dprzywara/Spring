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
    
    it('deleteBook is defined', inject(function ($controller) {
    	// when
    	$controller('BookSearchController', {$scope: $scope});
    	// then
    	expect($scope.deleteBook).toBeDefined();
    }));
    it('addBook is defined', inject(function ($controller) {
    	// when
    	$controller('BookSearchController', {$scope: $scope});
    	// then
    	expect($scope.addBook).toBeDefined();
    }));
    
    

    it('editModal is defined', inject(function ($controller) {
        // when
        $controller('BookSearchController', {$scope: $scope});
        // then
        expect($scope.editModal).toBeDefined();
    }));
   
    
    it('search book should call bookService.search', inject(function ($controller, $q, bookService) {
    	// given
    	var searchDeferred = $q.defer();
    	var searchSpyCount = 0;
    	spyOn(bookService, 'search').and.callFake(function () {
    		searchSpyCount = searchSpyCount + 1;
    		if (searchSpyCount === 1) {
    			return {then: angular.noop};
    		}
    		else if (searchSpyCount === 2) {
    			return searchDeferred.promise;
    		}
    	});
    	
    	$controller('BookSearchController', {$scope: $scope});
    	
    	var prefix = 'p';
    	$scope.prefix=prefix;
    	var booksResult = [{id: '1', title: 'ptest'},{id: '2', title: 'ptest2'}];
    	// when
    	$scope.search();
    	searchDeferred.resolve({data: booksResult});
    	$scope.$digest();
    	// then
    	expect(bookService.search).toHaveBeenCalledWith(prefix);
    	expect($scope.books.length).toBe(2);
    	expect(bookService.search).toHaveBeenCalledWith(prefix);
    	expect($scope.books[0].id).toBe('1');
    	expect($scope.books[1].title).toBe('ptest2');
    }));
    
    it('search book should return danger flash for reject in promise', inject(function ($controller, $q, bookService,Flash) {
    	// given
    	var searchDeferred = $q.defer();
    	var searchSpyCount = 0;
    	spyOn(bookService, 'search').and.callFake(function () {
    		searchSpyCount = searchSpyCount + 1;
    		if (searchSpyCount === 1) {
    			return {then: angular.noop};
    		}
    		else if (searchSpyCount === 2) {
    			return searchDeferred.promise;
    		}
    	});
    	
    	 spyOn(Flash, 'create');
    	$controller('BookSearchController', {$scope: $scope});
    	
    	var prefix = 'p';
    	$scope.prefix=prefix;
    	
    	// when
    	$scope.search();
    	searchDeferred.reject();
    	$scope.$digest();
    	// then
    	expect(bookService.search).toHaveBeenCalledWith(prefix);
    	 expect(Flash.create).toHaveBeenCalledWith('danger', 'Wyjątek szukanie', 'custom-class');
         
    }));

    
    it('delete book should call bookService.deleteBook', inject(function ($controller, $q, bookService, Flash) {
        // given
    	spyOn(bookService, 'search').and.returnValue({then: angular.noop});
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
    
    
    it('editModal should edit title in book', inject(function ($controller, $q, bookService, Flash,$modal) {
        // given
    	spyOn(bookService, 'search').and.returnValue({then: angular.noop});
        $controller('BookSearchController', {$scope: $scope});
        var book= {id: '1', title: 'ptest'};
        var newTitle='new';
        var saveBookDeferred = $q.defer();
        var ModalDeferred = $q.defer();
        spyOn(bookService, 'save').and.returnValue(saveBookDeferred.promise);
        spyOn($modal, 'open').and.returnValue({result:ModalDeferred.promise}); 
        spyOn(Flash, 'create');
        // when
        $scope.editModal(book);
        ModalDeferred.resolve(newTitle);
        saveBookDeferred.resolve();
        $scope.$digest();
        // then
        expect(bookService.save).toHaveBeenCalledWith(book);
        expect(Flash.create).toHaveBeenCalledWith('success', 'Książka została edytowana.', 'custom-class');
        expect(book.title).toBe('new');
    }));
    
    

    
    it('editModal should show flash for promise reject', inject(function ($controller, $q, bookService, Flash,$modal) {
    	// given
    	spyOn(bookService, 'search').and.returnValue({then: angular.noop});
    	$controller('BookSearchController', {$scope: $scope});
    	var book= {id: '1', title: 'ptest'};
    	var newTitle='new';
    	var saveBookDeferred = $q.defer();
    	var ModalDeferred = $q.defer();
    	spyOn(bookService, 'save').and.returnValue(saveBookDeferred.promise);
    	spyOn($modal, 'open').and.returnValue({result:ModalDeferred.promise}); 
    	spyOn(Flash, 'create');
    	// when
    	$scope.editModal(book);
    	ModalDeferred.resolve(newTitle);
    	saveBookDeferred.reject();
    	$scope.$digest();
    	// then
    	expect(bookService.save).toHaveBeenCalledWith(book);
    	expect(Flash.create).toHaveBeenCalledWith('danger', 'Wyjątek edycja', 'custom-class');
    }));
    
    it('editModal should show danger flash for modal dismiss', inject(function ($controller, $q, bookService, Flash,$modal) {
    	// given
    	spyOn(bookService, 'search').and.returnValue({then: angular.noop});
    	$controller('BookSearchController', {$scope: $scope});
    	var book= {id: '1', title: 'ptest'};
    	var saveBookDeferred = $q.defer();
    	var ModalDeferred = $q.defer();
    	spyOn(bookService, 'save').and.returnValue(saveBookDeferred.promise);
    	spyOn($modal, 'open').and.returnValue({result:ModalDeferred.promise}); 
    	spyOn(Flash, 'create');
    	// when
    	$scope.editModal(book);
    	ModalDeferred.reject();
    	saveBookDeferred.resolve();
    	$scope.$digest();
    	// then
    	expect(bookService.save).not.toHaveBeenCalledWith(book);
    	expect(Flash.create).toHaveBeenCalledWith('danger', 'Modal dismissed', 'custom-class');
    }));

    
    it('addBook should call $location.url', inject(function ($controller, $location) {
    	// given   	
    	$controller('BookSearchController', {$scope: $scope});
    	spyOn($location, 'url');
    	// when
    	$scope.addBook();
    	// then
    	expect($location.url).toHaveBeenCalledWith('/books/add-book');
    }));

    
    
    
});
