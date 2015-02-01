(function () {
    var app = angular.module('jobs', []);

    app.controller("JobsListCtrl", function ($scope, $location, $http, $routeParams) {
        $http.get('/jobs/list').
            success(function (data) {
                $scope.jobs = data;
            }).
            error(function () {
                console.log("Fail");
            });

        $scope.select = function (job) {
            $location.path('/jobs/'+job.id);
        }
    });

    app.controller("AddJobCtrl", function ($scope, $location, $http) {
        $scope.JobsForm = {};
        $scope.save = function () {
            console.log("try to save this: ",this);
            console.log("try to save $scope: ",$scope);
            var dataObject = {
                caption: this.JobsForm.caption,
                description: this.JobsForm.description,
                parent: {
                    id: (this.JobsForm.parentId == "" ? null : this.JobsForm.parentId)
                }
            };

                $http.post("/jobs/add", dataObject).
                    success(function (dataFromServer, status, headers, config) {
                        console.log("add job success");
                        $location.path('/jobs');
                    }).
                    error(function (response, status) {
                        alert(response);
                    });
        }
    });

    app.controller("EditJobCtrl", function ($scope, $location, $http, $routeParams) {
        $scope.JobsForm = {};
        console.log("try to edit");

        $http.get('/jobs/' + $routeParams.id).
            success(function (data) {
                console.log("get job with id: " + $routeParams.id + " success");
                $scope.JobsForm = data;
            }).
            error(function () {
                console.log("get job with id: " + $routeParams.id + " failed");
            });

        $scope.save = function () {
            console.log("try to save");

            var dataObject = {
                id:this.JobsForm.id,
                caption: this.JobsForm.caption,
                description: this.JobsForm.description,
                parent: {
                    id: (this.JobsForm.parentId == "" ? null : this.JobsForm.parentId)
                }
            };

            var responsePromise = $http.put("/jobs/update", dataObject, {});
            responsePromise.success(function (dataFromServer, status, headers, config) {
                console.log("update success");
                $location.path('/jobs');
            });
            responsePromise.error(function (data, status, headers, config) {
                console.log("update error status "+status);
            });
        }
    });

    app.controller("JobCtrl", function ($scope, $location, $http, $routeParams) {
        $http.get('/jobs/' + $routeParams.jobId).
            success(function (data) {
                $scope.job = data;
            }).
            error(function () {
                alert("Fail");
            });
        $http.get('/requiredSkill/' + $routeParams.jobId + '/list').
            success(function (data) {
                $scope.skills = data;
            }).
            error(function () {
                alert("Fail");
            });
        $scope.add = function () {
            $location.path('/jobs/' + $routeParams.jobId + "/skills/add");
        };
        $scope.edit = function (skill) {
            $location.path('/jobs/' + $routeParams.jobId + "/skills/" + skill.id + "/edit");
        };
        $scope.select = function (skill) {
            $location.path('/jobs/' + $routeParams.jobId + "/skills/" + skill.id + '/ways');
        };
    });
})();