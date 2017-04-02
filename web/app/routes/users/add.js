import Ember from 'ember';
import RSVP from 'rsvp';

export default Ember.Route.extend({
  model: function() {
    this.store.adapterFor('application').set('host', 'http://users.peoplerunning.co');
    return RSVP.hash({
      roles: this.get('store').findAll('role')
      // print: this.store.createRecord('print')
    });
  }
});
