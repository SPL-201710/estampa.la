import DS from 'ember-data';
import ENV from 'estampala/config/environment';

export default DS.JSONAPIAdapter.extend({
  host: ENV.APP.API_HOST,
  headers: {
    'Accept': 'application/json',
    "crossDomain": true,
    "content-type": "application/json",
    "cache-control": "no-cache",
    "Access-Control-Allow-Origin": "*"
  },
  namespace: 'api/v1',
  methodForRequest({ requestType }) {
    if (requestType === "updateRecord") {
      return "PUT";
    }
    return this._super(...arguments);
  }
});
