(function () {
    var app = angular.module('news', []);

    app.controller("NewsListCtrl", function ($scope, $http) {
        $http.get('/news/list').
            success(function (data) {
                $scope.news = data;
            }).
            error(function () {
                alert("Fail");
            });
        $scope.remove = function (news) {

            console.log("id: " + news.id);
            console.log("$routeParams:" + $routeParams);
            $http.delete('/news/' + news.id).
                success(function (data) {
                    alert("Success remove");
                }).
                error(function () {
                    alert("Fail");
                });
        }
    })
    app.controller("AddNewsCtrl", function ($scope, $location, $http) {
        $scope.NewsForm = {};
        $scope.save = function () {
            var dataObject = {
                caption: this.NewsForm.caption,
                body: this.NewsForm.body,
                addDate: this.NewsForm.addDate,
                author: this.NewsForm.author,
                link: this.NewsForm.link,
                tags: this.NewsForm.tags,
                commentaries: this.NewsForm.commentaries,
                rating: this.NewsForm.rating
            };

            var responsePromise = $http.post("/news/add", dataObject, {});
            responsePromise.success(function (dataFromServer, status, headers, config) {
                alert("add news success");
                $location.path('/news');
            });
            responsePromise.error(function (data, status, headers, config) {
                alert("Submitting form failed!");
            });
        }
    });
    app.controller("EditNewsCtrl", function ($scope, $location, $http, $routeParams) {
        $scope.NewsForm = {};
        console.log("try to edit");

        $http.get('/news/' + $routeParams.id).
            success(function (data) {
                alert("success");
                console.log("get news with id: " + $routeParams.id + " success");
                $scope.NewsForm = data;
            }).
            error(function () {
                console.log("get news with id: " + $routeParams.id + " failed");
            });

        $scope.save = function () {
            console.log("try to save");

            var dataObject = {
                id:this.NewsForm.id,
                caption: this.NewsForm.caption,
                body: this.NewsForm.body,
                addDate: this.NewsForm.addDate,
                author: this.NewsForm.author,
                link: this.NewsForm.link,
                tags: this.NewsForm.tags,
                commentaries: this.NewsForm.commentaries,
                rating: this.NewsForm.rating
            };

            var responsePromise = $http.put("/news/update", dataObject, {});
            responsePromise.success(function (dataFromServer, status, headers, config) {
                alert("update success");
                $location.path('/news');
            });
            responsePromise.error(function (data, status, headers, config) {
                alert("update error status "+status);
            });
        }
    });
})();