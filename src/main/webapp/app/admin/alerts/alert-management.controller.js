(function() {
    'use strict';

    angular
        .module('logsApp')
        .controller('AlertController', AlertController);

    AlertController.$inject = ['Principal', 'ALertLog', 'ParseLinks', 'paginationConstants', '$filter'];

    function AlertController(Principal, ALertLog, ParseLinks, paginationConstants, $filter) {
        var vm = this;

        vm.currentAccount = null;
        vm.links = null;
        vm.loadAll = loadAll;
        vm.loadPage = loadPage;
        vm.page = 1;
        vm.totalItems = null;
        vm.alerts = [];
        vm.dateStart = null ;
        vm.dateEnd = null ;
        vm.dateOptions = {
            changeYear: true,
            changeMonth: true,
            yearRange: '1900:-0',
            dateFormat : 'dd-MMMM-yyyy'
        };
        vm.error = {
            isError : false ,
            message: ''
        } ;

        vm.getAlert = getAlert  ;


        //vm.loadAll();


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

        function getAlert() {

            if(vm.dateStart > vm.dateEnd){
                vm.error.isError = true;
                vm.error.message  = 'The start date  has to be strictly lower than date end';
                return ;
            }

            var start = $filter('date')(vm.dateStart,'yyyy-MM-dd');
            var end = $filter('date')(vm.dateEnd,'yyyy-MM-dd');

            ALertLog.listByDate({page: vm.page - 1, size: paginationConstants.itemsPerPage,start:start, end:end}, function (result, headers) {
                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                vm.alerts = result;
                console.log('The alerts is '+JSON.stringify(result)) ;
            });

        }


    }
})();
