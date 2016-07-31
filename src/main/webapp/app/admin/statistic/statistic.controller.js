(function () {
    'use strict';

    angular
        .module('logsApp')
        .controller('StatisticController', StatisticController);

    StatisticController.$inject = ['$timeout', 'ALertLog'];

    function StatisticController( $timeout, ALertLog) {
        var vm = this;
        vm.onClick = onClick;
        vm.loadAll = loadAll;

        vm.loadAll();


        function loadAll () {
            ALertLog.stat({}, function (result) {
             //   console.log('The result is '+Object.keys(result)) ;
                vm.alerts = result;
            });
        }



        vm.labels =/* Object.keys(vm.alerts)*/["January", "February", "March", "April", "May", "June", "July"];
        vm.series = ['TRACE', 'DEBUG','INFO','WARN','ERROR','SEVERE'];
        vm.data = [
            [65, 59, 80, 81, 56, 55, 40],
            [28, 30, 40, 19, 86, 27, 90],
            [30, 70, 20, 12, 53, 30, 63],
            [50, 60, 53, 30, 100, 15, 78],
            [100, 66, 30, 40, 30, 40, 45],
            [70, 45, 60, 7, 20, 80, 32]
        ];
        function onClick(points, evt) {
            console.log(points, evt);
        }

        // Simulate async data update
        //$timeout(function () {
        //    vm.data = [
        //        [28, 48, 40, 19, 86, 27, 90],
        //        [65, 59, 80, 81, 56, 55, 40]
        //    ];
        //}, 3000);

        vm.labelsBar = ["January", "February", "March", "April", "May", "June", "July"];
        vm.seriesBar = ['TRACE', 'DEBUG','INFO','WARN','ERROR','SEVERE'];

        vm.dataBar = [
            [65, 59, 80, 81, 56, 55, 40],
            [28, 30, 40, 19, 86, 27, 90],
            [30, 70, 20, 12, 53, 30, 63],
            [50, 60, 53, 30, 100, 15, 78],
            [100, 66, 30, 40, 30, 40, 45],
            [70, 45, 60, 7, 20, 80, 32]
        ];

        vm.labelsPie = ['TRACE', 'DEBUG','INFO','WARN','ERROR','SEVERE'];
        vm.dataPie = [65, 59, 80, 81, 56, 55, 40];

    }
})();
