import DS from 'ember-data';

export default DS.Model.extend({
  userName: DS.attr('string'),
  firtsName: DS.attr('string'),
  lastName: DS.attr('string'),
  email: DS.attr('string'),
  phoneNumber: DS.attr('string')
});
