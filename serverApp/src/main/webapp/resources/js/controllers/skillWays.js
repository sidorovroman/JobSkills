(function () {
    var app = angular.module('skillWays', []);

    app.controller("SkillWaysListCtrl", function ($scope, $http) {
        $http.get('/wayToImproveSkill/list').
            success(function (data) {
                console.log("ways: "+data);
                $scope.ways = data;
            }).
            error(function () {
                alert("Fail");
            });
        $scope.remove = function (way) {

            $http.delete('/wayToImproveSkill/' + way.id).
                success(function (data) {
                    alert("Success remove");
                }).
                error(function () {
                    alert("Fail");
                });
        }
    })

})();