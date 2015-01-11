(function () {
    var app = angular.module('skills', []);

    app.controller("SkillsListCtrl", function ($scope, $location, $http, $routeParams) {
        $http.get('/requiredSkill/' + $routeParams.id + '/list').
            success(function (data) {
                $scope.skills = data;
            }).
            error(function () {
                alert("Fail");
            });
        $scope.add = function () {
            $location.path('/jobs/'+$routeParams.id+"/add");
        }
        $scope.edit = function (skill) {
            $location.path('/jobs/'+$routeParams.id+"/edit/" + skill.id);
        }
    })
    app.controller("AddSkillCtrl", function ($scope, $location, $http, $routeParams) {
        $scope.SkillForm = {};
        $scope.save = function () {
            var dataObject = {
                caption: this.SkillForm.caption,
                description: this.SkillForm.description,
                job:{id: $routeParams.id}
            };

            var responsePromise = $http.post("/requiredSkill/add", dataObject, {});
            responsePromise.success(function (dataFromServer, status, headers, config) {
                alert("add job success");
                $location.path('/skills');
            });
            responsePromise.error(function (data, status, headers, config) {
                alert("Submitting form failed!");
            });
        }
    });
    app.controller("EditSkillCtrl", function ($scope, $location, $http, $routeParams) {
//        $scope.SkillForm = {};
//        console.log("try to edit");
//
//        $http.get('/jobs/' + $routeParams.id).
//            success(function (data) {
//                alert("success");
//                console.log("get job with id: " + $routeParams.id + " success");
//                $scope.JobsForm = data;
//            }).
//            error(function () {
//                console.log("get job with id: " + $routeParams.id + " failed");
//            });
//
//        $scope.save = function () {
//            console.log("try to save");
//
//            var dataObject = {
//                id:this.JobsForm.id,
//                caption: this.JobsForm.caption,
//                description: this.JobsForm.description,
//                parent: {
//                    id: (this.JobsForm.parentId == "" ? null : this.JobsForm.parentId)
//                }
//            };
//
//            var responsePromise = $http.put("/jobs/update", dataObject, {});
//            responsePromise.success(function (dataFromServer, status, headers, config) {
//                alert("update success");
//                $location.path('/jobs');
//            });
//            responsePromise.error(function (data, status, headers, config) {
//                alert("update error status "+status);
//            });
//        }
    });

})();