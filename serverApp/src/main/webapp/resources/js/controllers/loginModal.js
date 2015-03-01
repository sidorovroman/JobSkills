(function () {
    var app = angular.module('loginModal', []);

    app.controller('ModalLoginCtrl', function ($scope, $modal, $log) {

        $scope.items = ['item1', 'item2', 'item3'];
        $scope.login = function () {
            console.log('login');

        };
    })

})();