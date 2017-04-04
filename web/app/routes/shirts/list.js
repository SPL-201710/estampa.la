import Ember from 'ember';

export default Ember.Route.extend({
  model() {
    this.store.adapterFor('application').set('host', 'http://catalog.peoplerunning.co');
    debugger;
    return this.get('store').findAll('shirt');
  }
});
