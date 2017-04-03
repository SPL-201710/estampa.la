import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    addShirtSize: function(){
      this.store.adapterFor('application').set('host', 'http://catalog.peoplerunning.co');
      var self = this;
      let newShirtSize = this.get('store').createRecord('shirtsize', {
        name: self.get('name')
      });

      function transitionToIndex () {
        self.transitionToRoute('shirtsizes.list');
      }

      function failure (reason) {
        alert(reason);
      }

      newShirtSize.save().then(transitionToIndex).catch(failure);
    }
  }
});
