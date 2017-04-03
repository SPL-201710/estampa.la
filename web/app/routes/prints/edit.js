import Ember from 'ember';
import RSVP from 'rsvp';

export default Ember.Route.extend({
  model(params) {
    this.store.adapterFor('application').set('host', 'http://catalog.peoplerunning.co');
    return RSVP.hash({
      print: this.get('store').findRecord('print', params.print_id),
      themes: this.get('store').findAll('theme')
    });
  }
});
