$(document).ready(function() {
    $('article main div.main_p span').click(function() {
        $('div.form').slideToggle(500);
    });
});

var app = angular.module('springDemo', ['ui.bootstrap']);

app.filter('beginning_data', function() {
    return function(input, begin) {
        if (input) {
            begin = +begin;
            return input.slice(begin);
        }
        return [];
    }
});
app.controller('AppCtrl', function($scope, $http, $timeout) {

    $http({
        method: 'GET',
        url: '/rest/judgements'})
        .then(function(response){
            $scope.file=response.data;
            $scope.current_grid = 1;
            $scope.data_limit = 40;
            $scope.filter_data = $scope.file.length;
            $scope.entire_user = $scope.file.length;
        });

    $scope.page_position = function(page_number) {
        $scope.current_grid = page_number;
    };
    $scope.filter = function() {
        $timeout(function() {
            $scope.filter_data = $scope.searched.length;
        }, 20);
    };
    $scope.sort_with = function(base) {
        $scope.base = base;
        $scope.reverse = !$scope.reverse;
    };
});