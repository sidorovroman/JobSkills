angular.module('App.controllers', [])

    .controller('UserInfo',function UserInfo($scope) {
            $scope.save = function (info, userInfoForm) {
                if(info.pass.equals(info.pass2)){
                    alert("пароли не совпадают");
                }
                if (userInfoForm.$valid) {
                    // действия по сохранению
                    alert(info.login + ", всё ништяк");
                }
            };
        }
    )
    .controller("NewJobCtrl",function ($scope, $location, $http) {
            $scope.JobsForm = {};
            $scope.JobsForm.add = function() {
                var dataObject = {
                    caption : this.caption,
                    description  : this.description,
                    parent : {
                        id:(this.parentId == "" ? null : this.parentId)
                    }
                };

                var responsePromise = $http.post("/jobs/add", dataObject,{});
                responsePromise.success(function(dataFromServer, status, headers, config) {
                    $location.path('/jobs');
                });
                responsePromise.error(function(data, status, headers, config) {
                    alert("Submitting form failed!");
                });
            }
        })
    .controller("JobsListCtrl",function ($scope, $http) {
            $http.get('/jobs/list').
                success(function(data) {
                    $scope.jobs = data;
                }).
                error(function(){
                        alert("Fail");
                    });
    })
    .controller("DashboardListCtrl",function ($scope, $http) {
        $http.get('/dashboard/list').
            success(function(data) {
                $scope.dashboard = data;
            }).
            error(function(){
                alert("Fail");
            });
    })
    .controller("NewsListCtrl",function ($scope, $http) {
        $http.get('/news/list').
            success(function(data) {
                $scope.news = data;
            }).
            error(function(){
                alert("Fail");
            });
    })
    .controller("SkillsListCtrl",function ($scope, $http) {
        $http.get('/skills/list').
            success(function(data) {
                $scope.skills = data;
            }).
            error(function(){
                alert("Fail");
            });
    })
    .controller("WaysToImproveSkillsListCtrl",function ($scope, $http) {
        $http.get('/waysToImproveSkills/list').
            success(function(data) {
                $scope.ways = data;
            }).
            error(function(){
                alert("Fail");
            });
    })
