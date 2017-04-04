import Ember from 'ember';

export default Ember.Controller.extend({
  theme: "",
  actions: {
    selectTheme: function(selectedTheme){
      this.set('theme', selectedTheme);
    },
    editPrint: function(id){      
      var self = this;
      this.store.adapterFor('application').set('host', 'http://catalog.peoplerunning.co');
      this.get('store').findRecord('print', id).then(function(print) {
        print.set('name', self.get('model.print.name'));
        print.set('price', self.get('model.print.price'));
        if(self.get('theme')===""){
          print.set('theme', self.get('model.print.theme.id'));
        }
        else{
          print.set('theme', self.get('theme'));
        }
        print.set('description', self.get('model.print.description'));
        print.save();
        self.transitionToRoute('prints.list');
      });
    }
  }
});
