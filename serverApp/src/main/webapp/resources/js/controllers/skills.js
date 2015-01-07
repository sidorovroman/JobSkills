(function () {
    var app = angular.module('skills', []);

    app.controller("SkillsListCtrl", function ($scope, $http, $routeParams) {
        $http.get('/requiredSkill/' + $routeParams.id + '/list').
            success(function (data) {
                $scope.skills = data;
            }).
            error(function () {
                alert("Fail");
            });
        $scope.remove = function (skill) {

            $http.delete('/requiredSkill/' + skill.id).
                success(function (data) {
                    alert("Success remove");
                }).
                error(function () {
                    alert("Fail");
                });
        }
    })

})();