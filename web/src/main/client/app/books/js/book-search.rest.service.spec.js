describe('Books rest service', function () {
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
    
    var httpBackend;
    beforeEach(inject(function ($httpBackend) {
        httpBackend = $httpBackend;
    }));
    
    it('search is defined', inject(function (bookRestService) {
        // then
        expect(bookRestService.search).toBeDefined();
    }));
    
    it('deleteBook is defined', inject(function (bookRestService) {
        // then
        expect(bookRestService.deleteBook).toBeDefined();
    }));
    
    it('save is defined', inject(function (bookRestService) {
        // then
        expect(bookRestService.save).toBeDefined();
    }));
    
    it('should search by title', inject(function (bookRestService) {
    	// given

        var url = '/context.html/rest/books/books-by-title';
        var httpResponse = [{id:'1', title: 'first1'}, {id:'2', title: 'first2'}];
        httpBackend.expectGET(url).respond(200, httpResponse);
        // when
        var promise=bookRestService.search().then(function(response) {
                    expect(response.status).toEqual(200);
                    expect(response.data).toEqual(httpResponse);
        });
        httpBackend.flush();
        // then
        expect(promise.then).toBeDefined();
    } ));
    
    it('should delete book', inject(function (bookRestService) {
    	// given
    	
    	var url = '/context.html/rest/books/book/';
    	var httpResponse = [];
    	httpBackend.expectDELETE(url).respond(200, httpResponse);
    	// when
    	var promise=bookRestService.deleteBook('').then(function(response) {
    		        expect(response.status).toEqual(200);
    		        expect(response.data).toEqual(httpResponse);
    	});
    	httpBackend.flush();
    	// then
    	expect(promise.then).toBeDefined();
    } ));
    
    
    it('should save book', inject(function (bookRestService) {
    	// given

        var url = '/context.html/rest/books/book/';
        var httpResponse = {id:'1', title: 'first1'};
        httpBackend.expectPOST(url).respond(200, httpResponse);
        // when
        var promise=bookRestService.save().then(function(response) {
                    expect(response.status).toEqual(200);
                    expect(response.data).toEqual(httpResponse);
        });
        httpBackend.flush();
        // then
        expect(promise.then).toBeDefined();
    } ));
    
    
});