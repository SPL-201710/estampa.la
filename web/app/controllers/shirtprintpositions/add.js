import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    addShirtPrintPosition: function(){
      this.store.adapterFor('application').set('host', 'http://catalog.peoplerunning.co');
      var self = this;
      let newShirtPrintPosition = this.get('store').createRecord('shirtprintposition', {
        name: self.get('name'),
        hightInCentimeters: parseInt(self.get('hightInCentimeters')),
        widthInCentimeters: parseInt(self.get('widthInCentimeters'))
      });

      function transitionToIndex () {
        self.transitionToRoute('shirtprintpositions.list');
      }

      function failure (reason) {
        alert(reason);
      }

      newShirtPrintPosition.save().then(transitionToIndex).catch(failure);
    }
  }
});
