//describe('editbook modal controller', function () {
//    'use strict';
//
//    
////    function FakeModal(){
////        this.resultDeferred = $q.defer();
////        this.result = this.resultDeferred.promise;
////    }
////    FakeModal.prototype.open = function(options){ return this;  };
////    FakeModal.prototype.close = function (item) {
////        this.resultDeferred.resolve(item);
////        $rootScope.$apply(); 
////    };
////    FakeModal.prototype.dismiss = function (item) {
////        this.resultDeferred.reject(item);
////        $rootScope.$apply(); 
////    };
//    
//    
//    beforeEach(function () {
//        module('app.main');
//        module('app.books');
//      //  module('$modalInstance');
//    });
//    
//    
//    var $scope;
//    
////    beforeEach(inject(function ($controller, $rootScope) {
////        scope = $rootScope.$new();
////        fakeModal = new FakeModal();
////       
////    }));
////    
////    beforeEach(inject(function($modal) {
////    	spyOn($modal, 'open').and.returnValue(fakeModal);
////    }));
//
//    it('ok is defined', inject(function ($controller) {
//        // when
//        $controller('EditBookModalController', {$scope: $scope});
//        // then
//        expect($scope.ok).toBeDefined();
//    }));
//    
//    it('cancel is defined', inject(function ($controller) {
//    	// when
//    	$controller('EditBookModalController', {$scope: $scope});
//    	// then
//    	expect($scope.cancel).toBeDefined();
//    }));
    

//    
//    it("should cancel the dialog when dismiss is called, and $scope.canceled should be true", function () {
//    	
//    	$controller('EditBookModalController', {$scope: scope,$modal: fakeModal });
//    	
//    	expect( scope.canceled ).toBeUndefined();
//
//        scope.open(); // Open the modal
//        scope.modalInstance.dismiss( "cancel" ); //Call dismiss (simulating clicking the cancel button on the modal)
//        expect( scope.canceled ).toBe( true );
//    });
    
//    
//});