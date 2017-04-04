import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    updateStyle: function(value) {
      this.set('style.value', value);
    },
    updateSize: function(value) {
      this.set('size.value', value);
    },
    updateColor: function(value) {
      this.set('color.value', value);
    },
    updateMaterial: function(value) {
      this.set('material.value', value);
    },
    addShirt: function(){
      this.store.adapterFor('application').set('host', 'http://catalog.peoplerunning.co');
      let newShirt = this.get('store').createRecord('shirt', {
        style: this.get('style.value'),
        size: this.get('size.value'),
      	color: this.get('color.value'),
      	material: this.get('material.value')
      });

      var self = this;
      
      function transitionToIndex () {
        self.transitionToRoute('shirts.list');
      }

      function failure (reason) {
        alert(reason);
      }

      newShirt.save().then(transitionToIndex).catch(failure);
    }
  }
});
