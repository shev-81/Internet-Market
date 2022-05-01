angular.module('market-front').controller('storeController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/app/api/v1';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                // p: $scope.filter ? $scope.filter.p : null,
                p: pageIndex,
                name_category: $scope.filter ? $scope.filter.name_category : null,
                name_part: $scope.filter ? $scope.filter.name_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.ProductList = response.data.content;
            $scope.ProductPage = response.data;
            $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.ProductPage.totalPages);
        });
    };

    $scope.categoryProducts = function (categoryName) {
        let pageIndex = 1;
        // console.log(categoryName+ " категория");
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                p: pageIndex,
                name_category: categoryName,
                name_part: $scope.filter ? $scope.filter.name_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.ProductList = response.data.content;
            $scope.ProductPage = response.data;
            $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.ProductPage.totalPages);
        });
    };

    $scope.loadCategory = function (){
        // console.log("загружаем категории");
        $http.get(contextPath + '/categories')
            .then(function (response) {
                $scope.CategoryList = response.data;
            });
    }



    $scope.addToCart = function (productId) {
        // console.log("Добавлен продукт");
        $http.get(contextPath + '/cart/'+ $localStorage.springWebGuestCartId + '/add/' + productId)
            .then(function (response) {
            });
    }

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }
    $scope.loadCategory();
    $scope.loadProducts();
});