describe('editmodal controller', function() {
	'use strict';
	var modalInstance;
	beforeEach(function() {
		module('app.main');
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
        $controller('BookEditModalController', {$scope: $scope});
        // then
        expect($scope.ok).toBeDefined();
    }));
    
    it('cancel is defined', inject(function ($controller) {
        // when
        $controller('BookEditModalController', {$scope: $scope});
        // then
        expect($scope.cancel).toBeDefined();
    }));
    

	it('should call dismiss with cancel argument ', inject(function($controller) {
		// given
		$controller('BookEditModalController', {
			$scope : $scope
		});
		// when
		$scope.cancel();
		// then
		expect(modalInstance.dismiss).toHaveBeenCalledWith('cancel');
	}));
	it('should call close with scope.title in ok function', inject(function($controller) {
		// given
		$controller('BookEditModalController', {
			$scope : $scope
		});
		$scope.EditForm = {
				$valid: true
		};
		$scope.title='tit';
		// when
		$scope.ok();
		// then
		expect(modalInstance.close).toHaveBeenCalledWith('tit');
	}));
	it('should return danger alert for invalid form in ok function', inject(function($controller,Flash) {
		// given
		$controller('BookEditModalController', {
			$scope : $scope
		});
		
		$scope.EditForm = {
				$valid: false
		};
		spyOn(Flash, 'create');
		$scope.title='';
		// when
		$scope.ok();
		// then
		expect(Flash.create).toHaveBeenCalledWith('danger','Tytul jest wymagany', 'custom-class');
		expect(modalInstance.close).not.toHaveBeenCalled();
	}));


});