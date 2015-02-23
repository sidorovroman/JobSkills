(function () {
    var app = angular.module('news', []);

    app.controller("NewsListCtrl", function ($scope, $http,$sce,$location) {
        $http.get('/news/list').
            success(function (data) {
                $scope.news = data;
            }).
            error(function () {
                alert("Fail");
            });

        $scope.select = function (news) {
            $location.path('/news/' + news.id);
        };

        $scope.renderHtml = function(html_code){
            return $sce.trustAsHtml(html_code);
        };
        $scope.voteUp = function(info){
            var responsePromise = $http.post("/news/up/"+info.id,{});
            responsePromise.success(function (dataFromServer, status, headers, config) {
                alert("vote up success!");
                info.rating = dataFromServer;

            });
            responsePromise.error(function (data, status, headers, config) {
                alert("vote up form failed!");
            });
        }
        $scope.voteDown = function(info){
            var responsePromise = $http.post("/news/down/"+info.id,{});
            responsePromise.success(function (dataFromServer, status, headers, config) {
                alert("vote down success!");
                info.rating = dataFromServer;

            });
            responsePromise.error(function (data, status, headers, config) {
                alert("vote down form failed!");
            });
        }
    });
    app.controller("NewsCtrl", function ($scope, $http,$sce,$routeParams) {
        $http.get('/news/' + $routeParams.newsId).
            success(function (data) {
                $scope.news = data;
                console.log(data);
            }).
            error(function () {
                alert("Fail");
            });
        $scope.renderHtml = function(html_code){
            return $sce.trustAsHtml(html_code);
        };
        $scope.voteUp = function(info){
            var responsePromise = $http.post("/news/up/"+info.id,{});
            responsePromise.success(function (dataFromServer, status, headers, config) {
                alert("vote up success!");
                info.rating = dataFromServer;

            });
            responsePromise.error(function (data, status, headers, config) {
                alert("vote up form failed!");
            });
        }
        $scope.voteDown = function(info){
            var responsePromise = $http.post("/news/down/"+info.id,{});
            responsePromise.success(function (dataFromServer, status, headers, config) {
                alert("vote down success!");
                info.rating = dataFromServer;

            });
            responsePromise.error(function (data, status, headers, config) {
                alert("vote down form failed!");
            });
        }
    });
    app.controller("AddNewsCtrl", function ($scope, $location, $http) {

        CKEDITOR.replace( 'newsEditor' );
        $scope.NewsForm = {};
        $scope.save = function () {
            var dataObject = {
                caption: this.NewsForm.caption,
                body: CKEDITOR.instances.newsEditor.getData(),
                addDate: this.NewsForm.addDate,
                author: this.NewsForm.author,
                link: this.NewsForm.link,
                tags: this.NewsForm.tags,
                rating: this.NewsForm.rating
            };

            var responsePromise = $http.post("/news/add", dataObject, {});
            responsePromise.success(function (dataFromServer, status, headers, config) {
                $location.path('/news');
            });
            responsePromise.error(function (data, status, headers, config) {
                alert("Submitting form failed!");
            });
        }
    });
    app.controller("EditNewsCtrl", function ($scope, $location, $http, $routeParams) {

        CKEDITOR.replace( 'newsEditor' );
        $scope.NewsForm = {};
        console.log("try to edit");

        $http.get('/news/' + $routeParams.id).
            success(function (data) {
                console.log("get news with id: " + $routeParams.id + " success");
                $scope.NewsForm = data;
                CKEDITOR.instances.newsEditor.setData(data.body);
            }).
            error(function () {
                console.log("get news with id: " + $routeParams.id + " failed");
            });

        $scope.save = function () {
            console.log("try to save");

            var dataObject = {
                id:this.NewsForm.id,
                caption: this.NewsForm.caption,
                body: CKEDITOR.instances.newsEditor.getData(),
                addDate: new Date().getTime(),
                author: this.NewsForm.author,
                link: this.NewsForm.link,
                tags: this.NewsForm.tags,
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