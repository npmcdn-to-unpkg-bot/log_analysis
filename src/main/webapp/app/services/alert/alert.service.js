(function () {
    'use strict';

    angular
        .module('logsApp')
        .factory('ALertLog', ALertLog);

    ALertLog.$inject = ['$resource'];

    function ALertLog($resource) {
        var service = $resource('ws/alert/:action', {}, {
            'list': {
                method: 'GET',
                url: 'ws/alert/list',
                isArray: true,

                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'listByDate': {
                method: 'GET',
                url: 'ws/alert/list/date',
                isArray: true,

                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'stat': {
                method: 'GET',
                url: 'ws/alert/statistic'
            },

            'get': {
                method: 'GET',
                url: 'ws/alert/get'
            },
            'delete': {
                method: 'DELETE',
                url: 'ws/alert/delete'
            }
        });

        return service;
    }
})();
