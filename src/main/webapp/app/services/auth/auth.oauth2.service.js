(function() {
    /*jshint camelcase: false */
    'use strict';

    angular
        .module('logsApp')
        .factory('AuthServerProvider', AuthServerProvider);

    AuthServerProvider.$inject = ['$http', '$localStorage', 'Base64'];

    function AuthServerProvider ($http, $localStorage, Base64) {
        var service = {
            getToken: getToken,
            hasValidToken: hasValidToken,
            login: login,
            logout: logout
        };

        return service;

        function getToken () {
            return $localStorage.authenticationToken;
        }

        function hasValidToken () {
            var token = this.getToken();
            return token && token.expires_at && token.expires_at > new Date().getTime();
        }

        function login (credentials) {
            var data = '?username=' +  encodeURIComponent(credentials.username) + '&password=' +
                encodeURIComponent(credentials.password) ;

            return $http.get('/ws/user/authenticate'+data, {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'Accept': 'application/json',
                    'Authorization': 'Basic ' + Base64.encode('logsApp' + ':' + 'mySecretOAuthSecret')
                }
            }).success(authSucess);

            function authSucess (response) {
                var expiredAt = new Date();
                expiredAt.setSeconds(expiredAt.getSeconds() + response.expires_in);
                response.expires_at = expiredAt.getTime();
                $localStorage.authenticationToken = response;
                return response;
            }
        }

        function logout () {
            $http.post('ws/logout').then(function() {
                delete $localStorage.authenticationToken;
            });
        }
    }
})();
