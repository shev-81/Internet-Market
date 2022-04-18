angular.module('app2', ['ngStorage']).controller('usersController', function ($scope, $http, $localStorage, $rootScope) {

    const contextPath = 'http://localhost:8189/app/api/v1/users';

    if ($localStorage.springWebUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springWebUser.token;
    }

    $scope.tryToAuth = function () {
        $http.post('http://localhost:8189/app/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.clearUser = function () {
        delete $localStorage.springWebUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.springWebUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.showCurrentUserInfo = function () {
        $http.get('http://localhost:8189/app/api/v1/profile')
            .then(function successCallback(response) {
                alert('MY NAME IS: ' + response.data.username);
            }, function errorCallback(response) {
                alert('UNAUTHORIZED');
            });
    }

    $scope.loadUsers = function () {
        $http.get(contextPath
        ).then(function (response) {
            $scope.usersList = response.data;
        });
    };

    $scope.createUser = function (){
       // console.log($scope.newUser.name);
    $http.post(contextPath + '/new', $scope.newUser
    ).then(function (response) {
        $scope.loadUsers();
    });
}



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