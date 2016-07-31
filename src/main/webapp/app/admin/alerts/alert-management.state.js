(function () {
    'use strict';

    angular
        .module('logsApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('alert-management', {
                parent: 'admin',
                url: '/alert-management',
                data: {
                    authorities: ['ROLE_ADMIN'],
                    pageTitle: 'alerts.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/admin/alerts/alert-management.html',
                        controller: 'AlertController',
                        controllerAs: 'vm'
                    }

                }
            })
            .state('alert-management-detail', {
                parent: 'admin',
                url: '/alert/:id',
                data: {
                    authorities: ['ROLE_ADMIN'],
                    pageTitle: 'alert-management.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/admin/alerts/alert-management-detail.html',
                        controller: 'AlertManagementDetailController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('user-management');
                        return $translate.refresh();
                    }]
                }
            })
            .state('alert-management.delete', {
                parent: 'alert-management',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_ADMIN']
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'app/admin/alerts/alert-management-delete-dialog.html',
                        controller: 'AlertManagementDeleteController',
                        controllerAs: 'vm',
                        size: 'md',
                        resolve: {
                            entity: ['ALertLog', function (ALertLog) {
                                console.log('The is is '+$stateParams.id)
                                return ALertLog.get({id: $stateParams.id});
                            }]
                        }
                    }).result.then(function () {
                            $state.go('alert-management', null, {reload: true});
                        }, function () {
                            $state.go('^');
                        });
                }]
            });
    }
})();
