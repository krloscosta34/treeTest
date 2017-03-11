(function () {
    'use strict';

    angular.module('com.module.tree')
        .service('NodeService', NodeService);

    NodeService.$inject = ['Restangular'];
    function NodeService(Restangular) {

        var _self = this;
        _self.entityService = Restangular.all("/entity/node/");

        this.findByAll = function (findByFilterSuccess) {
            _self.entityService.customGET("findAll").then(function (response) {
                findByFilterSuccess(response);
            });

        };

        this.findByFilter = function (filter, findByFilterSuccess) {
            _self.entityService.customGET("findByFilter", filter).then(function (response) {
                findByFilterSuccess(response);
            });

        };

        this.findById = function (neoId, findByIdSuccess) {
            _self.entityService.customGET("findById/" + neoId).then(function (response) {
                findByIdSuccess(response);
            });

        };

        this.save = function (entity, saveSuccess) {
            _self.entityService.customPOST(entity, "save").then(function (response) {
                saveSuccess(response);
            });
        };

        this.update = function (neoId, entity, updateSuccess) {
            _self.entityService.customPUT(entity, "update/" + neoId).then(function (response) {
                updateSuccess(response);
            });
        };

        this.remove = function (neoId, removeSucess) {
            _self.entityService.customDELETE("remove/" + neoId).then(function (response) {
                removeSucess(response);
            });
        };

        this.moveFolder = function (neoId, neoIdNewParent, moveSuccess, moveFailure) {
            _self.entityService.customPUT({}, "move/" + neoId + "/" + neoIdNewParent).then(function (response) {
                moveSuccess(response);
            }, function (response) {
                moveFailure(response);
            });
        };

    }
})
();