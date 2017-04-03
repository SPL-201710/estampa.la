import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    addPrintFont: function(){
      this.store.adapterFor('application').set('host', 'http://localhost:8080');
      var self = this;
      let newPrintFont = this.get('store').createRecord('printfont', {
        name: self.get('name')
      });

      function transitionToIndex () {
        self.transitionToRoute('printfonts.list');
      }

      function failure (reason) {
        alert(reason);
      }

      newPrintFont.save().then(transitionToIndex).catch(failure);
    }
  }
});
