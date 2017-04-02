import Ember from 'ember';

export function role(params) {
  return params[0].roles[0].name;
}

export default Ember.Helper.helper(role);
