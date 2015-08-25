describe('book-modal controller', function() {
	'use strict';
	
	var modalInstance;
	beforeEach(function() {
		module('app.main');
		module('flash');
		modalInstance = {
			close : jasmine.createSpy('modalInstance.close'),
			dismiss : jasmine.createSpy('modalInstance.dismiss')
		};

		module('app.books', function($provide) {
			$provide.value('$modalInstance', modalInstance);
		});

	});
	
	var $scope;
	beforeEach(inject(function($rootScope) {
		$scope = $rootScope.$new();
	}));

	
	
	
	it('should call close with scope.author in ok function', inject(function($controller) {
		// given
		$controller('BookModalController', {
			$scope : $scope
		});
		//$scope.form.AddAuthorForm=false;
		$scope.AddAuthorForm = {
				  $invalid: false
				};
		$scope.author={firstName:'imie',lastName:'nazwisko'};
		// when
		$scope.ok();
		// then
	    expect($scope.ok).toBeDefined();
		expect(modalInstance.close).toHaveBeenCalledWith($scope.author);
	}));
	
	
	it('should call dismiss with cancel argument ', inject(function($controller) {
		// given
		$controller('BookModalController', {
			$scope : $scope
		});
		// when
		$scope.cancel();
		// then
		expect($scope.cancel).toBeDefined();
		expect(modalInstance.dismiss).toHaveBeenCalledWith('cancel');
	}));
	
	
	it('should return warning allert cause of to long input data in ok function', inject(function($controller,Flash) {
		// given
		$controller('BookModalController', {
			$scope : $scope
		});
		
		$scope.AddAuthorForm = {
				  $invalid: false
				};
		$scope.author={firstName:'imiejdksmapwksnvofsnfsbhyawekvbahkbgkahdfbajkdbfkdsbhfasdhfasd',lastName:'nazwisko'};
		spyOn(Flash, 'create');
		// when
		$scope.ok();
		// then
	    expect($scope.ok).toBeDefined();
	    expect(Flash.create).toHaveBeenCalledWith('danger','imie i nazwisko nie moze być dłuższe niż 50 znaków ', 'custom-class');
		expect(modalInstance.close).not.toHaveBeenCalledWith($scope.author);
	}));
	
	
    
});