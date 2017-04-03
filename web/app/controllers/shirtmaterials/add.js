import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    addShirtMaterial: function(){
      this.store.adapterFor('application').set('host', 'http://catalog.peoplerunning.co');
      var self = this;
      let newShirtMaterial = this.get('store').createRecord('shirtmaterial', {
        name: self.get('name')
      });

      function transitionToIndex () {
        self.transitionToRoute('shirtmaterials.list');
      }

      function failure (reason) {
        alert(reason);
      }

      newShirtMaterial.save().then(transitionToIndex).catch(failure);
    }
  }
});
