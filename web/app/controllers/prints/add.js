import Ember from 'ember';

export default Ember.Controller.extend({
  theme: "",
  actions: {
    selectTheme: function(selectedTheme){
      this.set('theme', selectedTheme);
    },
    addPrint: function(){
      var reader = new FileReader();
      var imageBase64 = "";
      var self = this;
      
      reader.onloadend = function () {
        imageBase64 = reader.result.replace(/^data:image\/[a-z]+;base64,/, "");
        let newPrint = self.get('store').createRecord('print', {
          name: self.get('name'),
          description: self.get('description'),
          rating: 0,
          popularity: 0,
          price: parseInt(self.get('price')),
          theme: self.get('theme'),
          image: imageBase64,
          owner: "b14e9a08-6c85-4711-8e53-c55e7b5312df",
          ownerUsername: "farruza"
        });

        newPrint.save();
      };

      var file = Ember.$("#image")[0].files[0];
      if (file) {
        reader.readAsDataURL(file);
      }
    }
  }
});
