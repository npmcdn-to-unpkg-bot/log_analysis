(function () {
    'use strict';

    angular
        .module('logsApp')

        /*
         Languages codes are ISO_639-1 codes, see http://en.wikipedia.org/wiki/List_of_ISO_639-1_codes
         They are written in English to avoid character encoding issues (not a perfect solution)
         */
        .constant('LANGUAGES', [
            'en'
            // logsApp-needle-i18n-language-constant - logsApp will add/remove languages in this array
        ]
    );
})();
