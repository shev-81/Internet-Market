angular.module('app', []).controller('indexController', function ($scope, $http) {

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

    $scope.createProduct = function(){
//    console.log($scope.newProduct);
        $http.post(contextPath + '/products', $scope.newProduct
                ).then(function (response) {
                     $scope.loadProducts();
                });
    }

    $scope.deleteProduct = function (productId) {
        $http.delete(contextPath + '/products/' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.addProductInCart = function (productId){
        // console.log(productId);
        $http.get(contextPath + '/products/cart/' + productId)
            .then(function (response) {
                console.log((response.data));
                $scope.ProductCartList = response.data;
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