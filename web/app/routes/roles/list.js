import Ember from 'ember';

export default Ember.Route.extend({
  model() {
    this.store.adapterFor('application').set('host', 'http://users.soybackend.com');
    return this.get('store').findAll('role');
  }
});
