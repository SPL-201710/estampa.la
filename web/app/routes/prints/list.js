import Ember from 'ember';
import AuthenticatedRouteMixin from 'ember-simple-auth/mixins/authenticated-route-mixin';

export default Ember.Route.extend(AuthenticatedRouteMixin, {
  model() {
    this.store.adapterFor('application').set('host', 'http://localhost:8080');
    return this.get('store').query('print', {
        owner: this.get('session.data.authenticated.user.username')
    });
  }
});
