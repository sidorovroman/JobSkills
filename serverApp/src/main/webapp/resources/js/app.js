angular.module('App', [
        'App.services',
        'App.controllers',
        'ngRoute'
    ]).
    config(['$routeProvider', function($routeProvider) {
        $routeProvider.
            when("/", {templateUrl: "/resources/partials/home.html"}).
          when("/login", {templateUrl: "/resources/partials/login.html"}).
          when("/userInfo", {templateUrl:"/resources/partials/userInfo.html"}).
          when("/jobs", {
                controller: "JobsCtrl",
                templateUrl:"/resources/partials/jobs.html"
            }).

            otherwise({redirectTo: '/'});
    }]);