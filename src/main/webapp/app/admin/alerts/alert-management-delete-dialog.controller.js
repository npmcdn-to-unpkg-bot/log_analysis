(function() {
    'use strict';

    angular
        .module('logsApp')
        .controller('AlertManagementDeleteController', AlertManagementDeleteController);

    AlertManagementDeleteController.$inject = ['$uibModalInstance', 'entity', 'ALertLog'];

    function AlertManagementDeleteController ($uibModalInstance, entity, ALertLog) {
        var vm = this;

        vm.alert = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            ALertLog.delete({id: vm.alert.id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
