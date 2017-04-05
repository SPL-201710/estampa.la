import Ember from 'ember';

export default Ember.Route.extend({
  model() {
    return Ember.$.getJSON('http://catalog.peoplerunning.co/api/v1/products/')
  }
});
