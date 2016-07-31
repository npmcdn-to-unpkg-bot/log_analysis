(function() {
    'use strict';

    angular
        .module('logsApp')
        .factory('Account', Account);

    Account.$inject = ['$resource'];

    function Account ($resource) {
        var service = $resource('/ws/user/account', {}, {
            'get': { method: 'GET', params: {}, isArray: false,
                interceptor: {
                    response: function(response) {
                        console.log('the current user is '+JSON.stringify(response.data)) ;
                        return response;
                    }
                }
            }
        });

        return service;
    }
})();
