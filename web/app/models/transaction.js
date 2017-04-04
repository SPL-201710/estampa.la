import DS from 'ember-data';

export default DS.Model.extend({
  transactionUser: DS.attr('string'),
	shoppingCart: DS.attr('string'),
	date: DS.attr('string')
});
