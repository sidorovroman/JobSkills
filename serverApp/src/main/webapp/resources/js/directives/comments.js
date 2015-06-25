(function () {
    var app = angular.module('comments', []);

    app.directive('ngComments', function() {
        return {
            restrict: 'E',
            templateUrl:"resources/partials/templates/comments.jsp",
            scope: {
                comments: '=',
                id: "="
            },
            controller: ['$scope','$attrs', '$http', function($scope, $attrs, $http) {
                $scope.comment = function(id){

                    var responsePromise = $http.put($attrs.url + '/' + id,{
                        message: $scope.message,
                        addDate: new Date().getTime()
                    });
                    responsePromise.success(function (dataFromServer, status, headers, config) {
                        $scope.comments.push(dataFromServer);
                        console.log("comment success!");
                        $scope.toggleCommentForm();
                    });
                    responsePromise.error(function (data, status, headers, config) {
                        alert("comment failed!");
                    });
                };

                $scope.showCommentForm = true;
                $scope.toggleCommentForm = function() {
                    $scope.showCommentForm = $scope.showCommentForm === false ? true: false;
                };
            }],

            link: function (scope, element, attrs) {
            }
        }
    });
})();