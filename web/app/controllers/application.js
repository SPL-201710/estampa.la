import Ember from 'ember';

export default Ember.Controller.extend({
  init: function() {
    Ember.$.getJSON('/features.json', function(features) {
      localStorage.setItem('features', JSON.stringify(features));
    });
  },
  session: Ember.inject.service('session'),
  actions: {
    invalidateSession() {
      this.get('session').invalidate();
      this.transitionToRoute('index');
    }
  }
});
