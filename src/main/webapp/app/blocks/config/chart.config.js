(function() {
    'use strict';

    angular
        .module('logsApp')
        .config(chartConfig);

    chartConfig.$inject = ['ChartJsProvider'];

    function chartConfig(ChartJsProvider) {
        //
        //ChartJsProvider.setOptions({
        //    chartColors: ['#FF5252', '#FF8A80'],
        //    responsive: false
        //});
        //// Configure all line charts
        //ChartJsProvider.setOptions('line', {
        //    showLines: false
        //});
    }
})();
