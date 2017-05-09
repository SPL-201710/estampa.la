import Ember from 'ember';
import RSVP from 'rsvp';

export default Ember.Route.extend({
  model: function() {
    this.store.adapterFor('application').set('host', 'http://users.soybackend.com');
    return RSVP.hash({
      roles: Ember.$.getJSON('http://users.soybackend.com/api/v1/roles/')      
    });
  }
});
