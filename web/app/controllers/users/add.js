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
      this.store.adapterFor('application').set('host', 'http://users.peoplerunning.co');

      var roleArray = [];
      roleArray.push(this.get('roleSelected'));

      var newUser = this.get('store').createRecord('user', {
        username: this.get('username'),
        password: this.get('password'),
        firstName: this.get('firstName'),
        lastName: this.get('lastName'),
        email: this.get('email'),
        phoneNumber: this.get('phoneNumber'),
        roles: ["feac7c3a-fb28-425e-ac33-46163abef9fd"]
      });

      var self = this;

      function transitionToIndex (user) {
        var credentials = {
          "identification": user.get('username'),
          "password": user.get('password')
        };

        var authenticator = 'authenticator:jwt';
        var selfold = self;
        self.get('session').authenticate(authenticator, credentials).then(function() {
          selfold.transitionToRoute('index');
        }, function(error) {
          alert("Error de usuario o clave");
        });
      };

      function failure (reason) {
        alert(reason);        
      };

      newUser.save().then(transitionToIndex).catch(failure);
    }
  }
});
