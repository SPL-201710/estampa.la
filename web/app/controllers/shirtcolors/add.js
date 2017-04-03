import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    addShirtColor: function(){
      this.store.adapterFor('application').set('host', 'http://catalog.peoplerunning.co');
      var self = this;
      let newShirtColor = this.get('store').createRecord('shirtcolor', {
        name: self.get('name'),
        hexadecimalValue: self.get('hexadecimalValue')
      });

      function transitionToIndex () {
        self.transitionToRoute('shirtcolors.list');
      }

      function failure (reason) {
        alert(reason);
      }

      newShirtColor.save().then(transitionToIndex).catch(failure);
    }
  }
});
