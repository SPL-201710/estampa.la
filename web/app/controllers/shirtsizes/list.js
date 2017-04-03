import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    deleteShirtSize: function(shirtSize){
      shirtSize.destroyRecord().then(function() {
        alert("Talla borrado");
        window.location.href = '/tallas/';
      }, function() {
        window.location.href = '/tallas/';
      });
    }
  }
});
