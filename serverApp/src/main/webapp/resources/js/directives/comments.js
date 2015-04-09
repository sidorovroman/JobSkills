(function () {
    var app = angular.module('comments', []);

    app.directive('ngComments', function() {
        return {
            restrict: 'E',
            templateUrl:"resources/partials/templates/comments.jsp",
            controller: ['$scope','$attrs', '$http', function($scope, $attrs, $http) {
                //че то элемент {{}} не компилиться
                console.log("controller атрибуты - комментарии:" + $attrs.comments);
                console.log("controller атрибуты - url:" + $attrs.url);

                $scope.comment = function(commentableObj){
                    // пока что только для новостей
                    var responsePromise = $http.put($attrs.url + '/' + commentableObj.id,{
                        message: $scope.message,
                        addDate: new Date().getTime()
                    });
                    responsePromise.success(function (dataFromServer, status, headers, config) {
                        console.log("comment success!");
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
            link: function(scope, iElement, iAttrs, ctrl) {
                console.log("атрибуты - комментарии:" + iAttrs.comments);
                console.log("атрибуты - url:" + iAttrs.url);
//                scope.getTemp(iAttrs.ngCity);
//                scope.$watch('weather', function(newVal) {
//                    // the `$watch` function will fire even if the
//                    // weather property is undefined, so we'll
//                    // check for it
//                    if (newVal) {
//                        var highs = [];
//
//                        angular.forEach(scope.weather, function(value){
//                            highs.push(value.temp.max);
//                        });
//
//                    }
//                });
            }
        }
    });
})();