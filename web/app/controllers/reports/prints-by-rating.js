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
      /*var start_date = Ember.$('#start_date').val();
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
      }*/

      var url = 'http://localhost:8082/api/v1/reports/printsbyrating/';
      if(params != '')
        url = url + params;

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
