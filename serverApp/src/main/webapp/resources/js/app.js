(function () {
    angular.module('App', [
        'App.controllers',
        'ngRoute'
    ]).
    config(['$routeProvider', function($routeProvider) {
        $routeProvider.
            when("/", {templateUrl: "/resources/partials/content/main.html"}).
            when("/login", {templateUrl: "/resources/partials/content/login.html"}).
            when("/about", {templateUrl: "/resources/partials/content/about.html"}).
            when("/user/info", {
                controller: "UserInfoCtrl",
                templateUrl:"/resources/partials/content/userInfo.html",
            }).
            when("/jobs", {
                controller: "JobsListCtrl",
                templateUrl:"/resources/partials/lists/jobs.html"
            }).
            when("/dashboard", {
                controller: "DashboardListCtrl",
                templateUrl:"/resources/partials/content/dashboard.html"
            }).
            when("/news", {
                controller: "NewsListCtrl",
                templateUrl:"/resources/partials/lists/news.html"
            }).
            when("/skillWays", {
                controller: "SkillWaysListCtrl",
                templateUrl:"/resources/partials/lists/skillsWays.html"
            }).
            when("/jobs/add", {
                controller: "AddJobCtrl",
                templateUrl:"/resources/partials/details/job.html"
            }).
            when('/jobs/edit/:id', {
                controller:"EditJobCtrl",
                templateUrl:'/resources/partials/details/job.html'
            }).
            when('/jobs/:id', {
                controller:"SkillsListCtrl",
                templateUrl:'/resources/partials/lists/skills.html'
            }).
            when('/jobs/:id/add', {
                controller:"AddSkillCtrl",
                templateUrl:'/resources/partials/details/skill.html'
            }).
            when('/jobs/:jobId/edit/:skillId', {
                controller:"EditSkillCtrl",
                templateUrl:'/resources/partials/details/skill.html'
            }).
            when('/news/add', {
                controller:"AddNewsCtrl",
                templateUrl:'/resources/partials/details/news.html'
            }).
            when('/news/edit/:id', {
                controller:"EditNewsCtrl",
                templateUrl:'/resources/partials/details/news.html'
            }).

            otherwise({redirectTo: '/'});
    }]);
})();
