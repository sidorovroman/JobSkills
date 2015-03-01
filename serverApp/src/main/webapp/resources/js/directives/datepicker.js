(function () {
    angular.module('datepicker', ['ui.bootstrap'])
        .controller('DatepickerCtrl', function ($scope) {
        if($scope.info==null){
            $scope.info={}
        }
        $scope.today = function() {
            $scope.info.birthday = new Date();
        };
        $scope.today();

        $scope.clear = function () {
            $scope.info.birthday = null;
        };

//        // Disable weekend selection
//        $scope.disabled = function(date, mode) {
//            return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
//        };
//
//        $scope.toggleMin = function() {
//            $scope.minDate = $scope.minDate ? null : new Date();
//        };
//        $scope.toggleMin();

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