import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
      updateShirtColor: function(id){
        var self = this;
        this.store.adapterFor('application').set('host', 'http://catalog.soybackend.com');
        this.get('store').findRecord('shirtcolor', id).then(function(shirtcolor) {
          shirtcolor.set('name', self.get('model.name'));
          shirtcolor.set('hexadecimalValue', Ember.$("#colorvalue").val());
          shirtcolor.save();
          self.transitionToRoute('shirtcolors.list');
        });
      }
  }
});
