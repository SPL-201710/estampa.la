import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    deletePrintFont: function(printfont){
      printfont.destroyRecord().then(function() {
        alert("Tipo de letra borrado");
        window.location.href = '/tiposletra/';
      }, function() {
        window.location.href = '/tiposletra/';
      });
    }
  }
});
