describe('Authors rest service', function () {
    'use strict';

    beforeEach(function () {
        module('app.main');
        module('flash');
        module('app.authors');
    });
    
    var $scope;
    beforeEach(inject(function ($rootScope) {
        $scope = $rootScope.$new();
    }));
    
    var httpBackend;
    
    beforeEach(inject(function ($httpBackend) {
        httpBackend = $httpBackend;
    }));
    
    it('get is defined', inject(function (authorsRestService) {
        // then
        expect(authorsRestService.get).toBeDefined();
    }));
    
    
    it('should get all authors', inject(function (authorsRestService) {
    	// given
        var url = '/context.html/rest/authors/authors';
        var httpResponse = [{firstName: 'first1', lastName: 'last1'}, {firstName: 'first2', lastName: 'last2'}];
        httpBackend.expectGET(url).respond(200, httpResponse);
        // when
        var promise=authorsRestService.get().then(function(response) {
                    expect(response.status).toEqual(200);
                    expect(response.data).toEqual(httpResponse);
        });
        httpBackend.flush();
        // then
        expect(promise.then).toBeDefined();
    } ));
    
});
