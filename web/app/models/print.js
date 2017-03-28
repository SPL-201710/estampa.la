import DS from 'ember-data';

export default DS.Model.extend({
  description: DS.attr('string'),
  image: DS.attr('string'),
  name: DS.attr('string'),
  popularity: DS.attr('number'),
  price: DS.attr('number'),
  rating: DS.attr('number'),
  theme: DS.attr('string'),
  owner: DS.attr('string'),
  ownerUsername: DS.attr('string')
});
