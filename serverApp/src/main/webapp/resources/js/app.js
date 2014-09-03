angular.module('App', [
        'App.services',
        'App.controllers',
        'ngRoute'
    ]).
    config(['$routeProvider', function($routeProvider) {
        $routeProvider.
            when("/", {templateUrl: "/resources/partials/home.html"}).
            when("/login", {templateUrl: "/resources/partials/login.html"}).
            when("/registration", {templateUrl:"/resources/partials/registration.html"}).
            otherwise({redirectTo: '/'});
    }]);