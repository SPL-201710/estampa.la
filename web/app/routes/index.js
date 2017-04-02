import Ember from 'ember';
import RSVP from 'rsvp';

export default Ember.Route.extend({
  model() {
    this.store.adapterFor('application').set('host', 'http://catalog.peoplerunning.co');
    return RSVP.hash({
      prints: this.get('store').findAll('print')
    });
  }
});
