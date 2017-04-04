import DS from 'ember-data';

export default DS.Model.extend({
  shirtStyle: DS.attr('string'),
	shirtSize: DS.attr('string'),
	shirtColor: DS.attr('string'),
	shirtMaterial: DS.attr('string')
});
