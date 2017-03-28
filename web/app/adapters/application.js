import DS from 'ember-data';
import ENV from 'estampala/config/environment';

export default DS.JSONAPIAdapter.extend({
  host: ENV.APP.API_HOST,
  headers: {
    'Accept': 'application/json',
    'Access-Control-Allow-Origin': '*'
  },
  namespace: 'api/v1'
});
