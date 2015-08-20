angular.module('app.authors').factory('authorsRestService', function ($http, currentContextPath) {
    'use strict';

    return {
        get: function () {
        	  return $http.get(currentContextPath.get() + 'rest/authors/authors');
        }
    
    };
});
