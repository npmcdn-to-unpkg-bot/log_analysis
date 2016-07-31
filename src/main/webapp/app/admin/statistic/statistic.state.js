(function() {
    'use strict';

    angular
        .module('logsApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('statistic', {
                parent: 'admin',
                url: '/statistic',
                data: {
                    authorities: ['ROLE_ADMIN'],
                    pageTitle: 'statistic.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/admin/statistic/statistic.html',
                        controller: 'StatisticController',
                        controllerAs: 'vm'
                    }
                }

            });
    }
})();
