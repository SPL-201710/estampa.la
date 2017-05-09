import Ember from 'ember';
import RSVP from 'rsvp';

export default Ember.Route.extend({
  model() {
    this.store.adapterFor('application').set('host', 'http://catalog.soybackend.com');
    return RSVP.hash({
      // prints: this.get('store').findAll('print'),
      themes: Ember.$.getJSON('http://catalog.soybackend.com/api/v1/themes'),
      artists: Ember.$.getJSON('http://users.soybackend.com/api/v1/users/roles?role=ARTIST')
    });
  }
});
