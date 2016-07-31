(function () {
    'use strict';

    angular
        .module('logsApp')
        .controller('LogsController', LogsController);

    LogsController.$inject = ['FileUpload', 'AlertService'];

    function LogsController(FileUpload, AlertService) {
        var vm = this;
        vm.file = null;
        vm.upload = upload;


        function upload() {
            var file = vm.file;
            console.log('file is ' + file);
            console.dir(file);
            var result = FileUpload.upload(file).then(function (data) {
                    AlertService.success('The file has been added successfully \n there  is a ' + data.data.count + ' alerts found');
                    vm.file = null;
                },
                function (error) {
                    AlertService.error('Unexpected problem happened please verify the format of the file ');
                });


        }

    }
})();
