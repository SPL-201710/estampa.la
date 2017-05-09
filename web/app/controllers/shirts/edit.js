import Ember from 'ember';

export default Ember.Controller.extend({
  init: function() {
    this._super();
    console.log(this.model);
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
    updateShirt: function(id){
      console.log(id);
      var self = this;

      this.store.adapterFor('application').set('host', 'http://catalog.soybackend.com');
      this.get('store').findRecord('shirt', id).then(function(shirt) {
        if(self.get('style')===""){
          shirt.set('shirtStyle', self.get('model.shirt.style.id'));
        }
        else{
          shirt.set('shirtStyle', self.get('style'));
        }

        if(self.get('size')===""){
          shirt.set('shirtSize', self.get('model.shirt.size.id'));
        }
        else{
          shirt.set('shirtSize', self.get('size'));
        }

        if(self.get('color')===""){
          shirt.set('shirtColor', self.get('model.shirt.color.id'));
        }
        else{
          shirt.set('shirtColor', self.get('color'));
        }

        if(self.get('material')===""){
          shirt.set('shirtMaterial', self.get('model.shirt.material.id'));
        }
        else{
          shirt.set('shirtMaterial', self.get('material'));
        }

        shirt.save();
        self.transitionToRoute('shirts.list');
      });
    }
  }
});
