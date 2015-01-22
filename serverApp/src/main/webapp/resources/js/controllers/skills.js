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
        $scope.select = function (skill) {
            $location.path('/jobs/'+$routeParams.id+"/"+skill.id);
        }
    })
    app.controller("AddSkillCtrl", function ($scope, $location, $http, $routeParams) {
        $scope.SkillForm = {};
        $scope.save = function () {
            var dataObject = {
                caption: this.SkillForm.caption,
                description: this.SkillForm.description,
                ways: [{id:this.SkillForm.ways}],//todo пока в тестовом варианте
                job:{id: $routeParams.id}
            };

            var responsePromise = $http.post("/requiredSkill/add", dataObject, {});
            responsePromise.success(function (dataFromServer, status, headers, config) {
                alert("add skill success");
                window.history.back();
            });
            responsePromise.error(function (data, status, headers, config) {
                alert("Submitting form failed!");
            });
        }
    });
    app.controller("EditSkillCtrl", function ($scope, $location, $http, $routeParams) {
        $scope.SkillForm = {};
        $http.get('/requiredSkill/' + $routeParams.skillId).
            success(function (data) {
                alert("success");
                console.log("get skill with id: " + $routeParams.skillId + " success");
                $scope.SkillForm = data;
            }).
            error(function () {
                console.log("get job with id: " + $routeParams.skillId + " failed");
            });

        $scope.save = function () {
            var dataObject = {
                id: $routeParams.skillId,
                caption: this.SkillForm.caption,
                description: this.SkillForm.description,
                job:{id: $routeParams.jobId}
            };
            console.log("save: " + dataObject);

            console.log("dataObject "+dataObject);

            var responsePromise = $http.put("/requiredSkill/update", dataObject);
            responsePromise.success(function (dataFromServer, status, headers, config) {
                alert("update success");
                window.history.back();
            });
            responsePromise.error(function (data, status, headers, config) {
                alert("update error status "+status);
            });
        }
    });

})();