(function () {
    var app = angular.module('skillWays', []);

    app.controller("SkillWaysListCtrl", function ($scope, $http, $routeParams, $location) {
        $http.get('/wayToImproveSkill/'+$routeParams.skillId+'/list').
            success(function (data) {
                console.log("ways: "+data);
                $scope.ways = data;
            }).
            error(function () {
                alert("Fail");
            });
        $scope.add = function () {
            $location.path('/jobs/'+$routeParams.jobId+"/"+$routeParams.skillId+"/add");
        }
    })
    app.controller("AddSkillWaysCtrl", function ($scope, $location, $http, $routeParams) {
        $scope.SkillWaysForm = {};
        $scope.save = function () {
            var dataObject = {
                caption: this.SkillWaysForm.caption,
                description: this.SkillWaysForm.description,
                link: this.SkillWaysForm.link,
                addDate:  new Date().getTime(),
                grade:  this.SkillWaysForm.grade,
                resourceType:  this.SkillWaysForm.resourceType,
                skill:  this.SkillWaysForm.skill
            };

            var responsePromise = $http.post("/wayToImproveSkill/add", dataObject, {});
            responsePromise.success(function (dataFromServer, status, headers, config) {
                alert("add skill way success");
                window.history.back();
            });
            responsePromise.error(function (data, status, headers, config) {
                alert("Submitting form failed!");
            });
        }
    });
})();