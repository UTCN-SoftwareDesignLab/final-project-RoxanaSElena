import authHeader, { BASE_URL, HTTP } from "../http";

export default {
  create(lat, lng) {
    return HTTP.post(BASE_URL + "/addlocation", lat, lng, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },
};
