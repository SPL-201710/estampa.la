import Ember from 'ember';

export default Ember.Route.extend({
  model(params) {
    this.store.adapterFor('application').set('host', 'http://catalog.peoplerunning.co');
    return this.get('store').findRecord('print', params.print_id);
  }
});
