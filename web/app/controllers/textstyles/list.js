import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    deleteTextStyle: function(textstyle){
      textstyle.destroyRecord().then(function() {
        alert("Estilo de texto borrado");
        window.location.href = '/estilostexto/';
      }, function() {
        window.location.href = '/estilostexto/';
      });
    }
  }
});
