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

      //?start_date=2017/05/01&end_date=2017/05/21
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

      var user = Ember.$("#user option:selected").val();
      if(user != '') {
        var url = 'http://localhost:8082/api/v1/reports/salesbyartist/' + user;
        if(params != '')
          url = url + params;

          Ember.$.getJSON(url, function(result) {
            var dataset = [];
            result.forEach(function(data) {
              var row = [];
              row.push(data.printName);
              row.push("<a href='" + data.printImage + "'>Clic para consultar</a>");
              row.push(data.quantitySold);
              row.push(data.totalSold);
              dataset.push(row);
            });

            Ember.$('#data').DataTable( {
                data: dataset,
                columns: [
                  { title: "Nombre" },
                  { title: "Imagen" },
                  { title: "Cantidad" },
                  { title: "Total" }
                ]
            });
            self.set('initTable', true);
          });
      }
    }
  }
});
