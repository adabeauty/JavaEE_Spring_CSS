'use strict';

angular.module('users_management')
    .controller('usersController', function($scope, $http){

        $http.get('/web/api/users').success(function(users){
            $scope.users = users;
        });
    });