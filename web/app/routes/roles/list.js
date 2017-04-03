import Ember from 'ember';

export default Ember.Route.extend({
  model() {
    this.store.adapterFor('application').set('host', 'http://users.peoplerunning.co');
    return this.get('store').findAll('role');
  }
});
