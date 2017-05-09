import Ember from 'ember';
import RSVP from 'rsvp';

export default Ember.Route.extend({
  model() {
    this.store.adapterFor('application').set('host', 'http://catalog.soybackend.com');
    return RSVP.hash({
      shirts: Ember.$.getJSON('http://catalog.soybackend.com/api/v1/shirts/'),
      shirtprintpositions: this.get('store').findAll('shirtprintposition'),
      prints: JSON.parse(localStorage.getItem("printsselected")),
      printfonts: this.get('store').findAll('printfont'),
      textstyles: this.get('store').findAll('textstyle'),
      shirtcolors: this.get('store').findAll('shirtcolor')
    });
  }
});
