import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    deleteShirtColor: function(shirtcolor){
      shirtcolor.destroyRecord().then(function() {
        alert("Color borrado");
        window.location.href = '/colores/';
      }, function() {
        window.location.href = '/colores/';
      });
    }
  }
});
