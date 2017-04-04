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

  this.route('shirtsizes.list', {path: '/tallas/'});
  this.route('shirtsizes.add', {path: '/tallas/crear'});
  this.route('shirtsizes.edit', {path: '/tallas/editar/:shirtsize_id'});

  this.route('themes.list', {path: '/temas/'});
  this.route('themes.add', {path: '/temas/crear'});
  this.route('themes.edit', {path: '/temas/editar/:theme_id'});

  this.route('shirtmaterials.list', {path: '/materiales/'});
  this.route('shirtmaterials.add', {path: '/materiales/crear'});
  this.route('shirtmaterials.edit', {path: '/materiales/editar/:shirtmaterial_id'});

  this.route('printfonts.list',{path: '/tiposletra/'});
  this.route('printfonts.add',{path: '/tiposletra/crear'});
  this.route('printfonts.edit',{path: '/tiposletra/editar/:printfont_id'});

  this.route('roles.list',{path: '/roles/'});
  this.route('roles.add',{path: '/roles/crear'});
  this.route('roles.edit',{path: '/roles/editar/:role_id'});

  this.route('shirtcolors.list',{path: '/colores/'});
  this.route('shirtcolors.add',{path: '/colores/crear'});
  this.route('shirtcolors.edit',{path: '/colores/editar/:shirtcolor_id'});

  this.route('shirtprintpositions.list',{path: '/posicionestampas/'});
  this.route('shirtprintpositions.add',{path: '/posicionestampas/crear'});
  this.route('shirtprintpositions.edit',{path: '/posicionestampas/editar/:shirtprintposition_id'});

  this.route('shirts.list',{path: '/camisetas/'});
  this.route('shirts.add',{path: '/camisetas/crear'});
  this.route('shirts.edit',{path: '/camisetas/editar/:shirt_id'});

  this.route('product.add', {path: '/producto/'});
  this.route('cart');
  this.route('payment');
});

export default Router;
