import DS from 'ember-data';

export default DS.Model.extend({
  name: DS.attr('string'),
  hightInCentimeters: DS.attr('number'),
  widthInCentimeters: DS.attr('number')
});
