import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
      updateShirtStyle: function(id){
        var self = this;
        this.store.adapterFor('application').set('host', 'http://catalog.soybackend.com');
        this.get('store').findRecord('shirtstyle', id).then(function(shirtstyle) {
          shirtstyle.set('name', self.get('model.name'));
          shirtstyle.save();
          self.transitionToRoute('shirtstyles.list');
        });
      }
  }
});
