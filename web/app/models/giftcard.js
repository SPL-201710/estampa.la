import DS from 'ember-data';

export default DS.Model.extend({
  buyer: DS.attr('string'),
  receiver: DS.attr('string'),
  initialBalance: DS.attr('number'),
  balance: DS.attr('number'),
  pse_method: DS.attr()
});
