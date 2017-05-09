import Ember from 'ember';

export default Ember.Route.extend({
  model() {
    // this.store.adapterFor('application').set('host', 'http://catalog.soybackend.com');
    // return this.get('store').findAll('shirt');
    return Ember.$.getJSON('http://catalog.soybackend.com/api/v1/shirts');
  }
});
