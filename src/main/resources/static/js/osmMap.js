// initialize the map on the "map" div with a given center and zoom
const host = "http://www.localhost:8080";
const SUCCESS_STATUS = "success"
const OK_STATUS = "OK"
let places = [];
let currenntURL = window.location.href;
const hostingGeoCodingAPI = "https://maps.googleapis.com/maps/api/geocode/json"

// Account lethanhlong257 - need to change before submit code and defend
// Becareful to use because it is limited for request
const GOOGLE_MAP_API_KEY = "AIzaSyAi0Yb8-l2pZVk2MOo8U2p26q2y9PcaS4k";


let defaultCoordHCM = [10.7743, 106.6669]; // coord mặc định, chinh giữa HCMC
let zoomLevel = 13;
let mapIdContainer = 'map'
let map = L.map(mapIdContainer, {attributionControl: false}).setView(defaultCoordHCM, zoomLevel);




$(document).ready(function () {

  if (currenntURL.indexOf("/search") > -1) {
    var searchEndPoint = "/api/v1.0/search";
    var searchParams = getUrlParameter("keyword");
    var searchAPI = host + searchEndPoint + "?keyword=" + encodeURI(searchParams);
    $.get(searchAPI, function (searchResult, status) {
      if (status === SUCCESS_STATUS) {
        let searchList = searchResult.SearchList;
        for (let i = 0; i < searchList.length; i ++) {
          let result = searchList[i];
          let point = {
            name: result.name,
            lon: result.coordinates[0],
            lat: result.coordinates[1]
          }
          places.push(point)
        }
        drawMap(places);
      } else {
        drawMap(places);
      }
    })
  } else {
    drawMap(places);
  }

  $("#button-advanced-search").click(function (event) {
    event.preventDefault()
    const advancedSearchKeyword = $("#input-advanced-search").val()
    console.log(advancedSearchKeyword)
    if (advancedSearchKeyword === "") {
      alert("Search input is empty")
      return false
    }
    searchByGoogleGeocoding(advancedSearchKeyword)
  })

});

function getUrlParameter(sParam) {
  var sPageURL = window.location.search.substring(1),
      sURLVariables = sPageURL.split('&'),
      sParameterName,
      i;

  for (i = 0; i < sURLVariables.length; i++) {
    sParameterName = sURLVariables[i].split('=');

    if (sParameterName[0] === sParam) {
      return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
    }
  }
}

function drawMap(places) {
  L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
    attribution: '© <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
  }).addTo(map);

  places.map(place => {
    let marker = L.marker([place.lat, place.lon]).addTo(map);
    marker.bindPopup("<b>"+place.name+"</b><br>");
  })
}

function searchByGoogleGeocoding(keyword) {
  const urlEncodeKeyword = encodeURI(keyword)
  const geocodingAPI = hostingGeoCodingAPI + `?key=${GOOGLE_MAP_API_KEY}` + `&address=${urlEncodeKeyword}`
  console.log(geocodingAPI)
  $.get(geocodingAPI, function (data, status) {
    if (status === SUCCESS_STATUS) {
      if (data.status !== OK_STATUS) {
        console.log("Error 01 call searchByGoogleGeocoding: " + status)
        console.log(data)
        alert(`No result with keyword ${keyword}. Please search again`)
        return false
      }
      let searchResult = data.results
      if (searchResult.length > 0) {
        places = []
      }
      for (let i = 0; i < searchResult.length; i++) {
        let result = searchResult[i]
        let formattedAddress = result.formatted_address
        let point = {
          "address": formattedAddress,
          "lat": result.geometry.location.lat,
          "lon": result.geometry.location.lng,
          "name": result.address_components[0].long_name
        }
        places.push(point)
      }
      console.log(places)
      drawMap(places)
    } else {
      console.log("Error 02 call searchByGoogleGeocoding: " + status)
      console.log(data)
      drawMap(places)
    }
  })
}