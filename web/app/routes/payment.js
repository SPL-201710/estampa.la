import Ember from 'ember';
import RSVP from 'rsvp';

export default Ember.Route.extend({
    model() {
      this.store.adapterFor('application').set('host', 'http://catalog.soybackend.com');
      return RSVP.hash({
        cart: JSON.parse(localStorage.getItem("cart")),
        giftcards: Ember.$.getJSON('http://payment.soybackend.com/api/v1/giftcards')
      });
    }
});
