import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType,
  rootURL: config.rootURL
});

Router.map(function() {
  this.route('prints.list', { path: '/estampas/' });
  this.route('prints.add', { path: '/estampas/crear' });
  this.route('prints.edit', { path: '/estampas/editar/:print_id' });

  this.route('users.login', { path: '/usuarios/login' });
  this.route('users.add', { path: '/usuarios/crear' });
  this.route('users.edit', { path: '/usuarios/cuenta' });

  this.route('shirtstyles.list', {path: '/estilos/'});
  this.route('shirtstyles.add', {path: '/estilos/crear'});
  this.route('shirtstyles.edit', { path: '/estilos/editar/:shirtstyle_id' });
});

export default Router;
