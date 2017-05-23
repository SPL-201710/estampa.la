import Ember from 'ember';
import RSVP from 'rsvp';

export default Ember.Route.extend({
  model: function() {
    this.store.adapterFor('application').set('host', 'http://catalog.soybackend.com');
    return RSVP.hash({
      prints: this.get('store').query('print', { owner: this.get('session.data.authenticated.user.username') }),
      themes: this.get('store').findAll('theme')
    });
  }
});
