import Ember from 'ember';

export default Ember.Route.extend({
  model(params) {
    return Ember.$.getJSON('http://catalog.soybackend.com/api/v1/products/' + params.id);
  }
});
