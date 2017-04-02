import DS from 'ember-data';

export default DS.JSONAPISerializer.extend({
  normalizeResponse(store, primaryModelClass, payload, id, requestType){
    var newData = [];
    var modelString = primaryModelClass.toString();
    var newType = modelString.substring(
      modelString.indexOf(":") + 1, modelString.lastIndexOf(":")
    );

    if(payload.content){  
      payload.content.forEach(function(item){
        var newItem = {
          id: item.id,
          type: newType,
          attributes: item
        };
        newData.push(newItem);
      });
      payload.data = newData;
      delete payload.content;
      return this._super(store, primaryModelClass, payload, id, requestType);
    }
    else{
      var newItem = {
        id: payload.id,
        type: newType,
        attributes: payload
      };
      var newPayload = [];
      newPayload.data = newItem;
      return this._super(store, primaryModelClass, newPayload, id, requestType);
    }
  },
  serialize: function(snapshot, options) {
    var json = snapshot.attributes();
    return json;
  },
  keyForAttribute(key) { return key; }
});
