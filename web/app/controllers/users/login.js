import Ember from 'ember';

export default Ember.Controller.extend({
  session: Ember.inject.service(),

  actions: {
    authenticate: function() {
      var credentials = this.getProperties('identification', 'password');      
      var authenticator = 'authenticator:jwt';

      var self = this;
      this.get('session').authenticate(authenticator, credentials).then(function() {
        self.transitionToRoute('index');
      }, function(error) {
        alert("Error de usuario o clave");
      });
    }
  }
});
