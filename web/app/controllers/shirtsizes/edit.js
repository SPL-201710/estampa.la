import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
      updateShirtSize: function(id) {
        var self = this;
        this.store.adapterFor('application').set('host', 'http://catalog.soybackend.com');
        this.get('store').findRecord('shirtsize', id).then(function(shirtsize) {
          shirtsize.set('name', self.get('model.name'));
          shirtsize.save();
          self.transitionToRoute('shirtsizes.list');
        });
      }
  }
});
