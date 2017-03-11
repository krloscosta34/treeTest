(function () {
    'use strict'

    angular.module('com.module.tree')
        .controller('TreeController', TreeController);

    TreeController.$inject = ['NodeService', /*'LocatorHelpers',*/ '$scope', '$rootScope', '$uibModal', 'toaster', 'CORE_CONFIG'];
    function TreeController(NodeService, /*LocatorHelpers,*/$scope, $rootScope, $uibModal, toaster, CORE_CONFIG) {

        var _self = this;

        _self.foldersList = [];

        // folder
        _self.filterFolder = new Object();
        _self.filterFolder.name = '';
        _self.filterFolder.count = 0;
        _self.filterFolder.startRow = 1;
        _self.filterFolder.pageSize = 30;
        _self.filterFolder.orderBy = {"field" : "name", "type" : "ASC"};
        _self.filterFolder.fatherId = null,
        _self.filterFolder.dontFindParent = false,
        _self.folderLoader = false;

        _self.init = function () {
            //_self.loadFolders();
            NodeService.findByAll(function(data) {
                console.log(data);
            });
        };

        _self.loadFolders = function () {

            NodeService.findByFilter(_self.filterFolder, function (data) {

                data.data.forEach(function (folder, idx) {
                    folder.children = [];
                });

                if (_self.foldersList.length >= 1) {
                    _self.foldersList = _self.foldersList.concat(data.data);
                } else {

                    _self.foldersList = data.data;
                }

                _self.setStartNode(_self.foldersList);

                _self.folderLoader = false;
            });
        }

        _self.openFolderConfiguration = function (node) {

            var modalInstance = $uibModal.open({
                windowClass: 'folder-editing-modal',
                templateUrl: locatorHelpers.baseModulePath('ged/submodule/modal/submodule/folder/views/folder.configuration.modal.view.html'),
                controller: 'FolderConfigurationController as folderConfigCtrl',
                size: 'lg',
                resolve: {
                    folder: function () {
                        return angular.copy(node);
                    }
                }
            });

            modalInstance.result.then(function (result) {
                if (result != null) {
                    _self.changeFolder = true;
                    for(var key in result) {
                        if(key != "$$hashKey") {
                            node[key] = result[key];
                        }
                    }
                    _self.update(node);
                }
            });

            return false;

        };

        _self.init();

    }

})();