import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
      updateTextStyle: function(id) {
        var self = this;
        this.store.adapterFor('application').set('host', 'http://catalog.soybackend.com');
        this.get('store').findRecord('textstyle', id).then(function(textstyle) {
          textstyle.set('name', self.get('model.name'));
          textstyle.save();
          self.transitionToRoute('textstyles.list');
        });
      }
  }
});
