import DS from 'ember-data';

export default DS.Model.extend({
  name: DS.attr('string'),
  print: DS.belongsTo('print')
});
