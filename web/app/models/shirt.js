import DS from 'ember-data';

export default DS.Model.extend({
	style: DS.attr(),
	size: DS.attr(),
	color: DS.attr(),
	material: DS.attr()
});
