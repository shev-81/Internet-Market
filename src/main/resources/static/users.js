angular.module('app2', []).controller('usersController', function ($scope, $http) {

    const contextPath = 'http://localhost:8189/app/api/v1/users';

    $scope.loadUsers = function () {
        $http.get(contextPath + '/all'
        ).then(function (response) {
            $scope.usersList = response.data;
        });
    };

//     $scope.createProduct = function(){
// //    console.log($scope.newProduct);
//         $http.post(contextPath + '/products', $scope.newProduct
//         ).then(function (response) {
//             $scope.loadProducts();
//         });
//     }
//
    $scope.deleteUser = function (userId) {
        $http.delete(contextPath + '/' + userId)
            .then(function (response) {
                $scope.loadUsers();
            });
    }
//
//     $scope.addProductInCart = function (productId){
//         // console.log(productId);
//         $http.get(contextPath + '/products/cart/' + productId)
//             .then(function (response) {
//                 console.log((response.data));
//                 $scope.ProductCartList = response.data;
//             });
//     }
//
//     $scope.changePrice = function (productId, delta) {
//         $http({
//             url: contextPath + '/products/change_price',
//             method: 'GET',
//             params: {
//                 productId: productId,
//                 delta: delta
//             }
//         }).then(function (response) {
//             $scope.loadProducts();
//         });
//     }

     $scope.loadUsers();      //  запуск функции при загрузке страницы
});