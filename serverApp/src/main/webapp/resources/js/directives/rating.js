(function () {
    var app = angular.module('rating', []);

    app.directive('ngRating', function() {
        return {
            restrict: 'E',
            templateUrl:"resources/partials/templates/rating.jsp",
            scope: {
                id:"@id",
                rating:"@value",
                urlPart: "@urlPart"
            },
            link: function(scope, elem, attributes){

                scope.id = attributes.id;
                scope.rating = attributes.value;
                scope.urlPart = attributes.urlPart;
            },

            controller: ['$scope','$attrs', '$http', function($scope, $attrs, $http) {
                $scope.voteUp = function () {
                    var responsePromise = $http.post($scope.urlPart + "/up/" + $scope.id, {});
                    responsePromise.success(function (dataFromServer, status, headers, config) {
                        $scope.rating = dataFromServer;
                        console.log("vote up success!");
                    });
                    responsePromise.error(function (data, status, headers, config) {
                        alert("vote up form failed!");
                    });
                };

                $scope.voteDown = function () {
                    var responsePromise = $http.post($scope.urlPart + "/down/" + $scope.id, {});
                    responsePromise.success(function (dataFromServer, status, headers, config) {
                        $scope.rating = dataFromServer;
                        console.log("vote down success!");
                    });
                    responsePromise.error(function (data, status, headers, config) {
                        alert("vote down form failed!");
                    });
                };
            }]
        }
    });
})();