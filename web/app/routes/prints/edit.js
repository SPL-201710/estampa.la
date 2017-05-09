import Ember from 'ember';
import RSVP from 'rsvp';

export default Ember.Route.extend({
  model(params) {
    this.store.adapterFor('application').set('host', 'http://catalog.soybackend.com');    
    return RSVP.hash({
      print: Ember.$.getJSON('http://catalog.soybackend.com/api/v1/prints/' + params.print_id),
      themes: this.get('store').findAll('theme')
    });
  }
});
