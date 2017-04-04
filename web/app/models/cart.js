import DS from 'ember-data';

export default DS.Model.extend({
  subtotal: DS.attr('number'),
	shippingValue: DS.attr('number'),
	total: DS.attr('number'),
	cartProducts: DS.attr()
});
