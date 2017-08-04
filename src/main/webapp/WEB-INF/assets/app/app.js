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

app.controller('GetInstructionsController', function($scope, $http){
   $scope.instructionsList = [];
   $scope.author = 'anton@something.com';
   $scope.getAllInstructions = function(){
       alert('clicked');
       var url = "/getinstruction/anton@something.com";
       $http.get(url).success(function(data){
           alert('success');
           $scope.instructionsList.push(data)
       });
   };
    $scope.$watch('instructionsList', function(model) {
        $scope.getInstructionsModel = angular.toJson(model, true);
    }, true);
});

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

app.controller("StepController", function($scope, $http) {
    $scope.title = 'some title';
    $scope.author = 'anton@something.com';
    $scope.creationDate = '02.08.2017';
    $scope.category = 'some category';
    $scope.shortDescription = 'balblabla short description';
    $scope.stepsList = [];
    $scope.stepIndex = null;

    $scope.headline = "Example Headline";

    $scope.contentList = [];
    $scope.templates = [
        {type: "Text field", message: "", position: null},
        {type: "Media field", files: "", position: null}
    ];
////Начальные значения для шага
    $scope.contentList.push({type: "Text field", message: "Example field. Replace it", position: 0});
    $scope.stepsList.push({headline: $scope.headline, contentList: $scope.contentList, stepIndex: 0});
////
    $scope.addStepBuilder = function(){
        $scope.stepsList.push({headline: $scope.headline, contentList: $scope.contentList, stepIndex: $scope.stepIndex});
    };

    $scope.deleteStep = function(){
        $scope.stepsList.pop();
    };

    $scope.saveInstruction = function(){
        var url = "/createinstruction";
        var data = { title: $scope.title, author: $scope.author, creationDate: $scope.creationDate, category: $scope.category, shortDescription: $scope.shortDescription, stepsList: $scope.stepsList};
        var config = {
            headers : {
                'Content-Type': 'application/json;'
            }
        };
        $http.post(url, data, config).success(function (response) {});
    };

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




app.directive('toolsPanel',[function(){
    return{
        restrict: 'AE',
        replace: false,
        template: '<div class="panel panel-info">'+
                    '<div class="panel-heading">'+
                        '<h3 class="panel-title">New Elements</h3>'+
                    '</div>'+
                    '<div class="panel-body">'+
                        '<ul dnd-list="list">'+
                            '<li ng-repeat="item in templates" dnd-draggable="item" dnd-effect-allowed="copy">'+
                                '{{item.type}}'+
                            '</li>'+
                        '</ul>'+
                    '</div>'+
                '</div>'+

                '<div class="panel panel-danger">'+
                    '<div class="panel-heading">'+
                        '<h2 class="panel-title">Trashcan</h2>'+
                    '</div>'+
                    '<div class="panel-body">'+
                        '<ul dnd-list="[]">'+
                            '<li><img src="img/recyclebin.png"></li>'+
                        '</ul>'+
                    '</div>'+
                '</div>'
    }
}]);

app.directive('editingPanel', [function(){
    return{
        controller: 'StepController',
        restrict: 'AE',
        replace: false,
        template: '<div class="panel panel-info">'+
                        '<div class="panel-heading">'+
                            '<h3 class="panel-title">Step editing</h3>'+
                            '<input type="text" ng-model="headline" class="form-control">'+
                        '</div>'+
                        '<ul dnd-list="contentList">'+
                            '<li ng-repeat="item in contentList">'+
                                '<div dnd-handle class="handle" dnd-draggable="item" dnd-effect-allowed="move" dnd-moved="contentList.splice($index, 1)">' +
                                    '<strong>Grab me!</strong>'+
                                    '<span ng-bind="item.position=$index" ng-hide="true"></span>'+
                                '</div>'+
                                '<div ng-if="item.type==\'Text field\'">'+
                                    '<input type="text" ng-model="item.message" class="form-control input-lg">'+
                                '</div>'+
                                '<div ng-if="item.type==\'Media field\'" ng-controller="uploadFileController">'+
                                    '<p><input class="form-control" type="file" file-model = "uploadedFile"></p>'+
                                    '<button type="submit" class="btn btn-default" ng-click = "uploadFile(item)">Upload</button>'+
                                    '<p><label ng-bind="uploadResult"/></p>'+
                                '</div>'+
                            '</li>'+
                        '</ul>'+
                    '</div>'
    }
}]);

app.directive('resultPanel', [function(){
    return{
        restrict: 'AE',
        replace: false,
        template: '<div class="panel panel-default">'+
                    '<div class="panel-heading">'+
                        '<h3 class="panel-title">{{headline}}</h3>'+
                    '</div>'+
                    '<div class="panel-body">'+
                        '<ul>'+
                            '<div ng-repeat="item in contentList">'+
                                '<div ng-if="item.type==\'Text field\'">'+
                                    '<div ng-bind-html="item.message|markdown|trust"></div>'+
                                '</div>'+
                                '<div ng-if="item.type==\'Media field\'">'+
                                    '<img ng-src="{{item.files}}" style="max-height: 300px; max-width: 300px">'+
                                '</div>'+
                            '</div>'+
                        '</ul>'+
                    '</div>'+
                '</div>'
    }
}]);