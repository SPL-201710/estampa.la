import Ember from 'ember';

export default Ember.Controller.extend({
  session: Ember.inject.service('session'),

  init: function() {
    this._super();
    this.set('roleSelected', "");
  },
  roleSelected: "",
  actions: {
    updateValue: function(value) {
      this.set('roleSelected', value);
    },
    addUser: function(){
      this.store.adapterFor('application').set('host', 'http://users.soybackend.com');

      var roleArray = [];
      roleArray.push(this.get('roleSelected'));

      var newUser = this.get('store').createRecord('user', {
        username: this.get('username'),
        password: this.get('password'),
        firstName: this.get('firstName'),
        lastName: this.get('lastName'),
        email: this.get('email'),
        phoneNumber: this.get('phoneNumber'),
        roles: roleArray
      });

      var self = this;

      function transitionToIndex (user) {
        var credentials = {
          "identification": user.get('username'),
          "password": user.get('password')
        };

        var authenticator = 'authenticator:token';
        var selfold = self;
        self.get('session').authenticate(authenticator, credentials).then(function() {
          selfold.transitionToRoute('index');
        }, function(error) {
          alert("Error de usuario o clave " + error);
        });
      }

      function failure (reason) {
        alert(reason);
      }

      newUser.save().then(transitionToIndex).catch(failure);
    }
  }
});
