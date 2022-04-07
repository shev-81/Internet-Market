angular.module('app', []).controller('indexController', function ($scope, $http) {

    const contextPath = 'http://localhost:8189/app';

    $scope.loadProducts = function () {
        $http.get(contextPath + '/products')
            .then(function (response) {
                $scope.ProductList = response.data;
            });
    };

    $scope.createProduct = function(){
//    console.log($scope.newProduct);
        $http.post(contextPath + '/products', $scope.newProduct
                ).then(function (response) {
                     $scope.loadProducts();
                });
    }

    $scope.filterProduct = function(){
        $http({
            url: contextPath + '/products/betweenprice',
            method: 'GET',
            params: {
                priceOne: $scope.newBetween.priceOne,
                priceTwo: $scope.newBetween.priceTwo
            }
            }).then(function (response) {
                $scope.ProductList = response.data;
            });
    }

    $scope.lowPriceProducts = function (price) {
        $http.get(contextPath + '/products/low/' + price
            ).then(function (response) {
                $scope.ProductList = response.data;
            });
    }

    $scope.highPriceProducts = function (price) {
        $http.get(contextPath + '/products/hi/' + price
            ).then(function (response) {
                $scope.ProductList = response.data;
            });
    }

    $scope.betweenPriceProducts = function (priceOne, priceTwo) {
        $http({
            url: contextPath + '/products/between',
            method: 'GET',
            params: {
                priceOne: priceOne,
                priceTwo: priceTwo
            }
        }).then(function (response) {
            $scope.ProductList = response.data;
        });
    }

    $scope.deleteProduct = function (productId) {
        $http.get(contextPath + '/products/delete/' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.changePrice = function (productId, delta) {
        $http({
            url: contextPath + '/products/change_price',
            method: 'GET',
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadProducts();
        });
    }

    $scope.loadProducts();      //  запуск функции при загрузке страницы
});