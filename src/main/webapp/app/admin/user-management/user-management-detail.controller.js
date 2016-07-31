(function() {
    'use strict';

    angular
        .module('logsApp')
        .controller('UserManagementDetailController', UserManagementDetailController);

    UserManagementDetailController.$inject = ['$stateParams', 'User'];

    function UserManagementDetailController ($stateParams, User) {
        var vm = this;

        vm.load = load;
        vm.user = {};

        vm.load($stateParams.id);

        function load (id) {
            User.getUser({id: id}, function(result) {
                vm.user = result;
            });
        }
    }
})();
