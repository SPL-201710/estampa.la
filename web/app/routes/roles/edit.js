import Ember from 'ember';
import AuthenticatedRouteMixin from 'ember-simple-auth/mixins/authenticated-route-mixin';

export default Ember.Route.extend(AuthenticatedRouteMixin, {
  model(params) {
    this.store.adapterFor('application').set('host', 'http://users.soybackend.com');
    return this.get('store').findRecord('role', params.role_id);
  }
});
