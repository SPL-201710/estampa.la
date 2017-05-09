import Ember from 'ember';

export default Ember.Route.extend({
  model() {
    // return Ember.$.getJSON('http://shopping-cart.soybackend.com/api/v1/carts');
    return JSON.parse(localStorage.getItem("cart"));
  }
});
