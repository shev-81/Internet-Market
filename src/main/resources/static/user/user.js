angular.module('market-front').controller('userController', function ($scope, $http, $localStorage, $rootScope) {
    const contextPath = 'http://localhost:8189/app/api/v1/user';

    $scope.showCurrentUserInfo = function () {
        $http.get(contextPath + '/profile')
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

    $scope.deleteUser = function (userId) {
        $http.delete(contextPath + '/' + userId)
            .then(function (response) {
                $scope.loadUsers();
            });
    }
    $scope.loadUsers();      //  запуск функции при загрузке страницы
});