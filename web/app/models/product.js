import DS from 'ember-data';

export default DS.Model.extend({
  totalPrice: DS.attr('number'),
	shirt: DS.attr('string'),
	printsInShirts: DS.attr(),
	textsInShirts: DS.attr()
});
