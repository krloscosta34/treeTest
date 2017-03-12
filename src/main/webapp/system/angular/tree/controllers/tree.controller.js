(function () {
    'use strict'

    angular.module('com.module.tree')
        .controller('TreeController', TreeController);

    TreeController.$inject = ['NodeService', /*'LocatorHelpers',*/ '$scope', '$rootScope', '$uibModal', 'toaster', 'CORE_CONFIG'];
    function TreeController(NodeService, /*LocatorHelpers,*/$scope, $rootScope, $uibModal, toaster, CORE_CONFIG) {

        var _self = this;

        _self.nodeList = [];
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

        _self.initCtrl = function () {
            NodeService.findByAll(function (data) {
                _self.nodeList = data;
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

        _self.createNode = function () {
            _self.openEditionModal(
                {
                    mode: 'new',
                    node: {},
                    parentId: _self.activeNode != null ? _self.activeNode.id : undefined,
                    callback: function (node) {
                        if(_self.activeNode != null) {
                            if(_self.activeNode.children == null || _self.activeNode.children != undefined)
                                _self.activeNode.children = [];
                            _self.activeNode.children.push(node);
                        }
                        else {
                            _self.nodeList.push(node);
                        }
                    }
                });
        };

        _self.editNode = function () {
            if(_self.activeNode == null)
                return;
            _self.openEditionModal(
                {
                    mode: 'edit',
                    node: _self.activeNode,
                    callback: function (node) {
                        angular.merge(_self.activeNode, node);
                    }
                });
        };

        _self.openEditionModal = function (params) {

            var modalInstance = $uibModal.open({
                windowClass: 'node-editing-modal',
                templateUrl: 'angular/tree/views/node.edit.view.html',
                controller: 'NodeEditionController as nodeEditionCtrl',
                resolve: {
                    mode: function () {
                        return params.mode;
                    },
                    node: function () {
                        return angular.copy(params.node);
                    },
                    parentId: function () {
                        return params.parentId;
                    }
                }
            });
            modalInstance.result.then(function (result) {
                if(params.callback != undefined){
                    params.callback(result);
                }
            });
        };

        _self.removeNode = function () {
            if(_self.activeNode != null){
                var modalInstance = $uibModal.open({
                    windowClass: 'node-editing-modal',
                    templateUrl: 'angular/tree/views/node.remove.view.html',
                    controller: 'NodeRemovalController as nodeRemovalCtrl',
                    resolve: {
                        node: function () {
                            return angular.copy(_self.activeNode);
                        }
                    }
                });
                modalInstance.result.then(function () {
                    //ACHAR E REMOVER NÓ??
                    delete _self.activeNode;
                });
            }
        };

        _self.isNodeActive = function (node) {
            if (node == null && node == undefined)
                return false;
            if (_self.activeNode == null && _self.activeNode == undefined)
                return false;
            return _self.activeNode.id == node.id;
        };

        _self.setActiveNode = function (node) {
            _self.activeNode = node;
        };
        
        _self.hasChildren = function (node) {
            return node.children != undefined && node.children != null && node.children.length > 0;
        };

        /*************************************** ANGULAR-UI-TREE ***************************************/
        _self.collapseAll = function () {
            _self.setActiveNode(null);
            $scope.$broadcast('angular-ui-tree:collapse-all');
        };
        _self.expandAll = function () {
            _self.setActiveNode(null);
            $scope.$broadcast('angular-ui-tree:expand-all');
        };
        _self.scope = $scope;
        _self.resetEmptyElement = function () {
            if ((!$scope.$nodesScope.$modelValue || $scope.$nodesScope.$modelValue.length === 0) &&
                $scope.emptyPlaceholderEnabled) {
                $element.append($scope.$emptyElm);
            } else {
                $scope.$emptyElm.remove();
            }
        };
        $scope.resetEmptyElement = _self.resetEmptyElement;
        /***********************************************************************************************/

    }

})();