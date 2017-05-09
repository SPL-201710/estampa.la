import Ember from 'ember';
import SessionService from 'ember-simple-auth/services/session';

export default Ember.Controller.extend({
  session: Ember.inject.service('session'),
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
        var imgpath = Ember.$('#image').val();
        var imgfile = imgpath.substr(imgpath.lastIndexOf('\\')+1);
        var imgext = imgfile.substr(imgfile.lastIndexOf('.')+1);
        self.store.adapterFor('application').set('host', 'http://catalog.soybackend.com');
        let newPrint = self.get('store').createRecord('print', {
          name: self.get('name'),
          description: self.get('description'),
          rating: 0,
          popularity: 0,
          price: parseInt(self.get('price')),
          theme: self.get('theme'),
          image: imageBase64,
          imageExtension: imgext,
          owner: self.get('session.data.authenticated.user.id'),
        });

        function transitionToIndex () {
          alert("Estampa creada");
          self.transitionToRoute('index');
        }

        function failure (reason) {
          alert(reason);
        }

        newPrint.save().then(transitionToIndex).catch(failure);
      };

      var file = Ember.$("#image")[0].files[0];
      if (file) {
        reader.readAsDataURL(file);
      }
    }
  }
});
