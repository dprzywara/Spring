describe('author service', function () {
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
    
    it('get is defined', inject(function (authorsService) {
        // then
        expect(authorsService.get).toBeDefined();
    }));
    
    
    it('should call authorsService.get', inject(function (authorsService,authorsRestService) {
    	// given
    	spyOn(authorsRestService, 'get').and.returnValue({then: angular.noop});
    	// when
    	authorsService.get();
    	// then
    	expect(authorsRestService.get).toHaveBeenCalled();
    } ));
    
    
    
});
