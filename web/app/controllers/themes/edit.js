import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
      updateTheme: function(id){
        var self = this;
        this.store.adapterFor('application').set('host', 'http://catalog.peoplerunning.co');
        this.get('store').findRecord('theme', id).then(function(theme) {
          theme.set('name', self.get('model.name'));
          theme.save();
          self.transitionToRoute('themes.list');
        });
      }
  }
});
