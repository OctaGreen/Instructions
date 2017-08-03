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


/*
app.controller('CreateInstructionController', function($scope){
    $scope.title = 'some title';
    $scope.author = 'anton@itransition.com';
    $scope.creationDate = '02.08.2017';
    $scope.category = 'some category';
    $scope.shortDescription = 'balblabla short description';
    $scope.stepsList = [];


    $scope.stepsButtons = [];
     $scope.addStepBuilder = function($scope){
     $scope.stepsButtons.push();
     };
    $scope.$watch('stepsList', function(model) {
        $scope.instructionModelAsJson = angular.toJson(model, true);
    }, true);
});
*/

app.controller("StepController", function($scope) {
    $scope.title = 'some title';
    $scope.author = 'anton@itransition.com';
    $scope.creationDate = '02.08.2017';
    $scope.category = 'some category';
    $scope.shortDescription = 'balblabla short description';
    $scope.stepsList = [];

    $scope.headline = "Example Headline";

    $scope.contentList = [];
    $scope.templates = [
        {type: "Text field", id: 1, message: "", position: null},
        {type: "Media field", id: 2, files: "", position: null}
    ];

    $scope.contentList.push({type: "Text field", id: 3, message: "Example field. Replace it", position: 0});
    $scope.stepsList.push({headline: $scope.headline, contentList: $scope.contentList});

//Как указать на изменяемость переменной headline (выше)? Думаю, что $scope берет значения из конкретной узкой области видимости

    $scope.addStepBuilder = function(){
        $scope.stepsList.push({headline: $scope.headline, contentList: $scope.contentList});
    };

    $scope.$watch('contentList', function(model) {
        $scope.modelAsJson = angular.toJson(model, true);
    }, true);

    $scope.$watch('stepsList', function(model) {
        $scope.instructionModelAsJson = angular.toJson(model, true);
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

app.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);

app.controller('uploadFileController', ['$scope', '$http', function($scope, $http){
    $scope.uploadFile = function(item){
        var file = $scope.uploadedFile;
        var url = "/api/uploadfile";
        var data = new FormData();
        data.append('uploadfile', file);
        $scope.filename = file.name;
        var config = {
            transformRequest: angular.identity,
            transformResponse: angular.identity,
            headers : {
                'Content-Type': undefined
            }
        };

        $http.post(url, data, config).success(function (response, status) {
            var restObj = JSON.parse(response);
            $scope.uploadResult=restObj.message;
            item.files = restObj.fileUrl;
        });
    };
}]);

app.directive('showStepbutton', [function(){
    return{
        restrict: 'AE',
        replace: false,
        template: '<div class="row"><div class="col-sm-12"><button class="btn btn-info" data-toggle="collapse" data-target="#showStepAction" ng-bind="headline" style="width: 100%"></button></div></div>'
    }
}]);

app.directive('showStep', function(){
    return{
        restrict: 'AE',
        replace: false

    }
});