(function() {
    'use strict';

    angular
        .module('logsApp')
        .controller('AlertController', AlertController);

    AlertController.$inject = ['Principal', 'ALertLog', 'ParseLinks', 'paginationConstants'];

    function AlertController(Principal, ALertLog, ParseLinks, paginationConstants) {
        var vm = this;

        vm.currentAccount = null;
        vm.links = null;
        vm.loadAll = loadAll;
        vm.loadPage = loadPage;
        vm.page = 1;
        vm.totalItems = null;
        vm.alerts = [];


        vm.loadAll();


        Principal.identity().then(function(account) {
            vm.currentAccount = account;
        });


        function loadAll () {
            ALertLog.list({page: vm.page - 1, size: paginationConstants.itemsPerPage}, function (result, headers) {
                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                vm.alerts = result;
            });
        }

        function loadPage (page) {
            vm.page = page;
            vm.loadAll();
        }


    }
})();
