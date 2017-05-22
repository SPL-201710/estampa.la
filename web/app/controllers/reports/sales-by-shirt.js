import Ember from 'ember';

export default Ember.Controller.extend({
  initTable: false,
  session: Ember.inject.service('session'),
  actions: {
    loadData: function(){
      var self = this;
      if(this.get('initTable')) {
        var table = Ember.$('#data').DataTable();
        table.clear().draw();
        table.destroy();
      }

      var params = '';
      var start_date = Ember.$('#start_date').val();
      var end_date = Ember.$('#end_date').val();
      if(start_date != '') {
        params = '?start_date=' + start_date.replace(/-/g , "/");
      }

      if(end_date != '') {
        if(params == '')
          params = '?end_date=' + end_date.replace(/-/g , "/");
        else {
          params = params + '&end_date=' + end_date.replace(/-/g , "/");
        }
      }

      var material = Ember.$("#material option:selected").val();
      if(material != '') {
        if(params == '')
          params = '?id_material=' + material;
        else {
          params = params + '&id_material=' + material;
        }
      }

      var color = Ember.$("#color option:selected").val();
      if(color != '') {
        if(params == '')
          params = '?id_color=' + color;
        else {
          params = params + '&id_color=' + color;
        }
      }

      var size = Ember.$("#size option:selected").val();
      if(size != '') {
        if(params == '')
          params = '?id_size=' + size;
        else {
          params = params + '&id_size=' + size;
        }
      }

      var style = Ember.$("#style option:selected").val();
      if(style != '') {
        if(params == '')
          params = '?id_style=' + style;
        else {
          params = params + '&id_style=' + style;
        }
      }

      var url = 'http://34.225.45.212/api/v1/reports/salesbyshirt/';
      if(params != '')
        url = url + params;

        console.log(url);

        Ember.$.getJSON(url, function(result) {
          var dataset = [];
          result.forEach(function(data) {
            var row = [];
            row.push(data.shirtStyleName);
            row.push("<a href='" + data.shirtStyleImage + "'>Clic para consultar</a>");
            row.push(data.shirtColorName);
            row.push(data.shirtMaterialName);
            row.push(data.quantity);
            row.push(data.totalSold);
            row.push(data.date);
            dataset.push(row);
          });

          Ember.$('#data').DataTable( {
              data: dataset,
              columns: [
                { title: "Estilo" },
                { title: "Imagen" },
                { title: "Color" },
                { title: "Material" },
                { title: "Cantidad" },
                { title: "Total" },
                { title: "Fecha" }
              ]
          });
          self.set('initTable', true);
        });
    }
  }
});
