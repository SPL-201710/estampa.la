import Ember from 'ember';
import RSVP from 'rsvp';

export default Ember.Route.extend({
  model() {
    this.store.adapterFor('application').set('host', 'http://catalog.peoplerunning.co');
    return RSVP.hash({
      prints: this.get('store').findAll('print'),
      themes: this.get('store').findAll('theme'),
      artists: Ember.$.getJSON('http://users.peoplerunning.co/api/v1/users/roles?role=ARTIST')
    });
  }
});
