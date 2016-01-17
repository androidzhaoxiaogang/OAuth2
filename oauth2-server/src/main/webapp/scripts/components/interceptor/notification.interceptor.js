 'use strict';

angular.module('oauth2App')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-oauth2App-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-oauth2App-params')});
                }
                return response;
            }
        };
    });
