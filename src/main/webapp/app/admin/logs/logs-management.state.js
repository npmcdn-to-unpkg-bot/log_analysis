(function() {
    'use strict';

    angular
        .module('logsApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('logs', {
                parent: 'admin',
                url: '/logs',
                data: {
                    authorities: ['ROLE_ADMIN'],
                    pageTitle: 'logs.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/admin/logs/logs-management.html',
                        controller: 'LogsController',
                        controllerAs: 'vm'
                    }
                }

            });
    }
})();
