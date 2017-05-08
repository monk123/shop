/**
 * Created by Vadim Andreevich on 16.04.2017.
 */
'use strict';

angular.module('myApp').controller('RestController', ['$scope', 'ProductService', function($scope, ProductService) {
    var self = this;
    self.product = {id:null, name:'', description:'', price:'', photo:'', category:''};
    self.product = [];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;

    getAllEntities();
    
    function getAllEntities() {
        ProductService.getAllEntities()
            .then(
                function (d) {
                    self.products = d;
                },
                function (errResponse) {
                    console.error('Error Products')
                }
            )
    }
}]);