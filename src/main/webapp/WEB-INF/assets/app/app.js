'use strict';

var app = angular.module('app', ['ngRoute', 'dndLists']);

app.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            when('/', {
                templateUrl: 'home.html',
                controller: 'HomeController'
            }).
            when('/user', {
                templateUrl: 'user.html',
                controller: 'UserController'
            }).
            when('/admin', {
                templateUrl: 'admin.html',
                controller: 'AdminController'
            }).
            otherwise({redirectTo:'/'});
    }]);

app.controller('HomeController',
    function($scope, $routeParams, $http, $location) {

    }
);

app.controller('UserController',
    function($scope, $routeParams, $http, $location) {

    }
);

app.controller('AdminController',
    function($scope, $routeParams, $http, $location) {

    }
);

app.controller('postcontroller', function($scope, $http){
    $scope.submitForm = function(){
        var url = "http://localhost:8080/postinstruction";
        var config = {
            headers : {
                'Content-Type': 'application/json;charset=utf-8;'
            }};
        var data = {textinfo: $scope.textinfo};

        $http.post(url, data, config).then(function(response){
            $scope.postResultMessage = "Successful!";
        }, function(response){
            $scope.postResultMessage = "Fail!";
        });
        $scope.textinfo = "";
    }
});

app.controller('getcontroller', function($scope, $http){
    $scope.getTextInfo = function(){
        var url = "http://localhost:8080/getinstruction";
        var config = {
            headers : {
                'Content-Type': 'application/json;charset=utf-8;'
            }};
        $http.get(url, config).then(function(response) {
            $scope.response = response.data
        }, function(response){
            $scope.getResultMessage = "Fail!";
        });
    }
});

app.controller("DnDController", function($scope) {
        $scope.models = {
            selected: null,
            lists: {"FieldsList": []},
            templates: [
                {type: "Text field", id: 1, message: ""},
                {type: "Media field", id: 2}
            ],
            wholemessage: "Message: "
        };

        $scope.models.lists.FieldsList.push({type: "Text field", id: 3, message: "Example field. Replace it"});


        $scope.$watch('models', function(model) {
            $scope.modelAsJson = angular.toJson(model, true);
        }, true);


});

app.filter('markdown', function(){
    var converter = new Showdown.converter();
        return function(input) {
            var html = converter.makeHtml(input || '');
                return html;
        }
});

app.filter('trust', function($sce){
    return function (input) {
        return $sce.trustAsHtml(input || '');
    }
});



