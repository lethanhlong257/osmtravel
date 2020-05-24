// initialize the map on the "map" div with a given center and zoom

$(document).ready(function () {
  var host = "localhost:8080";
  var endpoint = "/api/v1.0/search";
  var parameter = "?keyword='Nguyen dinh chieu'";
  var places = [];
  addPlaces(places)
  drawMap(places);


  function addPlaces(places) {
    var url = host + endpoint + parameter;
   $.get("http://localhost:8080/api/v1.0/search?keyword=nguyen+dinh+chieu", function(data, status) {
     alert("Data: " + data + "\nStatus: " + status);
   })
  }

  function drawMap(places) {
    var defaultCoordHCM = [10.7743, 106.6669]; // coord mặc định, chinh giữa HCMC
    var zoomLevel = 13;
    var mapIdContainer = 'map'
    var map = L.map(mapIdContainer, {attributionControl: false}).setView(defaultCoordHCM, zoomLevel);

    L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
      attribution: '© <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);

    places.map(place => {
      var marker = L.marker([place.lat, place.lon]).addTo(map);
      marker.bindPopup("<b>"+place.name+"</b><br>");
    })
  }

});