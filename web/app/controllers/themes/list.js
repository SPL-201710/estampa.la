import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    deleteTheme: function(theme){
      theme.destroyRecord().then(function() {
        alert("Tema borrado");
        window.location.href = '/temas/';
      }, function() {
        window.location.href = '/temas/';
      });
    }
  }
});
