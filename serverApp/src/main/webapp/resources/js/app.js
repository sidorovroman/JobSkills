angular.module('App', [
        'App.services',
        'App.controllers',
        'ngRoute'
    ]).
    config(['$routeProvider', function($routeProvider) {
        $routeProvider.
            when("/", {templateUrl: "/resources/partials/main.html"}).
            when("/login", {templateUrl: "/resources/partials/login.html"}).
            when("/userInfo", {templateUrl:"/resources/partials/userInfo.html"}).
            when("/jobs", {
                controller: "JobsListCtrl",
                templateUrl:"/resources/partials/jobs.html"
            }).
            when("/dashboard", {
                controller: "DashboardListCtrl",
                templateUrl:"/resources/partials/dashboard.html"
            }).
            when("/news", {
                controller: "NewsListCtrl",
                templateUrl:"/resources/partials/news.html"
            }).
            when("/skills", {
                controller: "SkillsListCtrl",
                templateUrl:"/resources/partials/skills.html"
            }).
            when("/waysToImproveSkills", {
                controller: "WaysToImproveSkillsListCtrl",
                templateUrl:"/resources/partials/waysToImproveSkills.html"
            }).
            when("/newJob", {
                controller: "NewJobCtrl",
                templateUrl:"/resources/partials/addJob.html"
            }).

            otherwise({redirectTo: '/'});
    }]);