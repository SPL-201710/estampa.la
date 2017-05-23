import Ember from 'ember';

export default Ember.Controller.extend({
  session: Ember.inject.service(),
  twitter_response: null,
  actions: {
    authenticate: function() {
      var credentials = this.getProperties('identification', 'password');
      var authenticator = 'authenticator:token';

      var self = this;
      this.get('session').authenticate(authenticator, credentials).then(function() {
        self.transitionToRoute('index');
      }, function(error) {
        console.log(error);
        alert("Error de usuario o clave");
      });
    },
    facebookLogin: function() {
      var self = this;
      var facebook_response = null;
      FB.login(function(response) {
        if (response.authResponse) {
          var access_token = response.authResponse.accessToken;
          FB.api('/me', { locale: 'es_CO', fields: 'first_name, email, last_name',  }, function(response) {
            facebook_response = response;

            var credentials = {
              username: response.email,
              token: access_token,
              method: 'facebook'
            }
            var authenticator = 'authenticator:social';
            self.get('session').authenticate(authenticator, credentials).then(function() {
              self.transitionToRoute('index');
            }, function(error) {

              self.store.adapterFor('application').set('host', 'http://users.soybackend.com');

              var newUser = self.get('store').createRecord('user', {
                username: facebook_response.email,
                password: '',
                firstName: facebook_response.first_name,
                lastName: facebook_response.last_name,
                email: facebook_response.email,
                phoneNumber: '3167556764',
                method: 'facebook',
                roles: ['abfaad49-1392-478b-aacd-06b7e4577605']
              });

              function transitionToIndex () {
                var credentials = {
                  username: facebook_response.email,
                  token: access_token,
                  method: 'facebook'
                }
                var authenticator = 'authenticator:social';
                self.get('session').authenticate(authenticator, credentials).then(function() {
                  self.transitionToRoute('index');
                }, function(error) {
                  alert("Error de autenticacion");
                });
              };

              function failure (reason) {
                alert(reason);
              };

              newUser.save().then(transitionToIndex).catch(failure);

            });
          });
        } else {
            console.log('User cancelled login or did not fully authorize.');
        }
      }, {
        scope: 'public_profile, email'
      });
    },
    twitterLogin: function(){
      var self = this;
      hello.init({
      	'twitter' : 'DLVNm7s5qAh352x6py8ZkENft'
      },{
        //redirect_uri: 'http://127.0.0.1:4200/usuarios/login/',
        //redirect_uri: 'http://web.soybackend.com/usuarios/login/',
        oauth_proxy: 'https://auth-server.herokuapp.com/proxy'
      });

      var twitter = hello('twitter');

    	twitter.login().then( function(response){
        self.set('twitter_response', response);

        var credentials = {
          username: response.authResponse.screen_name,
          token: response.authResponse.access_token,
          method: 'twitter'
        }

        var authenticator = 'authenticator:social';

        self.get('session').authenticate(authenticator, credentials).then(function() {
          self.transitionToRoute('index');
        }, function(error) {
          self.store.adapterFor('application').set('host', 'http://users.soybackend.com');
          var response = self.get('twitter_response');
          var newUser = self.get('store').createRecord('user', {
            username: response.authResponse.screen_name,
            password: '',
            firstName: response.authResponse.screen_name,
            lastName: response.authResponse.screen_name,
            email: 'kayroscenter@hotmail.com',
            phoneNumber: '3167556764',
            method: 'twitter',
            roles: ['abfaad49-1392-478b-aacd-06b7e4577605']
          });

          function transitionToIndex () {
            var credentials = {
              username: response.authResponse.screen_name,
              token: response.authResponse.access_token,
              method: 'twitter'
            }
            var authenticator = 'authenticator:social';
            self.get('session').authenticate(authenticator, credentials).then(function() {
              self.transitionToRoute('index');
            }, function(error) {
              alert("Error de autenticacion");
            });
          };

          function failure (reason) {
            alert(reason);
          };

          newUser.save().then(transitionToIndex).catch(failure);

        });
    	}, function(data){console.log(data)});
    }
  }
});
