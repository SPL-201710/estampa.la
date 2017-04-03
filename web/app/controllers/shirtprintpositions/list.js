import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    deleteShirtPrintPosition: function(shirtprintposition){
      shirtprintposition.destroyRecord().then(function() {
        alert("Posición de Estampa borrada");
        window.location.href = '/posicionestampas/';
      }, function() {
        window.location.href = '/posicionestampas/';
      });
    }
  }
});
