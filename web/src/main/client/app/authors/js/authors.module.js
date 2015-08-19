angular.module('app.authors', ['ngRoute'])
    .config(function ($routeProvider) {
        'use strict';
        $routeProvider.when('/authors/authors-list', {templateUrl: 'authors/html/authors-list.html'});
    });


