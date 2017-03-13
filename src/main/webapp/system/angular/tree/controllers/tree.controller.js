(function () {
    'use strict'

    angular
        .module('com.module.tree')
        .controller('TreeController', TreeController);

    TreeController.$inject = ['NodeService', /*'LocatorHelpers',*/ '$scope', '$rootScope', '$uibModal', 'toaster', 'CORE_CONFIG'];
    function TreeController(NodeService, /*LocatorHelpers,*/$scope, $rootScope, $uibModal, toaster, CORE_CONFIG) {

        var _self = this;

        _self.nodeList = [];
        _self.activeNode = null;
        _self.searchTerm = '';
        _self.loadingNodes = true;

        _self.initCtrl = function () {
            _self.initNodes();
        };
        
        _self.initNodes = function () {
            _self.activeNode = null;
            _self.loadingNodes = true;
            NodeService.findRootNodes(function (data) {
                _self.nodeList = data;
                _self.loadingNodes = false;
            });
        };

        _self.findByDescription = function () {
            
            if(_self.searchTerm.trim() == '') {
                _self.initNodes();
                return;
            }

            _self.activeNode = null;
            _self.loadingNodes = true;
            NodeService.findByDescription(_self.searchTerm, function (data) {
                _self.nodeList = data;
                _self.loadingNodes = false;
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
                            if(_self.activeNode.children == null || _self.activeNode.children == undefined)
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
                    _self.removeNodeFromTree(_self.activeNode, _self.nodeList);
                });
            }
        };

        _self.removeNodeFromTree = function (node, list) {
            for(var i=0; i< list.length; i++){
                if(list[i].id == node.id){
                    list.splice(i, 1);
                    return true;
                }
                if(list[i].children != undefined && list[i].children != null && list[i].children.length > 0)
                {
                    var r = _self.removeNodeFromTree(node, list[i].children);
                    if(r) {
                        return true;
                    }
                }
            }
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
            _self.activeNode = node;
        };
        
        _self.hasChildren = function (node) {
            return node.children != undefined && node.children != null && node.children.length > 0;
        };

        _self.getTooltipMsg = function (node) {
            var msg = '';
            msg += 'Código: ' + node.code + ' | ';
            msg += 'Descrição: ' + node.description + ' | ';
            msg += 'Observação: ' + (node.note != undefined && node.note != null ? node.note : "-");
            return msg;
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