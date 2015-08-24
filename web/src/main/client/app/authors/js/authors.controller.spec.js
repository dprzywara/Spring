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
    
    
    //zeby dzialal test get w controlerze musi byc zakomentowane
    it('get should call bookService.get', inject(function ($controller, $q, authorsService) {
        // given
        $controller('AuthorsController', {$scope: $scope});

        var authorsResult = [{firstName: 'name1', lastname: 'aaa'},{firstName: 'name2', lastName: 'aaa2'}];
        var getDeferred = $q.defer();
        spyOn(authorsService, 'get').and.returnValue(getDeferred.promise);
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
    
    
    
    it('get should return Exception from bookService.get', inject(function ($controller, $q, authorsService,Flash) {
    	// given
    	$controller('AuthorsController', {$scope: $scope});
    	
    	var getDeferred = $q.defer();
    	spyOn(authorsService, 'get').and.returnValue(getDeferred.promise);
    	spyOn(Flash, 'create');
    	// when
    	$scope.get();
    	getDeferred.reject();
    	$scope.$digest();
    	// then
    	 expect(Flash.create).toHaveBeenCalledWith('danger', 'Wyjątek', 'custom-class');
    }));



    it('startWith should return true', inject(function ($controller) {
    	// given
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