import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
      updateRole: function(id) {
        var self = this;
        this.store.adapterFor('application').set('host', 'http://users.peoplerunning.co');
        this.get('store').findRecord('role', id).then(function(role) {
          role.set('name', self.get('model.name'));
          role.save();
          self.transitionToRoute('roles.list');
        });
      }
  }
});
