import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    deleteShirt: function(shirt_id){
      this.store.adapterFor('application').set('host', 'http://catalog.soybackend.com');      
      this.get('store').findRecord('shirt', shirt_id).then(function(shirt) {
        debugger;
        shirt.destroyRecord().then(function() {
          alert("Camiseta borrada");
          window.location.href = '/camisetas/';
        }, function() {
          window.location.href = '/camisetas/';
        });
      });
    }
  }
});
