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
    
    
    it('addAuthor is defined', inject(function ($controller) {
        // when
        $controller('BookAddController', {$scope: $scope});
        // then
        expect($scope.addAuthor).toBeDefined();
    }));
    
    
    
    it('savebook is defined', inject(function ($controller) {
    	// when
    	$controller('BookAddController', {$scope: $scope});
    	// then
    	expect($scope.saveBook).toBeDefined();
    }));
    
    
    it('deleteAuthor is defined', inject(function ($controller) {
    	// when
    	$controller('BookAddController', {$scope: $scope});
    	// then
    	expect($scope.deleteAuthor).toBeDefined();
    }));
    
    
    
    it('save book should call bookService.newBook', inject(function ($controller, $q, bookService, Flash) {
        // given
        $controller('BookAddController', {$scope: $scope});
        var entryAuthors= [{firstName: 'first', lastName: 'last'}];
        $scope.book={ title: 'test',authors : entryAuthors};
        
        var saveBookDeferred = $q.defer();
        spyOn(bookService, 'newBook').and.returnValue(saveBookDeferred.promise);
        spyOn(Flash, 'create');
        // when
        $scope.saveBook($scope.book);
        saveBookDeferred.resolve();
        $scope.$digest();
        // then
        expect(bookService.newBook).toHaveBeenCalledWith($scope.book);
        expect(Flash.create).toHaveBeenCalledWith('success', 'Książka została dodana.', 'custom-class');
        expect($scope.book.title).toBe('test');
    }));
    
    
    it('delete author should delete author from book list', inject(function ($controller) {
    	// given
    	$controller('BookAddController', {$scope: $scope});
    	var author={firstName: 'first', lastName: 'last'};
    	var entryAuthors= [author,{firstName: 'first2', lastName: 'last2'}];
    	
    	$scope.book={ title: 'test',authors : entryAuthors};
    	
    	//spyOn('BookAddController', 'deleteAuthor').and.returnValue(saveBookDeferred.promise);
    	// when
    	$scope.deleteAuthor(author);
    	$scope.$digest();
    	// then
    	//expect($scope.deleteAuthor).toHaveBeenCalledWith(author); //pytanie
    	expect($scope.book.authors.length).toBe(1);
    	expect($scope.book.authors[0].firstName).toBe('first2');
    }));
    
    
    
    
    
    
});
