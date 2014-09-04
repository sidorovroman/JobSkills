angular.module('App', [
        'App.services',
        'App.controllers',
        'ngRoute'
    ]).
    config(['$routeProvider', function($routeProvider) {
        $routeProvider.
            when("/", {templateUrl: "/resources/partials/home.html"}).
            when("/login", {templateUrl: "/login"}).
            when("/userInfo", {templateUrl:"/user/index"}).

            otherwise({redirectTo: '/'});
    }]);