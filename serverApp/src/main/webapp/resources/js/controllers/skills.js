(function () {
    var app = angular.module('skills', []);

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
                console.log("add skill success");
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
                console.log("success");
                console.log("get skill with id: " + $routeParams.skillId + " success");
                $scope.SkillForm = data;
            }).
            error(function () {
                alert("get job with id: " + $routeParams.skillId + " failed");
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
                console.log("update success");
                window.history.back();
            });
            responsePromise.error(function (data, status, headers, config) {
                console.log("update error status "+status);
            });
        }
    });

})();