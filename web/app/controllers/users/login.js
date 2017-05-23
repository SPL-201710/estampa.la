import Ember from 'ember';

export default Ember.Controller.extend({
  session: Ember.inject.service(),
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
      FB.login(function(response) {
        if (response.authResponse) {
          var access_token = response.authResponse.accessToken;
          FB.api('/me', { locale: 'es_CO', fields: 'name, email' }, function(response) {
            var credentials = {
              username: response.email,
              token: access_token,
              method: 'facebook'
            }
            var authenticator = 'authenticator:facebook';
            self.get('session').authenticate(authenticator, credentials).then(function() {
              self.transitionToRoute('index');
            }, function(error) {
              alert("Error de autenticacion");
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
    }
  }
});
