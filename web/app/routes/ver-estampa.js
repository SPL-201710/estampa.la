import Ember from 'ember';

export default Ember.Route.extend({
  model(params) {    
    this.store.adapterFor('application').set('host', 'http://catalog.soybackend.com');
    return this.get('store').find('print', params.id);
  }
});
