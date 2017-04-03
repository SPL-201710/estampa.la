import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
      updateShirtMaterial: function(id){
        var self = this;
        this.store.adapterFor('application').set('host', 'http://catalog.peoplerunning.co');
        this.get('store').findRecord('shirtmaterial', id).then(function(shirtmaterial) {
          shirtmaterial.set('name', self.get('model.name'));
          shirtmaterial.save();
          self.transitionToRoute('shirtmaterials.list');
        });
      }
  }
});
