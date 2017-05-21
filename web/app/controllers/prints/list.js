import Ember from 'ember';

export default Ember.Controller.extend({
  printSelected: "",
  actions: {
    selectTheme: function(print){
      this.set('printSelected', print);
      try{
          Ember.$("#themesModal").modal({backdrop: 'static'});
      }
      catch(err){
        $("#themesModal").modal({backdrop: 'static'});
      }
    },
    changeTheme: function () {
      this.store.adapterFor('application').set('host', 'http://catalog.soybackend.com');
      var self = this;
      this.get('store').findRecord('print', this.get('printSelected')).then(function(print) {
        console.log(print);
        var theme = Ember.$("#theme option:selected").val();
        print.set('theme', theme);
        print.set('image', null);
        print.save();
        try{
            Ember.$("#themesModal").modal('toogle');
        }
        catch(err){
          $("#themesModal").modal('toogle');
        }
      });
    }
  }
});
