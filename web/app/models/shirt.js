import DS from 'ember-data';

export default DS.Model.extend({
	style: DS.belongsTo('shirtstyle'),
	size: DS.belongsTo('shirtsize'),
	color: DS.belongsTo('shirtcolor'),
	material: DS.belongsTo('shirtmaterial')
});
