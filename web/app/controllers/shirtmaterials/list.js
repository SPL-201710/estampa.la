import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    deleteShirtMaterial: function(shirtStyle){
      shirtStyle.destroyRecord().then(function() {
        alert("Material borrado");
        window.location.href = '/materiales/';
      }, function() {
        window.location.href = '/materiales/';
      });
    }
  }
});
