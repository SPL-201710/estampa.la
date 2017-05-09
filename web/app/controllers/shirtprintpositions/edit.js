import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
      updateShirtPrintPosition: function(id){
        var self = this;
        this.store.adapterFor('application').set('host', 'http://catalog.soybackend.com');
        this.get('store').findRecord('shirtprintposition', id).then(function(shirtprintposition) {
          shirtprintposition.set('name', self.get('model.name'));
          shirtprintposition.set('hightInCentimeters', parseInt(self.get('model.hightInCentimeters')));
          shirtprintposition.set('widthInCentimeters', parseInt(self.get('model.widthInCentimeters')));
          shirtprintposition.save();
          self.transitionToRoute('shirtprintpositions.list');
        });
      }
  }
});
