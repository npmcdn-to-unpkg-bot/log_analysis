(function () {
    'use strict';

    angular
        .module('logsApp')
        .factory('User', User);

    User.$inject = ['$resource'];

    function User($resource) {
        var service = $resource('ws/user/:action', {}, {
            'query': {method: 'GET', isArray: true},
            'list': {
                method: 'GET',
                url: 'ws/user/list',
                isArray: true,

                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'save': {
                method: 'POST',
                'url': '/ws/user/create'
            },
            'getUser': {
                method: 'GET',
                url: 'ws/user/get',
                isArray: false
            },
            'update': {
                method: 'PUT',
                url: 'ws/user/update'
            },
            'delete': {method: 'DELETE',
            url :'ws/user/delete'}
        });

        return service;
    }
})();
