(function () {
    'use strict';

    angular.module('com.module.tree')
        .service('NodeService', NodeService);

    NodeService.$inject = ['Restangular'];
    function NodeService(Restangular) {

        var _self = this;
        _self.entityService = Restangular.all("/entity/node/");

        this.findByAll = function (successFn) {
            _self.entityService.customGET("findAll").then(function (response) {
                successFn(response);
            });
        };

        this.findByFilter = function (filter, successFn) {
            _self.entityService.customGET("findByFilter", filter).then(function (response) {
                successFn(response);
            });
        };

        this.findById = function (id, successFn) {
            _self.entityService.customGET("findById/" + id).then(function (response) {
                successFn(response);
            });
        };

        this.create = function (entity, parentId, successFn) {
            _self.entityService.customPOST(entity, "create/" + parentId).then(function (response) {
                successFn(response);
            });
        };

        this.update = function (id, entity, successFn) {
            _self.entityService.customPUT(entity, "update/" + id).then(function (response) {
                successFn(response);
            });
        };

        this.remove = function (id, successFn) {
            _self.entityService.customDELETE("remove/" + id).then(function (response) {
                successFn(response);
            });
        };

    }
})
();