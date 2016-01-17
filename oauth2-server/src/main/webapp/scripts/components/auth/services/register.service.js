'use strict';

angular.module('oauth2App')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


