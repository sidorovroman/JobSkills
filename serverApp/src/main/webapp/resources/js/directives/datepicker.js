(function () {
    var app = angular.module('datepicker', ['ui.bootstrap']);

    app.controller('DatepickerCtrl', function ($scope) {
        $scope.today = function() {
            if($scope.info==null){
                $scope.info={}
            }
            $scope.info.birthday = new Date();
        };
        $scope.today();

        $scope.clear = function () {
            $scope.td = null;
        };

        // Disable weekend selection
        $scope.disabled = function(date, mode) {
            return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
        };

        $scope.toggleMin = function() {
            $scope.minDate = $scope.minDate ? null : new Date();
        };
        $scope.toggleMin();

        $scope.open = function($event) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope.opened = true;
        };

        $scope.dateOptions = {
            formatYear: 'yy',
            startingDay: 1
        };

        $scope.format = 'dd.MM.yyyy';
    });
})();