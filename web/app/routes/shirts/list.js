import Ember from 'ember';

export default Ember.Route.extend({
  model() {
    // this.store.adapterFor('application').set('host', 'http://catalog.peoplerunning.co');
    // return this.get('store').findAll('shirt');
    return Ember.$.getJSON('http://catalog.peoplerunning.co/api/v1/shirts');
  }
});
