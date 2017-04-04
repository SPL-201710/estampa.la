import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
      updateShirt: function(id){
        var self = this;
        this.store.adapterFor('application').set('host', 'http://catalog.peoplerunning.co');
        this.get('store').findRecord('shirt', id).then(function(shirt) {
          shirt.set('style', self.get('model.style'));
          shirt.set('size', self.get('model.size'));
        	shirt.set('color', self.get('model.color'));
        	shirt.set('material', self.get('model.material'));

          shirt.save();
          self.transitionToRoute('shirts.list');
        });
      }
  }
});
