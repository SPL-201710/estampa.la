import Ember from 'ember';

export default Ember.Controller.extend({
  theme: "",
  actions: {
    selectTheme: function(selectedTheme){
      this.set('theme', selectedTheme);
    },
    editPrint: function(id){
      console.log(id);
      var self = this;
      this.store.adapterFor('application').set('host', 'http://catalog.peoplerunning.co');
      this.get('store').findRecord('print', id).then(function(print) {
        console.log(self.get('model.print.name'));
        // print.set('name', self.get('model.print.name'));
        // print.save();
        // self.transitionToRoute('prints.list');
      });
    }
  }
});
