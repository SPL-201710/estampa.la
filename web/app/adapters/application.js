import DS from 'ember-data';
import ENV from 'estampala/config/environment';
import DataAdapterMixin from 'ember-simple-auth/mixins/data-adapter-mixin';

export default DS.JSONAPIAdapter.extend(DataAdapterMixin, {
  authorizer: 'authorizer:token',
  host: ENV.APP.API_HOST,
  headers: {
    'Accept': 'application/json',
    "crossDomain": true,
    "content-type": "application/json",
    "cache-control": "no-cache",
    "Access-Control-Allow-Origin": "*",
  },
  namespace: 'api/v1',
  methodForRequest({ requestType }) {
    if (requestType === "updateRecord") {
      return "PUT";
    }
    return this._super(...arguments);
  }
});
