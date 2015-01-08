(function () {
    var app = angular.module('jobs', []);

    app.controller("JobsListCtrl", function ($scope, $location, $http, $routeParams) {
        $http.get('/jobs/list').
            success(function (data) {
                $scope.jobs = data;
            }).
            error(function () {
                alert("Fail");
            });

        $scope.remove = function (job) {
            $http.delete('/jobs/' + job.id).
                success(function (data) {
                    console.log("Success remove job.id="+job.id);
                }).
                error(function () {
                    console.log("Fail remove job.id="+job.id);
                });
        }

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

            var responsePromise = $http.post("/jobs/add", dataObject, {});
            responsePromise.success(function (dataFromServer, status, headers, config) {
                alert("add job success");
                $location.path('/jobs');
            });
            responsePromise.error(function (data, status, headers, config) {
                alert("Submitting form failed!");
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
                alert("update success");
                $location.path('/jobs');
            });
            responsePromise.error(function (data, status, headers, config) {
                alert("update error status "+status);
            });
        }
    });

})();