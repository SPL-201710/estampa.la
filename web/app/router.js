import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType,
  rootURL: config.rootURL
});

Router.map(function() {
  this.route('prints.list', { path: '/estampas/' });
  this.route('prints.add', { path: '/estampas/crear' });
  this.route('login');
});

export default Router;
