(function () {
    'use strict'

    angular.module('com.module.tree')
        .controller('NodeRemovalController', NodeRemovalController);

    NodeRemovalController.$inject = ['node', 'NodeService', '$scope', '$rootScope', '$uibModalInstance'];
    function NodeRemovalController(node, NodeService, $scope, $rootScope, $uibModalInstance) {

        var _self = this;

        _self.currentNode = node;

        _self.alerts = [];
        _self.addAlert = function(type, msg) {
            _self.alerts = [];
            _self.alerts.push({type: type, msg: msg});
        };
        _self.closeAlert = function(index) {
            _self.alerts.splice(index, 1);
        };

        _self.remove = function () {
            NodeService.remove(_self.currentNode.id, function (response) {
                if (response.success) {
                    $uibModalInstance.close();
                }
                else {
                    _self.addAlert('danger', 'Ops! ' + response.errorMsg);
                }
            });
        };

        _self.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
       
    }

})();