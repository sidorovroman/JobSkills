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
    })

})();