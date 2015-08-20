angular.module('app.authors').factory('authorsService', function (authorsRestService) {
    'use strict';

    return {
        get: function () {
            return authorsRestService.get();
        }
    
    };
});
