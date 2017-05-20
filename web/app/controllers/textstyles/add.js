import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    addTextStyle: function(){
      this.store.adapterFor('application').set('host', 'http://catalog.soybackend.com');
      var self = this;
      let newTextStyle = this.get('store').createRecord('textstyle', {
        name: self.get('name')
      });

      function transitionToIndex () {
        self.transitionToRoute('textstyles.list');
      }

      function failure (reason) {
        alert(reason);
      }

      newTextStyle.save().then(transitionToIndex).catch(failure);
    }
  }
});
