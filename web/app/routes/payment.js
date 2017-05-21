import Ember from 'ember';
import RSVP from 'rsvp';
import AuthenticatedRouteMixin from 'ember-simple-auth/mixins/authenticated-route-mixin';

export default Ember.Route.extend(AuthenticatedRouteMixin, {
    model() {
      this.store.adapterFor('application').set('host', 'http://catalog.soybackend.com');
      return RSVP.hash({
        cart: JSON.parse(localStorage.getItem("cart")),
        giftcards: Ember.$.getJSON('http://payment.soybackend.com/api/v1/giftcards/receiver/' + this.get('session.data.authenticated.user.id'))
      });
    }
});
