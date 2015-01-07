(function () {
    angular.module('App', [
        'App.controllers',
        'ngRoute'
    ]).
    config(['$routeProvider', function($routeProvider) {
        $routeProvider.
            when("/", {templateUrl: "/resources/partials/main.html"}).
            when("/login", {templateUrl: "/resources/partials/login.html"}).
            when("/about", {templateUrl: "/resources/partials/about.html"}).
            when("/user/info", {templateUrl:"/resources/partials/userInfo.html"}).
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
            when("/skillWays", {
                controller: "SkillWaysListCtrl",
                templateUrl:"/resources/partials/skillsWays.html"
            }).
            when("/jobs/add", {
                controller: "AddJobCtrl",
                templateUrl:"/resources/partials/jobDetail.html"
            }).
            when('/jobs/edit/:id', {
                controller:"EditJobCtrl",
                templateUrl:'/resources/partials/jobDetail.html'
            }).
            when('/jobs/:id', {
                controller:"SkillsListCtrl",
                templateUrl:'/resources/partials/skills.html'
            }).
            when('/news/add', {
                controller:"AddNewsCtrl",
                templateUrl:'/resources/partials/newsDetail.html'
            }).
            when('/news/edit/:id', {
                controller:"EditNewsCtrl",
                templateUrl:'/resources/partials/newsDetail.html'
            }).

            otherwise({redirectTo: '/'});
    }]);
})();
