import Ember from 'ember';
import RSVP from 'rsvp';

export default Ember.Route.extend({
  model() {
    this.store.adapterFor('application').set('host', 'http://catalog.peoplerunning.co');
    return RSVP.hash({
      shirts: this.get('store').findAll('shirt'),
      shirtprintpositions: this.get('store').findAll('shirtprintposition'),
      prints: JSON.parse(localStorage.getItem("printsselected")),
      printfonts: this.get('store').findAll('printfont')
    });
  }
});
