angular.module('App.controllers', [])

    .controller('UserInfo',
    function UserInfo($scope) {
        $scope.save = function (info, userInfoForm) {
            if(info.pass.equals(info.pass2)){
                alert("пароли не совпадают");
            }
            if (userInfoForm.$valid) {
                // действия по сохранению
                alert(info.login + ", всё ништяк");
            }
        };
    })
    .controller(
        "JobsCtrl",
        function ($scope, $http) {
            $scope.JobsForm = {};
            $scope.JobsForm.send = function() {
                alert("--> Submitting form");

                var dataObject = {
                    caption : this.caption,
                    description  : this.description,
                    parent : {
                        id:this.parentId
                    }
                };

                var responsePromise = $http.post("/jobs/add", dataObject,{});
                responsePromise.success(function(dataFromServer, status, headers, config) {
                    alert("ok "+ dataFromServer.title);

                });
                responsePromise.error(function(data, status, headers, config) {
                    alert("Submitting form failed!");
                });
            }
        })
