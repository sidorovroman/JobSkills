(function () {
    var app = angular.module('jobs', []);

    app.controller("JobsListCtrl", function ($scope, $sce, $location, $http, $routeParams) {
        $http.get('/jobs/list').
            success(function (data) {
                $scope.jobs = data;
            }).
            error(function () {
                console.log("Fail");
            });

        $scope.select = function (job) {
            $location.path('/jobs/'+job.id);
        };
        $scope.edit = function (job) {
            $location.path('/editJob/'+job.id);
        }
        $scope.renderHtml = function(html_code){
            return $sce.trustAsHtml(html_code);
        };
    });

    app.controller("AddJobCtrl", function ($scope, $location, $http) {
        CKEDITOR.replace( 'jobEditor' );

        $scope.JobsForm = {};
        $http.get('/jobs/list').
            success(function (data) {
                $scope.jobs = data;
            }).
            error(function () {
                console.log("Fail");
            });
        $scope.save = function () {
            console.log("try to save this: ",this);
            console.log("try to save $scope: ",$scope);
            var dataObject = {
                caption: this.JobsForm.caption,
                description: CKEDITOR.instances.jobEditor.getData(),
                parent: {
                    id: (this.JobsForm.parentId == "" ? null : this.JobsForm.parentId)
                }
            };

                $http.post("/jobs/add", dataObject).
                    success(function (dataFromServer, status, headers, config) {
                        if(dataFromServer.error==null){
                            console.log("add job success");
                            $location.path('/jobs');
                        }else{
                            alert(dataFromServer.error);
                        }
                    })
        }
    });

    app.controller("EditJobCtrl", function ($scope, $location, $http, $routeParams) {
        CKEDITOR.replace( 'jobEditor' );
        $scope.JobsForm = {};
        $http.get('/jobs/list').
            success(function (data) {
                $scope.jobs = data;

            }).
            error(function () {
                console.log("Fail");
            });
        console.log("try to edit");

        $http.get('/jobs/' + $routeParams.id).
            success(function (data) {
                console.log("get job with id: " + $routeParams.id + " success");
                $scope.JobsForm = data;
                CKEDITOR.instances.jobEditor.setData(data.description);

            }).
            error(function () {
                console.log("get job with id: " + $routeParams.id + " failed");
            });

        $scope.save = function () {
            console.log("try to save");

            var dataObject = {
                id:this.JobsForm.id,
                caption: this.JobsForm.caption,
                description: CKEDITOR.instances.jobEditor.getData(),
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

    app.controller("JobCtrl", function ($scope, $sce, $location, $http, $routeParams) {
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
        $scope.editSkill = function (skill) {
            $location.path('/jobs/' + $routeParams.jobId + "/skills/" + skill.id + "/edit");
        };
        $scope.select = function (skill) {
            $location.path('/jobs/' + $routeParams.jobId + "/skills/" + skill.id + '/ways');
        };
        $scope.renderHtml = function(html_code){
            return $sce.trustAsHtml(html_code);
        };


        $scope.voteUp = function(skill){
            var responsePromise = $http.post("/requiredSkill/up/"+skill.id,{});
            responsePromise.success(function (dataFromServer, status, headers, config) {
                console.log("vote up success!");
                skill.rating = dataFromServer;

            });
            responsePromise.error(function (data, status, headers, config) {
                alert("vote up form failed!");
            });
        };
        $scope.voteDown = function(skill){
            var responsePromise = $http.post("/requiredSkill/down/"+skill.id,{});
            responsePromise.success(function (dataFromServer, status, headers, config) {
                console.log("vote down success!");
                skill.rating = dataFromServer;

            });
            responsePromise.error(function (data, status, headers, config) {
                alert("vote down form failed!");
            });
        }
    });
})();