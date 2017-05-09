import Ember from 'ember';
import RSVP from 'rsvp';

export default Ember.Route.extend({
  model: function() {
    this.store.adapterFor('application').set('host', 'http://catalog.soybackend.com');
    return RSVP.hash({
      styles: this.get('store').findAll('shirtstyle'),
      sizes: this.get('store').findAll('shirtsize'),
      colors: this.get('store').findAll('shirtcolor'),
      materials: this.get('store').findAll('shirtmaterial')
    });
  }
});
