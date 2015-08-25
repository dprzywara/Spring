describe('book-AddAuthorModal controller', function() {
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

	
    it('ok is defined', inject(function ($controller) {
        // when
        $controller('BookAddAuthorModalController', {$scope: $scope});
        // then
        expect($scope.ok).toBeDefined();
    }));
	
	it('should call close with scope.author in ok function', inject(function($controller) {
		// given
		$controller('BookAddAuthorModalController', {
			$scope : $scope
		});
		$scope.AddAuthorForm = {
				  $valid: true
				};
		$scope.author={firstName:'imie',lastName:'nazwisko'};
		// when
		$scope.ok();
		// then
	    expect($scope.ok).toBeDefined();
		expect(modalInstance.close).toHaveBeenCalledWith($scope.author);
	}));
	
	it('should return danger flash for to long input data in ok function', inject(function($controller,Flash) {
		// given
		$controller('BookAddAuthorModalController', {
			$scope : $scope
		});
		
		$scope.AddAuthorForm = {
				  $valid: true
				};
		$scope.author={firstName:'imiejdksmapwksnvofsnfsbhyawekvbahkbgkahdfbajkdbfkdsbhfasdhfasd',lastName:'nazwisko'};
		spyOn(Flash, 'create');
		// when
		$scope.ok();
		// then
	    
	    expect(Flash.create).toHaveBeenCalledWith('danger','Imie i Nazwisko nie moze być dłuższe niż 50 znaków ', 'custom-class');
		expect(modalInstance.close).not.toHaveBeenCalledWith($scope.author);
	}));
	it('should return danger flash for no firstName filled', inject(function($controller,Flash) {
		// given
		$controller('BookAddAuthorModalController', {
			$scope : $scope
		});
		
		$scope.AddAuthorForm = {
				$valid: false
		};
		$scope.author={firstName:undefined,lastName:'nazwisko'};
		spyOn(Flash, 'create');
		// when
		$scope.ok();
		// then
		
		expect(Flash.create).toHaveBeenCalledWith('danger','Imie jest wymagane', 'custom-class');
		expect(modalInstance.close).not.toHaveBeenCalledWith($scope.author);
	}));
	it('should return danger flash for no lastName filled', inject(function($controller,Flash) {
		// given
		$controller('BookAddAuthorModalController', {
			$scope : $scope
		});
		
		$scope.AddAuthorForm = {
				$valid: false
		};
		$scope.author={firstName:'fksm',lastName:undefined};
		spyOn(Flash, 'create');
		// when
		$scope.ok();
		// then
		
		expect(Flash.create).toHaveBeenCalledWith('danger','Nazwisko jest wymagane', 'custom-class');
		expect(modalInstance.close).not.toHaveBeenCalledWith($scope.author);
	}));
	
	it('should return danger flash for no first and lastName filled', inject(function($controller,Flash) {
		// given
		$controller('BookAddAuthorModalController', {
			$scope : $scope
		});
		
		$scope.AddAuthorForm = {
				$valid: false
		};
		$scope.author={firstName:undefined,lastName:undefined};
		spyOn(Flash, 'create');
		// when
		$scope.ok();
		// then
		
		expect(Flash.create).toHaveBeenCalledWith('danger','Imie i Nazwisko sa wymagane', 'custom-class');
		expect(modalInstance.close).not.toHaveBeenCalledWith($scope.author);
	}));
	
	
    it('cancel is defined', inject(function ($controller) {
        // when
        $controller('BookAddAuthorModalController', {$scope: $scope});
        // then
        expect($scope.cancel).toBeDefined();
    }));
	
	it('should call dismiss with cancel argument ', inject(function($controller) {
		// given
		$controller('BookAddAuthorModalController', {
			$scope : $scope
		});
		// when
		$scope.cancel();
		// then
		
		expect(modalInstance.dismiss).toHaveBeenCalledWith('cancel');
	}));
	
	
	
    
});