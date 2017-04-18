import Ember from 'ember';

export default Ember.Controller.extend({
  init: function() {
    this._super();
    this.set('style', "");
    this.set('size', "");
    this.set('color', "");
    this.set('material', "");
  },
  style: "",
  size: "",
  color: "",
  material: "",
  actions: {
    updateStyle: function(value) {
      this.set('style', value);
    },
    updateSize: function(value) {
      this.set('size', value);
    },
    updateColor: function(value) {
      this.set('color', value);
    },
    updateMaterial: function(value) {
      this.set('material', value);
    },
    addShirt: function(){
      this.store.adapterFor('application').set('host', 'http://catalog.peoplerunning.co');
      let newShirt = this.get('store').createRecord('shirt', {
        shirtStyle: this.get('style'),
        shirtSize: this.get('size'),
      	shirtColor: this.get('color'),
      	shirtMaterial: this.get('material')
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
