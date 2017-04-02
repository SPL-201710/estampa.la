import Ember from 'ember';

export default Ember.Controller.extend({
  session: Ember.inject.service('session'),
  actions: {
    editUser: function(){
      var self = this;
      this.store.adapterFor('application').set('host', 'http://users.peoplerunning.co');
      this.get('store').findRecord('user', this.get('session.data.authenticated.user.id')).then(function(user) {
        user.set('firstName', self.get('model.firstName'));
        user.set('lastName', self.get('model.lastName'));
        user.set('email', self.get('model.email'));
        user.set('phoneNumber', self.get('model.phoneNumber'));
        user.save();
        alert("Datos Actualizados");
        self.transitionToRoute('index');
      });

    }
  }
});
