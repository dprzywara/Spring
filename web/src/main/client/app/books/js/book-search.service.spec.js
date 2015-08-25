describe('book search service', function () {
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
    
    it('search is defined', inject(function (bookService) {
        // then
        expect(bookService.search).toBeDefined();
    }));
    it('deleteBook is defined', inject(function (bookService) {
    	// then
    	expect(bookService.deleteBook).toBeDefined();
    }));
    it('save is defined', inject(function (bookService) {
    	// then
    	expect(bookService.save).toBeDefined();
    }));
    
    
    it('should call bookRestService.search', inject(function (bookService,bookRestService) {
    	// given
    	var titlePrefix='t';
    	spyOn(bookRestService, 'search').and.returnValue({then: angular.noop});
    	// when
    	bookService.search(titlePrefix);
    	// then
    	expect(bookRestService.search).toHaveBeenCalledWith(titlePrefix);
    } ));
    
    it('should call bookRestService.deleteBook', inject(function (bookService,bookRestService) {
    	// given
    	var bookId=11;
    	spyOn(bookRestService, 'deleteBook').and.returnValue({then: angular.noop});
    	// when
    	bookService.deleteBook(bookId);
    	// then
    	expect(bookRestService.deleteBook).toHaveBeenCalledWith(bookId);
    } ));
    
    it('should call bookRestService.save', inject(function (bookService,bookRestService) {
    	// given
    	var book ={id:'1',title:'tit'};
    	spyOn(bookRestService, 'save').and.returnValue({then: angular.noop});
    	// when
    	bookService.save(book);
    	// then
    	expect(bookRestService.save).toHaveBeenCalledWith(book);
    } ));
    
    
});