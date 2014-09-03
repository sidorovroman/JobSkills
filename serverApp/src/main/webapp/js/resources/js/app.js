angular.module('App', [
        'App.services',
        'App.controllers',
        'ngRoute'
    ]).
    config(['$routeProvider', function($routeProvider) {
        $routeProvider.
            when("/", {templateUrl: "./js/resources/partials/home.html"}).
            when("/login", {templateUrl: "./js/resources/partials/login.html"}).
            when("/userInfo", {templateUrl:"./js/resources/partials/userInfo.html"}).
            otherwise({redirectTo: '/'});
    }]);