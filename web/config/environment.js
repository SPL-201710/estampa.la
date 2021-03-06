/* jshint node: true */

module.exports = function(environment) {
  var ENV = {
    modulePrefix: 'estampala',
    environment: environment,
    rootURL: '/',
    locationType: 'auto',
    EmberENV: {
      FEATURES: {
        // Here you can enable experimental features on an ember canary build
        // e.g. 'with-controller': true
      },
      EXTEND_PROTOTYPES: {
        // Prevent Ember Data from overriding Date.parse.
        Date: false
      }
    },

    APP: {
      // Here you can pass flags/options to your application instance
      // when it is created
    }
  };

  if (environment === 'development') {
    // ENV.APP.LOG_RESOLVER = true;
    // ENV.APP.LOG_ACTIVE_GENERATION = true;
    // ENV.APP.LOG_TRANSITIONS = true;
    // ENV.APP.LOG_TRANSITIONS_INTERNAL = true;
    // ENV.APP.LOG_VIEW_LOOKUPS = true;
    ENV.APP.API_HOST= 'http://users.soybackend.com';
    ENV.contentSecurityPolicy = {'connnect-src': "'self' http://users.soybackend.com"};
  }

  if (environment === 'test') {
    // Testem prefers this...
    ENV.locationType = 'none';

    // keep test console output quieter
    ENV.APP.LOG_ACTIVE_GENERATION = false;
    ENV.APP.LOG_VIEW_LOOKUPS = false;

    ENV.APP.rootElement = '#ember-testing';
  }

  if (environment === 'production') {
    ENV.APP.API_HOST= 'http://users.soybackend.com';
    ENV.contentSecurityPolicy = {'connnect-src': "'self' http://users.soybackend.com"};
  }

  ENV['ember-simple-auth'] = {
    headers: {
      "content-type": "application/json",
      "cache-control": "no-cache"
    },
    authorizer: 'authorizer:token',
    routeAfterAuthentication: 'index'
  }

  ENV['ember-simple-auth-token'] = {
    serverTokenEndpoint: 'http://users.soybackend.com/api/v1/users/login',
    identificationField: 'username',
    passwordField: 'password',
    tokenPropertyName: 'token',
    refreshTokenPropertyName: 'refresh_token',
    authorizationPrefix: 'Token ',
    authorizationHeaderName: 'Authorization',
    routeAfterAuthentication: 'index',
    headers: {
      "content-type": "application/json",
      "cache-control": "no-cache",
    }
  };  

  return ENV;
};
