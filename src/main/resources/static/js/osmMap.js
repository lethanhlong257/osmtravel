// initialize the map on the "map" div with a given center and zoom
const host = "http://www.localhost:8080";
const SUCCESS_STATUS = "success"
const OK_STATUS = "OK"
let places = [];
let currenntURL = window.location.href;
const hostingGeoCodingAPI = "https://maps.googleapis.com/maps/api/geocode/json"

// Account lethanhlong257 - need to change before submit code and defend
// Becareful to use because it is limited for request
const GOOGLE_MAP_API_KEY = "AIzaSyAi0Yb8-l2pZVk2MOo8U2p26q2y9PcaS4k.r3m0v3";


let defaultCoordHCM = [10.7743, 106.6669]; // coord mặc định, chinh giữa HCMC
let zoomLevel = 13;
let mapIdContainer = 'map'
let map = L.map(mapIdContainer, {attributionControl: false})
let marker;



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

  $("#form-advanced-search").submit(function (event) {
    event.preventDefault()
    const advancedSearchKeyword = $("#input-advanced-search").val()
    console.log(advancedSearchKeyword)
    if (advancedSearchKeyword === "") {
      alert("Search input is empty")
      return false
    }
    searchByGoogleGeocoding(advancedSearchKeyword, function (result) {
      places = result
      console.log(places)
      drawMap(places)
    })
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
  marker = null
  map.setView(defaultCoordHCM, zoomLevel)
  L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
    attribution: '© <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
  }).addTo(map);


  places.map(place => {
    marker = L.marker([place.lat, place.lon]).addTo(map);
    marker.bindPopup("<b>"+place.name+"</b><br>");
  })
}

function searchByGoogleGeocoding(keyword, callback) {
  const searchPlace = []
  const urlEncodeKeyword = encodeURI(keyword)
  const key = formatMapAPIKey(GOOGLE_MAP_API_KEY)
  const geocodingAPI = hostingGeoCodingAPI + `?key=${key}` + `&address=${urlEncodeKeyword}`
  $.get(geocodingAPI, function (data, status) {
    if (status !== SUCCESS_STATUS) {
      console.log("Error 02 call searchByGoogleGeocoding: " + status)
      callback(searchPlace)
    }
    if (data.status !== OK_STATUS) {
      console.log("Error 01 call searchByGoogleGeocoding: " + status)
      console.log(data)
      alert(`No result with keyword ${keyword}. Please search again`)
      callback(searchPlace)
    }

    let searchResult = data.results
    searchResult.forEach(result => {
      let point = {
        "address": result.formatted_address,
        "lat": result.geometry.location.lat,
        "lon": result.geometry.location.lng,
        "name": keyword + " - " +result.address_components[0].long_name
      }
      searchPlace.push(point)
    })
    callback(searchPlace)

    // if (status === SUCCESS_STATUS) {
    //   if (data.status !== OK_STATUS) {
    //     console.log("Error 01 call searchByGoogleGeocoding: " + status)
    //     console.log(data)
    //     alert(`No result with keyword ${keyword}. Please search again`)
    //     return false
    //   }
    //   let searchResult = data.results
    //   for (let i = 0; i < searchResult.length; i++) {
    //     let result = searchResult[i]
    //     let formattedAddress = result.formatted_address
    //     let point = {
    //       "address": formattedAddress,
    //       "lat": result.geometry.location.lat,
    //       "lon": result.geometry.location.lng,
    //       "name": keyword + " - " +result.address_components[0].long_name
    //     }
    //     searchPlace.push(point)
    //   }
    //   return searchPlace
    // } else {
    //   console.log("Error 02 call searchByGoogleGeocoding: " + status)
    //   return searchPlace
    // }
  })
}

// bởi vì uplen git là public Repository nên key cần chỉnh sửa xíu để tránh bị người khác dùng tool trộm xài
function formatMapAPIKey(api) {
  return GOOGLE_MAP_API_KEY.split(".")[0]
}