const host = "http://www.localhost:8080";
const SUCCESS_STATUS = "success"
const OK_STATUS = "OK"


$(document).ready(function () {
  $("#form-quick-add").submit(function (event) {
    event.preventDefault()
    let keyword = $("#input-quickadd").val()
    searchByGoogleGeocoding(keyword, function (result) {
      let place = result[0]
      let addressObj = seperateAddress(place.address)
      $("#input-place-name").val(place.name)
      $("#input-place-lat").val(place.lat)
      $("#input-place-lon").val(place.lon)
      $("#input-place-street").val(place.address)
      $("#input-place-country").val(addressObj.country)
      $("#input-place-city").val(addressObj.city)
      $("#input-place-name").focus()

    })
  })

})

function searchByGoogleGeocoding(keyword, callback) {
  const searchPlace = []
  const hostingGeoCodingAPI = "https://maps.googleapis.com/maps/api/geocode/json"
  const urlEncodeKeyword = encodeURI(keyword)
  const GOOGLE_MAP_API_KEY = "AIzaSyAi0Yb8-l2pZVk2MOo8U2p26q2y9PcaS4k.r3m0v3";
  const key = formatMapAPIKey(GOOGLE_MAP_API_KEY)
  const geocodingAPI = hostingGeoCodingAPI + `?key=${key}` + `&address=${urlEncodeKeyword}`
  console.log(geocodingAPI)
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
    console.log(searchResult)
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

  })
}

// bởi vì up len git là public Repository nên key cần chỉnh sửa xíu để tránh bị người khác dùng tool trộm xài
function formatMapAPIKey(api) {
  return api.split(".")[0]
}

function seperateAddress(address) {
  let addressPart = address.split(",")
  let addressObj = {}

  if (address.indexOf("Việt Nam")) {
    addressObj = {...addressObj, country: "Việt Nam"}
  }
  if (address.indexOf("Hồ Chí Minh")) {
    addressObj = {...addressObj, city: "Hồ Chí Minh"}
  }
  return addressObj
}

function removeLocation(id, name) {
  if (confirm(`Do you want to remove this location - id: ${id}?`)) {
    $.ajax({
      url: `/api/admin/location/remove/${id}`,
      type: 'DELETE',
      success: function() {
        alert(`Removed location ${name}`)
        location.reload()
      }
    });
  }
}