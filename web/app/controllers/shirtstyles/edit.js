import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
      updateShirtStyle: function(id){
        var self = this;
        // this.store.adapterFor('application').set('host', 'http://catalog.peoplerunning.co');
        // this.get('store').findRecord('shirtstyle', id).then(function(shirtstyle) {
        //   shirtstyle.set('name', self.get('model.name'));
        //   shirtstyle.save();
        // });

        var settings = {
          "url": "http://catalog.peoplerunning.co/api/v1/shirtstyles/" + id,
          "method": "PUT",
          "headers": {
            "content-type": "application/json"
          },
          "processData": false,
          "data": JSON.stringify({
            "name": self.get('model.name')
          })
        }
        var self = this;
        $.ajax(settings).done(function (response) {
          alert("Estilo Actualizado");
          self.transitionToRoute('shirtstyles.list');
        });
      }
  }
});
