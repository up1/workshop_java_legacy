app.controller('ContactController', function ($scope, $rootScope, $location, $http) {
    function initialize() {
        $scope.contacts = [];
        $scope.keyword = '';
        $scope.page = 0;
        $scope.searchContacts(0);
    }


    $scope.searchContacts = function (page) {
        if ($scope.isLoading) {
            return;
        }

        if (!page) {
            $scope.contacts = [];
        }


        $scope.isLoading = true;
        $http.get('api/contacts?keyword=' + $scope.keyword)
            .success(function (items) {
                $scope.contacts = items;
            })
            .finally(function () {
                $scope.isLoading = false;
            });
    };


    initialize();
});