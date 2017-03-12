(function () {
    'use strict'

    angular.module('com.module.tree')
        .controller('NodeEditionController', NodeEditionController);

    NodeEditionController.$inject = ['mode', 'node', 'parentId', 'NodeService', '$scope', '$rootScope', '$uibModalInstance'];
    function NodeEditionController(mode, node, parentId, NodeService, $scope, $rootScope, $uibModalInstance) {

        var _self = this;

        _self.mode = mode;
        _self.currentNode = node;
        if(parentId == undefined || parentId == null)
            parentId = -1;
        _self.parentId = parentId;

        _self.alerts = [];
        _self.addAlert = function(type, msg) {
            _self.alerts = [];
            _self.alerts.push({type: type, msg: msg});
        };
        _self.closeAlert = function(index) {
            _self.alerts.splice(index, 1);
        };

        _self.save = function () {
            _self.nodeForm.$submitted = true;
            if (_self.nodeForm.$valid) {
                if(_self.mode == 'new') {
                    NodeService.create(_self.currentNode, _self.parentId, function (response) {
                        if (response.success) {
                            // _self.currentNode.id = response.entity.id;    
                            _self.currentNode.id = response.entity.value;
                            $uibModalInstance.close(_self.currentNode);
                        }
                        else {
                            _self.addAlert('danger', 'Ops! ' + response.errorMsg);
                        }
                    });
                }
                else if(_self.mode == 'edit') {
                    NodeService.update(_self.currentNode.id, _self.currentNode, function (response) {
                        if (response.success) {
                            $uibModalInstance.close(_self.currentNode);
                        }
                        else {
                            _self.addAlert('danger', 'Ops! ' + response.errorMsg);
                        }
                    });
                }
                else {
                    _self.addAlert('danger', 'Ops! Não foi possível realizar a comunicação com o servidor.');
                }
            }
        };

        _self.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
       
    }

})();