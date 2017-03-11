(function () {
    'use strict'

    angular.module('com.module.tree')
        .controller('TreeController', TreeController);

    TreeController.$inject = ['NodeService', /*'LocatorHelpers',*/ '$scope', '$rootScope', '$uibModal', 'toaster', 'CORE_CONFIG'];
    function TreeController(NodeService, /*LocatorHelpers,*/$scope, $rootScope, $uibModal, toaster, CORE_CONFIG) {

        var _self = this;

        _self.nodeList = [
            {
                id: 1,
                description: "Asgard",
                children: [
                    {
                        id: 2,
                        description: "Boston",
                        children: [
                            {
                                id: 3,
                                description: "Central City",
                                children: [
                                    {
                                        id: 4,
                                        description: "Divinópolis",
                                        children: []
                                    }
                                ]
                            }
                        ]
                    },
                    {
                        id: 5,
                        description: "Egito",
                        children: []
                    },
                    {
                        id: 6,
                        description: "Far Far Way",
                        children: []
                    }
                ]
            }
        ];
        _self.activeNode = null;

        // folder
        _self.filterFolder = new Object();
        _self.filterFolder.name = '';
        _self.filterFolder.count = 0;
        _self.filterFolder.startRow = 1;
        _self.filterFolder.pageSize = 30;
        _self.filterFolder.orderBy = {"field": "name", "type": "ASC"};
        _self.filterFolder.fatherId = null,
            _self.filterFolder.dontFindParent = false,
            _self.folderLoader = false;

        _self.init = function () {
            //_self.loadFolders();
            NodeService.findByAll(function (data) {
                console.log(data);
            });
        };

        _self.loadFolders = function () {

            NodeService.findByFilter(_self.filterFolder, function (data) {

                data.data.forEach(function (folder, idx) {
                    folder.children = [];
                });

                if (_self.nodeList.length >= 1) {
                    _self.nodeList = _self.nodeList.concat(data.data);
                } else {

                    _self.nodeList = data.data;
                }

                _self.setStartNode(_self.nodeList);

                _self.folderLoader = false;
            });
        };

        _self.openEditionModal = function () {
            
            var node = _self.activeNode;
            var modalInstance = $uibModal.open({
                windowClass: 'node-editing-modal',
                templateUrl: 'angular/tree/views/node.edit.view.html',
                controller: 'NodeEditionController as nodeEditionCtrl',
                resolve: {
                    node: function () {
                        return angular.copy(node);
                    }
                }
            });

            modalInstance.result.then(function (result) {
                if (result != null) {
                    _self.changeFolder = true;
                    for (var key in result) {
                        if (key != "$$hashKey") {
                            node[key] = result[key];
                        }
                    }
                    _self.update(node);
                }
            });

            return false;

        };

        _self.isNodeActive = function (node) {
            if (node == null && node == undefined)
                return false;
            if (_self.activeNode == null && _self.activeNode == undefined)
                return false;
            return _self.activeNode.id == node.id;
        };

        _self.setActiveNode = function (node) {
            if (node == null && node == undefined)
                return;
            _self.activeNode = node;
            console.log("HEY");
        };
        
        _self.ttt = function (node) {
            console.log("WOW");
        };

        _self.init();

    }

})();