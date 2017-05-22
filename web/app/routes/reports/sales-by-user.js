import Ember from 'ember';
import RSVP from 'rsvp';

export default Ember.Route.extend({
    model() {
      this.store.adapterFor('application').set('host', 'http://catalog.soybackend.com');
      return RSVP.hash({
        users: Ember.$.getJSON('http://users.soybackend.com/api/v1/users'),
        themes: this.get('store').findAll('theme')
      });
    }
});
