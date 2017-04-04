import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    deleteShirt: function(shirt){
      shirt.destroyRecord().then(function() {
        alert("Camiseta borrada");
        window.location.href = '/camisetas/';
      }, function() {
        window.location.href = '/camisetas/';
      });
    }
  }
});
