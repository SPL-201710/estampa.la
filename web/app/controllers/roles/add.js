import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    addRole: function(){
      this.store.adapterFor('application').set('host', 'http://users.peoplerunning.co');
      var self = this;
      let newRole = this.get('store').createRecord('role', {
        name: self.get('name')
      });

      function transitionToIndex (user) {
        self.transitionToRoute('roles.list');
      }

      function failure (reason) {
        alert(reason);
      }

      newRole.save().then(transitionToIndex).catch(failure);
    }
  }
});
