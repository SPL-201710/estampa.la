import DS from 'ember-data';

export default DS.JSONAPISerializer.extend({
  normalizeResponse(store, primaryModelClass, payload, id, requestType){
    var newData = [];
    var modelString = primaryModelClass.toString();
    var newType = modelString.substring(
      modelString.indexOf(":") + 1, modelString.lastIndexOf(":")
    );

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
  },
  serialize: function(snapshot, options) {
    var json = snapshot.attributes();
    // debugger;
    // snapshot.eachRelationship(function(name, relationship) {
    //   console.log(snapshot.hasMany('themes'));
    // });
    return json;
  },
  keyForAttribute(key) { return key; }
});
