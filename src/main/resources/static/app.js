var app = angular.module('NewsApp',["ngResource", "spring-data-rest", "angular-table", "ui.bootstrap",]);


app.controller("NewsController", function($scope, $http, SpringDataRestAdapter){

    var d = new Date();
    d.setDate(d.getDate() - 7);
    var dateString = d.toISOString().slice(0,10).replace(/-/g,"/");
    var keyword = 'space';

    // TODO: use hateoas, form for keyword, datepicker, pagination
    // var httpPromise = $http.get('http://localhost:8080/api/articles?size=100');
    var httpPromise = $http.get('/api/articles/search/findByDatePublishedAfterAndTitleContainingIgnoreCase?date='+dateString+'&keyword='+keyword+'&size=100');

    SpringDataRestAdapter.process(httpPromise).then(function (processedResponse) {
        $scope.articles = processedResponse._embeddedItems;
        $scope.sortType = 'datePublished';
        $scope.sortReverse = true;
        $scope.dateFormat = 'medium'
        $scope.config = {
            itemsPerPage: 10,
            fillLastPage: false
        }
    });
});