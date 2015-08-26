describe('authors controller', function () {
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
    var getDeferred;
    var init=inject(function($q,authorsService){
    	getDeferred = $q.defer();
    	var getSpyCount = 0;
    	spyOn(authorsService, 'get').and.callFake(function () {
    		getSpyCount = getSpyCount + 1;
    		if (getSpyCount === 1) {
    			return {then: angular.noop};
    		}
    		else if (getSpyCount === 2) {
    			return getDeferred.promise;
    		}
    	});
    });
    
    it('get is defined', inject(function ($controller) {
        // when
        $controller('AuthorsController', {$scope: $scope});
        // then
        expect($scope.get).toBeDefined();
    }));
    
    
    it('startWith is defined', inject(function ($controller) {
        // when
        $controller('AuthorsController', {$scope: $scope});
        // then
        expect($scope.startsWith).toBeDefined();
    }));
    
    
    it('get should call bookService.get', inject(function ($controller, $q, authorsService) {
        // given
    	init();
        $controller('AuthorsController', {$scope: $scope});
        var authorsResult = [{firstName: 'name1', lastname: 'aaa'},{firstName: 'name2', lastName: 'aaa2'}];
        // when
    	$scope.get();
    	getDeferred.resolve({data: authorsResult});
    	$scope.$digest();
        // then
    	expect(authorsService.get).toHaveBeenCalled();
    	expect($scope.authors.length).toBe(2);
    	expect($scope.authors[0].firstName).toBe('name1');
    	expect($scope.authors[1].lastName).toBe('aaa2');
    }));
    
    
    
    it('get should return Flash danger for promise reject in bookService.get', inject(function ($controller, $q, authorsService,Flash) {
    	// given
    	init();
    	$controller('AuthorsController', {$scope: $scope});
    	spyOn(Flash, 'create');
    	// when
    	$scope.get();
    	getDeferred.reject();
    	$scope.$digest();
    	// then
    	 expect(Flash.create).toHaveBeenCalledWith('danger', 'Wyjątek', 'custom-class');
    }));



    it('startWith should return true for firstName', inject(function ($controller,authorsService) {
    	// given
    	spyOn(authorsService, 'get').and.returnValue({then: angular.noop});
    	$controller('AuthorsController', {$scope: $scope});
    	var actual='Pierwszy';
    	var expected='p';
    	// when
    	var result=$scope.startsWith(actual,expected);
    	$scope.$digest();
    	// then
    	expect(result).toEqual(true);
    }));

});