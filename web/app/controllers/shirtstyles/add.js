import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    addShirtStyle: function(){
      this.store.adapterFor('application').set('host', 'http://catalog.peoplerunning.co');
      var self = this;
      let newShirtStyle = this.get('store').createRecord('shirtstyle', {
        name: self.get('name')
      });

      function transitionToIndex () {
        alert("Estilo creado");
        self.transitionToRoute('shirtstyles.list');
      }

      function failure (reason) {
        alert(reason);
      }

      newShirtStyle.save().then(transitionToIndex).catch(failure);
    }
  }
});
