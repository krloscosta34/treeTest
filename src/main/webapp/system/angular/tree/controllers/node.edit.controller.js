(function () {
    'use strict'

    angular.module('com.module.tree')
        .controller('NodeEditionController', NodeEditionController);

    NodeEditionController.$inject = ['node', 'NodeService', '$scope', '$rootScope', '$uibModalInstance'];
    function NodeEditionController(node, NodeService, $scope, $rootScope, $uibModalInstance) {

        var _self = this;

        _self.currentNode = node;

        this.save = function () {
            this.nodeForm.$submitted = true;
            if (this.nodeForm.$valid) {
                $uibModalInstance.close(_self.currentNode);
            }
        };

        this.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
       
    }

})();