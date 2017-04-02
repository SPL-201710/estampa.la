import Ember from 'ember';
import AuthenticatedRouteMixin from 'ember-simple-auth/mixins/authenticated-route-mixin';

export default Ember.Route.extend(AuthenticatedRouteMixin, {
  model() {
    this.store.adapterFor('application').set('host', 'http://users.peoplerunning.co');
    return this.get('store').findRecord('user', this.get('session.data.authenticated.user.id'));
  }
});
