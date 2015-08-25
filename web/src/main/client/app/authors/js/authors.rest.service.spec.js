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
    
    
    
    it('should get all authors', inject(function (authorsRestService) {
    	// given
        var url = '/context.html/rest/authors/authors';
        var httpResponse = [{firstName: 'first1', lastName: 'last1'}, {firstName: 'first2', lastName: 'last2'}];
        httpBackend.expectGET(url).respond(200, httpResponse);
        // when
        var promise=authorsRestService.get();
        httpBackend.flush();
        // then
        //expect($scope.authors.length).toBe(2);
        expect(promise.then).toBeDefined();
    } ));
    
});
