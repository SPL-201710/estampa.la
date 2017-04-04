import Ember from 'ember';
import RSVP from 'rsvp';
import AuthenticatedRouteMixin from 'ember-simple-auth/mixins/authenticated-route-mixin';

export default Ember.Route.extend(AuthenticatedRouteMixin, {
  model(params) {    
    this.store.adapterFor('application').set('host', 'http://catalog.peoplerunning.co');
    return RSVP.hash({
      styles: this.get('store').findAll('shirtstyle'),
      sizes: this.get('store').findAll('shirtsize'),
      colors: this.get('store').findAll('shirtcolor'),
      materials: this.get('store').findAll('shirtmaterial'),
      shirt: Ember.$.getJSON('http://catalog.peoplerunning.co/api/v1/shirts/' + params.shirt_id)
    });
  }
});
