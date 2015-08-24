describe('book controller', function () {
    'use strict';

    beforeEach(function () {
        module('app.main');
        module('flash');
        module('app.books');
    });
    
    
    
//    var fakeModal = {
//    	    result: {
//    	        then: function (confirmCallback, cancelCallback) {
//    	            this.confirmCallBack = confirmCallback;
//    	            this.cancelCallback = cancelCallback;
//    	            return this;
//    	        },
//    	        catch: function (cancelCallback) {
//    	            this.cancelCallback = cancelCallback;
//    	            return this;
//    	        },
//    	        finally: function (finallyCallback) {
//    	            this.finallyCallback = finallyCallback;
//    	            return this;
//    	        }
//    	    },
//    	    close: function (item) {
//    	        this.result.confirmCallBack(item);
//    	    },
//    	    dismiss: function (item) {
//    	        this.result.cancelCallback(item);
//    	    },
//    	    finally: function () {
//    	        this.result.finallyCallback();
//    	    }
//    	};

    var $scope;
    beforeEach(inject(function ($rootScope) {
        $scope = $rootScope.$new();
        //fakeModal = new FakeModal();
    }));

    it('search is defined', inject(function ($controller) {
        // when
        $controller('BookSearchController', {$scope: $scope});
        // then
        expect($scope.search).toBeDefined();
    }));

    it('editModal is defined', inject(function ($controller) {
        // when
        $controller('BookSearchController', {$scope: $scope});
        // then
        expect($scope.editModal).toBeDefined();
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
//    	var searchResult = {result: {}};
//    	
//    	searchResult.result.then = angular.noop;
//    	spyOn(bookService, 'search').and.returnValue(searchResult.result);
//    	
//    	$controller('BookSearchController', {$scope: $scope});
//    	
//    	var prefix = 'p';
//    	//var booksResult = [{id: '1', title: 'ptest'},{id: '2', title: 'ptest2'}];
//    	var searchDeferred = $q.defer();
//    	
//    	$scope.prefix=prefix;
//    	searchResult.result = searchDeferred.promise;
//
//    	// when
//    	$scope.search();
//    	searchDeferred.resolve({data: [{id: '1', title: 'ptest'},{id: '2', title: 'ptest2'}]});
//    	$scope.$digest();
//    	// then
//    	expect(bookService.search).toHaveBeenCalledWith(prefix);
//    	expect($scope.books.length).toBe(2);
//    }));
    it('search book should call bookService.search', inject(function ($controller, $q, bookService) {
    	 // given
    	//spyOn(bookService, 'search').and.returnValue({then: angular.noop});
        $controller('BookSearchController', {$scope: $scope});

        var prefix = 'p';
        $scope.prefix=prefix;
        var booksResult = [{id: '1', title: 'ptest'},{id: '2', title: 'ptest2'}];
        var searchDeferred = $q.defer();
        spyOn(bookService, 'search').and.returnValue(searchDeferred.promise);
        
        // when
    	$scope.search();
    	searchDeferred.resolve({data: booksResult});
//    	searchDeferred.resolve({data: [{id: '1', title: 'ptest'},{id: '2', title: 'ptest2'}]});
    	$scope.$digest();
        // then
    	expect(bookService.search).toHaveBeenCalledWith(prefix);
    	expect($scope.books.length).toBe(2);
    	expect($scope.books[0].id).toBe('1');
    	expect($scope.books[1].title).toBe('ptest2');
    	
    }));
    
    
//    it('editModal should edit title in book', inject(function ($controller, $q, bookService, Flash,$modal) {
//        // given
//        $controller('BookSearchController', {$scope: $scope,$modal: fakeModal});
//        
//        var book= {id: '1', title: 'ptest'};
//        //$scope.book={ title: 'test',authors : entryAuthors};
//        
//        var editDeferred = $q.defer();
//        spyOn('BookSearchController', 'editModal').and.returnValue(editDeferred.promise);
//        spyOn($modal, 'open').and.returnValue(fakeModal); 
//        spyOn(Flash, 'create');
//        // when
//        $scope.editModal(book);
//       // $scope.open(); // Open the modal
//        $scope.modalInstance.close('item1');
//        //expect(scope.selected).toEqual('item1');
//        
//        editDeferred.resolve();
//        $scope.$digest();
//        // then
//        expect(bookService.newBook).toHaveBeenCalledWith(book);
//        expect(Flash.create).toHaveBeenCalledWith('success', 'Książka została edytowana.', 'custom-class');
//        expect(book.title).toBe('item1');
//    }));
//    
//    it('should cancel the dialog when dismiss is called, and  $scope.canceled should be true', function () {
//    	// given
//        $controller('BookSearchController', {$scope: $scope,$modal: fakeModal});
//    	
//    	expect( scope.canceled ).toBeUndefined();
//
//        fakeModal.dismiss( 'cancel' ); //Call dismiss (simulating clicking the cancel button on the modal)
//        expect( scope.canceled ).toBe( true );
//    });
    
    
    
    
});
