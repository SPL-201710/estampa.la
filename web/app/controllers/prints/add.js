import Ember from 'ember';

export default Ember.Controller.extend({
  // theme
  actions: {
    addPrint: function(){
      var reader = new FileReader();
      var imageBase64 = "";
      var self = this;
      debugger;
      reader.onloadend = function () {
        imageBase64 = reader.result.replace(/^data:image\/[a-z]+;base64,/, "");
        let print = self.get('store').createRecord('print', {
          name: self.get('name'),
          description: self.get('description'),
          // rating: 0,
          // popularity: 0,
          price: self.get('price'),
          theme: self.get('theme'),
          image: imageBase64
        });
        print.save();
      };

      var file = Ember.$("#image")[0].files[0];
      if (file) {
        reader.readAsDataURL(file);
      }
    }
  }
});
