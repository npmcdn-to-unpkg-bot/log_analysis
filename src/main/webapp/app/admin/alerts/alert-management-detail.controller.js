(function() {
    'use strict';

    angular
        .module('logsApp')
        .controller('AlertManagementDetailController', AlertManagementDetailController);

    AlertManagementDetailController.$inject = ['$stateParams', 'ALertLog'];

    function AlertManagementDetailController ($stateParams, ALertLog) {
        var vm = this;

        vm.load = load;
        vm.alert = {};

        vm.load($stateParams.id);

        function load (id) {
            ALertLog.get({id: id}, function(result) {
                vm.alert = result;
            });
        }
    }
})();
