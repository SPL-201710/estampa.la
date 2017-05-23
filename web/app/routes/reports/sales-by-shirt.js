import Ember from 'ember';
import RSVP from 'rsvp';

export default Ember.Route.extend({
    model() {
      this.store.adapterFor('application').set('host', 'http://catalog.soybackend.com');
      return RSVP.hash({
        materials: this.get('store').findAll('shirtmaterial'),
        colors: this.get('store').findAll('shirtcolor'),
        sizes: this.get('store').findAll('shirtsize'),
        styles: this.get('store').findAll('shirtstyle')
      });
    }
});
