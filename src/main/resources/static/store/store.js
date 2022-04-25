angular.module('market-front').controller('storeController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/app/api/v1';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                p: $scope.filter ? $scope.filter.p : null,
                name_part: $scope.filter ? $scope.filter.name_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.ProductList = response.data.content;
        });
    };

    $scope.addToCart = function (productId) {
        // console.log("Добавлен продукт");
        $http.get(contextPath + '/cart/add/' + productId)
            .then(function (response) {
            });
    }

    $scope.loadOrders = function () {
        $http.get(contextPath + '/orders')
            .then(function (response) {
                $scope.MyOrders = response.data;
            });
    }

    $scope.loadProducts();
    $scope.loadOrders();
});