import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    deleteShirtStyle: function(shirtStyle){
      shirtStyle.destroyRecord().then(function() {
        alert("Estilo borrado");
        window.location.href = '/estilos/';
      }, function() {
        alert("Estilo borrado");
        window.location.href = '/estilos/';
      });
    }
  }
});
