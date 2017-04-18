import Ember from 'ember';

export default Ember.Route.extend({
  model() {
    return JSON.parse(localStorage.getItem("products"));
  }
});
