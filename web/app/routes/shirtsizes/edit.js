import Ember from 'ember';
import AuthenticatedRouteMixin from 'ember-simple-auth/mixins/authenticated-route-mixin';

export default Ember.Route.extend(AuthenticatedRouteMixin, {
  model(params) {
    this.store.adapterFor('application').set('host', 'http://catalog.peoplerunning.co');
    return this.get('store').findRecord('shirtsize', params.shirtsize_id);
  }
});
