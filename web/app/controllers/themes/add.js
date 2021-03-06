import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    addTheme: function(){
      this.store.adapterFor('application').set('host', 'http://catalog.soybackend.com');
      var self = this;
      let newTheme = this.get('store').createRecord('theme', {
        name: self.get('name')
      });

      function transitionToIndex () {
        self.transitionToRoute('themes.list');
      }

      function failure (reason) {
        alert(reason);
      }

      newTheme.save().then(transitionToIndex).catch(failure);
    }
  }
});
