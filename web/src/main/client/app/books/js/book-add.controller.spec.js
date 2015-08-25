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
    
    
    var fakeModal = {
    	    result: {
    	        then: function (confirmCallback, cancelCallback) {
    	            this.confirmCallBack = confirmCallback;
    	            this.cancelCallback = cancelCallback;
    	            return this;
    	        },
    	    },
    	    close: function (item) {
    	        this.result.confirmCallBack(item);
    	    },
    	    dismiss: function (item) {
    	        this.result.cancelCallback(item);
    	    }
    	};
    
    
    it('addAuthor is defined', inject(function ($controller) {
        // when
        $controller('BookAddController', {$scope: $scope});
        // then
        expect($scope.addAuthor).toBeDefined();
    }));
    
    it('addAuthor should add author to the Authorslist', inject(function ($controller, $q, bookService, Flash,$modal) {
        // given
        $controller('BookAddController', {$scope: $scope});
        $scope.book= {id: '1', title: 'ptest',authors:[{firstName: 'firstName0', lastName: 'lastName0'}]};
        var author={firstName: 'firstName1', lastName: 'lastName1'};
        spyOn($modal, 'open').and.returnValue(fakeModal); 
        spyOn(Flash, 'create');
        // when
        $scope.addAuthor();
        fakeModal.close(author);
        $scope.$digest();
        // then
        expect(Flash.create).toHaveBeenCalledWith('success',  'Autor dodany.', 'custom-class');
        expect($scope.book.authors.length).toBe(2);
        expect($scope.book.authors[1].lastName).toBe('lastName1');
    }));
    
    it('addAuthor should return flash danger for dismiss modal', inject(function ($controller, $q, bookService, Flash,$modal) {
    	// given
    	$controller('BookAddController', {$scope: $scope});
    	$scope.book= {id: '1', title: 'ptest',authors:[{firstName: 'firstName0', lastName: 'lastName0'}]};
    	spyOn($modal, 'open').and.returnValue(fakeModal); 
    	spyOn(Flash, 'create');
    	// when
    	$scope.addAuthor();
    	fakeModal.dismiss('close');
    	$scope.$digest();
    	// then
    	expect(Flash.create).toHaveBeenCalledWith('danger', 'Wyjatek - dodawanie autora', 'custom-class');
    }));
    
    
    
    
    it('savebook is defined', inject(function ($controller) {
    	// when
    	$controller('BookAddController', {$scope: $scope});
    	// then
    	expect($scope.saveBook).toBeDefined();
    }));
  
    it('save book should return flash with warning to fill title', inject(function ($controller, $q, bookService, Flash) {
    	// given
    	$controller('BookAddController', {$scope: $scope});
    	var entryAuthors= [{firstName: 'first', lastName: 'last'}];
    	$scope.book={ title: undefined,authors : entryAuthors};
    	$scope.AddBookForm = {
    			$valid: false
    	};
    	var saveBookDeferred = $q.defer();
    	spyOn(bookService, 'save').and.returnValue(saveBookDeferred.promise);
    	spyOn(Flash, 'create');
    	// when
    	$scope.saveBook($scope.book);
    	saveBookDeferred.resolve();
    	$scope.$digest();
    	// then
    	expect(bookService.save).not.toHaveBeenCalled();
    	expect(Flash.create).toHaveBeenCalledWith('danger', 'Książka musi mieć tytuł.', 'custom-class');
    }));
    it('save book should return danger flash  to add authors', inject(function ($controller, $q, bookService, Flash) {
    	// given
    	$controller('BookAddController', {$scope: $scope});
    	$scope.book={ title: 'tere',authors : []};
    	$scope.AddBookForm = {
    			$valid: true
    	};
    	var saveBookDeferred = $q.defer();
    	spyOn(bookService, 'save').and.returnValue(saveBookDeferred.promise);
    	spyOn(Flash, 'create');
    	// when
    	$scope.saveBook($scope.book);
    	saveBookDeferred.resolve();
    	$scope.$digest();
    	// then
    	expect(bookService.save).not.toHaveBeenCalledWith($scope.book);
    	expect(Flash.create).toHaveBeenCalledWith('danger', 'Książka musi mieć co najmniej jednego autora.', 'custom-class');
    }));
    
    it('save book should call bookService.save', inject(function ($controller, $q, bookService, Flash) {
        // given
        $controller('BookAddController', {$scope: $scope});
        var entryAuthors= [{firstName: 'first', lastName: 'last'}];
        $scope.book={ title: 'test',authors : entryAuthors};
        $scope.AddBookForm = {
				  $valid: true
				};
        var saveBookDeferred = $q.defer();
        spyOn(bookService, 'save').and.returnValue(saveBookDeferred.promise);
        spyOn(Flash, 'create');
        // when
        $scope.saveBook($scope.book);
        saveBookDeferred.resolve();
        $scope.$digest();
        // then
        expect(bookService.save).toHaveBeenCalledWith($scope.book);
        expect(Flash.create).toHaveBeenCalledWith('success', 'Książka została dodana.', 'custom-class');
        expect($scope.book.title).toBe('test');
    }));
    it('save book should call bookService.save and return danger allert for promise reject', inject(function ($controller, $q, bookService, Flash) {
    	// given
    	$controller('BookAddController', {$scope: $scope});
    	var entryAuthors= [{firstName: 'first', lastName: 'last'}];
    	$scope.book={ title: 'test',authors : entryAuthors};
    	$scope.AddBookForm = {
    			$valid: true
    	};
    	var saveBookDeferred = $q.defer();
    	spyOn(bookService, 'save').and.returnValue(saveBookDeferred.promise);
    	spyOn(Flash, 'create');
    	// when
    	$scope.saveBook($scope.book);
    	saveBookDeferred.reject();
    	$scope.$digest();
    	// then
    	expect(bookService.save).toHaveBeenCalledWith($scope.book);
    	expect(Flash.create).toHaveBeenCalledWith('danger', 'Wyjątek-dodawanie ksiazki', 'custom-class');
    }));
    
    
    
    it('deleteAuthor is defined', inject(function ($controller) {
    	// when
    	$controller('BookAddController', {$scope: $scope});
    	// then
    	expect($scope.deleteAuthor).toBeDefined();
    }));
    
 
    it('delete author should delete author from book list', inject(function ($controller) {
    	// given
    	$controller('BookAddController', {$scope: $scope});
    	var author={firstName: 'first', lastName: 'last'};
    	var entryAuthors= [author,{firstName: 'first2', lastName: 'last2'}];
    	$scope.book={ title: 'test',authors : entryAuthors};
    	
    	// when
    	$scope.deleteAuthor(author);
    	$scope.$digest();
    	// then
    	expect($scope.book.authors.length).toBe(1);
    	expect($scope.book.authors[0].firstName).toBe('first2');
    }));
    
    
    
   
    
    
    
});
