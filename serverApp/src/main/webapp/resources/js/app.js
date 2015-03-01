(function () {
    angular.module('App', [
        'App.controllers',
        'App.directives',
        'ngRoute'
    ]).
    config(['$routeProvider', function($routeProvider) {
        $routeProvider.

            when("/", {
                templateUrl: "/resources/partials/content/main.html"
            }).

            /* ============  jobs  ============ */

            when("/jobs", {
                controller: "JobsListCtrl",
                templateUrl:"/resources/partials/lists/jobs.jsp"
            }).
            when('/jobs/:jobId', {
                controller:"JobCtrl",
                templateUrl:'/resources/partials/info/job.html'
            }).
            when("/addJob", {
                controller: "AddJobCtrl",
                templateUrl:"/resources/partials/details/job.html"
            }).
            when('/editJob/:id', {
                controller:"EditJobCtrl",
                templateUrl:'/resources/partials/details/job.html'
            }).

            /* ============  skills  ============ */

            when('/jobs/:id/skills/add', {
                controller:"AddSkillCtrl",
                templateUrl:'/resources/partials/details/skill.html'
            }).
            when('/jobs/:jobId/skills/:skillId/edit', {
                controller:"EditSkillCtrl",
                templateUrl:'/resources/partials/details/skill.html'
            }).

            /* ============  skill ways  ============ */

            when('/jobs/:jobId/skills/:skillId/ways', {
                controller:"SkillWaysListCtrl",
                templateUrl:'/resources/partials/lists/skillWays.html'
            }).
            when('/jobs/:jobId/skills/:skillId/ways/add', {
                controller:"AddSkillWaysCtrl",
                templateUrl:'/resources/partials/details/skillWays.html'
            }).
            when('/jobs/:jobId/skills/:skillId/ways/:wayId/edit', {
                controller:"EditSkillWaysCtrl",
                templateUrl:'/resources/partials/list/skillWays.html'
            }).

            /* ============  news  ============ */

            when("/news", {
                controller: "NewsListCtrl",
                templateUrl:"/resources/partials/lists/news.jsp"
            }).
            when('/news/add', {
                controller:"AddNewsCtrl",
                templateUrl:'/resources/partials/details/news.html'
            }).
            when('/news/:id/edit', {
                controller:"EditNewsCtrl",
                templateUrl:'/resources/partials/details/news.html'
            }).
            when('/news/:newsId', {
                controller:"NewsCtrl",
                templateUrl:'/resources/partials/info/news.jsp'
            }).

            /* ============  others  ============ */

            when("/login", {
                controller:"LoginCtrl",
                templateUrl: "/resources/partials/content/login.html"
            }).
            when("/logout", {
                controller: function(){
                    alert("Выход");
                }
            }).
            when("/about", {templateUrl: "/resources/partials/content/about.html"}).

            when("/user/info", {
                controller: "UserInfoCtrl",
                templateUrl:"/resources/partials/content/userInfo.html"
            }).
            when("/dashboard", {
                controller: "DashboardListCtrl",
                templateUrl:"/resources/partials/content/dashboard.html"
            }).

            otherwise({redirectTo: '/'});
    }]);

    CKEDITOR.editorConfig = function( config ) {
        config.htmlEncodeOutput = false;
        config.entities = false;
    };

    $('.btn-start').click(function(){
        $('.start-page').slideUp();
    });
})();
