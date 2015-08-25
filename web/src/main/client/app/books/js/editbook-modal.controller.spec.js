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

	it('should call dismiss with cancel argument ', inject(function($controller) {
		// given
		$controller('EditBookModalController', {
			$scope : $scope
		});
		// when
		$scope.cancel();
		// then
		expect($scope.cancel).toBeDefined();
		expect(modalInstance.dismiss).toHaveBeenCalledWith('cancel');
	}));
	it('should call close with scope.title in ok function', inject(function($controller) {
		// given
		$controller('EditBookModalController', {
			$scope : $scope
		});
		$scope.title='tit';
		// when
		$scope.ok();
		// then
	    expect($scope.ok).toBeDefined();
		expect(modalInstance.close).toHaveBeenCalledWith('tit');
	}));


});