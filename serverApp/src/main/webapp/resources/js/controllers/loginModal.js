(function () {
    var app = angular.module('loginModal', []);

    app.controller('ModalLoginCtrl', function ($scope, $http, $location) {
        $scope.login = function () {
            var $form = $('#login-form');
            $form.attr('action', '/j_spring_security_check');
            
            //добавление дополнительного аттрибута. поле не нужно удалять, тк страница обновляется
            $('<input class="additional-field"/>').attr('type', 'hidden')
                .attr('name', "redirectPath")
                .attr('value', $location.path())
                .appendTo('#login-form');
            $form.submit();
//
//            $.post(
//                "/j_spring_security_check",
//                {
//                    mail:$scope.mail,
//                    password: $scope.password
//                },
//                function(data){
//                    console.log("post success");
//                    if(data.error==null){
//                        console.log("login success");
//                    }else{
//                        alert(data.error);
//                    }
//                }
//            );
        };
        $scope.register = function () {

            $('#login-form').attr('action', '/register');
            //добавление дополнительного аттрибута. поле не нужно удалять, тк страница обновляется
            $('<input class="additional-field"/>').attr('type', 'hidden')
                .attr('name', "redirectPath")
                .attr('value', $location.path())
                .appendTo('#login-form');
            $('#login-form').submit();
        };
    })

})();