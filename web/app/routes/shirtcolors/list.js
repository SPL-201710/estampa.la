import Ember from 'ember';

export default Ember.Route.extend({
  model() {
    this.store.adapterFor('application').set('host', 'http://localhost:8080');
    return this.get('store').findAll('shirtcolor');
  }
});
