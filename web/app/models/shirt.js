import DS from 'ember-data';

export default DS.Model.extend({
	style: DS.attr('string'),
	size: DS.attr('string'),
	color: DS.attr('string'),
	material: DS.attr('string')
});
