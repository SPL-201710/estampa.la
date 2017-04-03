import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    deleteRole: function(role){
      role.destroyRecord().then(function() {
        alert("Rol borrado");
        window.location.href = '/roles/';
      }, function() {
        window.location.href = '/roles/';
      });
    }
  }
});
