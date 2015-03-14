(function () {
    var app = angular.module('loginModal', []);

    app.controller('ModalLoginCtrl', function ($scope, $http, $location) {
        $scope.login = function () {

            var dataObject = {
                mail: $scope.mail,
                password: $scope.password
            };

            $http.post('/j_spring_security_check',dataObject)
                .success(function(response){
                    if(response.error==null){
                        console.log("login success");
                    }else{
                        alert(response.error);
                    }
                });
        };
        $scope.register = function () {
            $http.post('/register',
                {
                    mail:$scope.mail,
                    password: $scope.password
                }).
                success(function(data, status, headers, config) {
                    console.log("post success");
                    if(data.error==null){
                        console.log("register success");
                    }else{
                        alert(data.error);
                    }
                }).
                error(function(data, status, headers, config) {
                    console.log("post error");
                });
        };
    })

})();