import Ember from 'ember';
import RSVP from 'rsvp';

export default Ember.Route.extend({
  model(params) {
    return RSVP.hash({
      payment: Ember.$.getJSON('http://payment.soybackend.com/api/v1/payments/' + params.id)
    });
  },
  setupController: function(controller, model) {
    this._super(controller, model);    
  }
});
