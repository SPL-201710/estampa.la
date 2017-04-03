import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
      updatePrintFont: function(id) {
        var self = this;
        this.store.adapterFor('application').set('host', 'http://catalog.peoplerunning.co');
        this.get('store').findRecord('printfont', id).then(function(printfont) {
          printfont.set('name', self.get('model.name'));
          printfont.save();
          self.transitionToRoute('printfonts.list');
        });
      }
  }
});
