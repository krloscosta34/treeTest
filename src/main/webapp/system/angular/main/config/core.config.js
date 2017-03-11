(function() {
    'use strict';

    angular
        .module('com.system.core')
        .config(coreConfig);

    coreConfig.$inject = ['RestangularProvider', 'CORE_CONFIG'];
    function coreConfig(RestangularProvider, CORE_CONFIG) {

        // Custom Route definition
        RestangularProvider.setBaseUrl(CORE_CONFIG.contextRoot + '/rest/');
        RestangularProvider.setDefaultHttpFields({cache: false});
        RestangularProvider.setDefaultHeaders({'Content-Type': 'application/json'});

    }
})();