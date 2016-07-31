(function () {
    'use strict';

    angular
        .module('logsApp')
        .factory('FileUpload', FileUpload);

    FileUpload.$inject = ['$http', '$q'];

    function FileUpload($http, $q) {
        var service = {
            upload: upload
        };

        return service;

        function upload(file) {

            var fd = new FormData();
            fd.append('file', file);
            return $http.post('/ws/logs/upload', fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            });
        }
    }
})();
