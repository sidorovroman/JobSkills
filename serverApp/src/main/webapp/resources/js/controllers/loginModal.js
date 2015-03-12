(function () {
    var app = angular.module('loginModal', []);

    app.controller('ModalLoginCtrl', function ($scope, $http, $location) {

        var dataObject = {
            email: this.mail,
            password: this.password
        };
        $scope.login = function () {
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
            $http.post('/register',dataObject)
                .success(function(response){
                    if(response.error==null){
                        console.log("register success");
                    }else{
                        alert(response.error);
                    }
                });

        };
    })

})();