import authHeader, { BASE_URL, HTTP } from "../http";

export default {
  allDonations() {
    return HTTP.get(BASE_URL + "/donation", { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  create(donation) {
    return HTTP.post(BASE_URL + "/donation", donation, {
      headers: authHeader(),
    }).then((response) => {
      console.log(response.data);
      return response.data;
    });
  },
  delete(id) {
    return HTTP.delete(BASE_URL + "/donation/" + id, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },
  edit(donation) {
    return HTTP.put(BASE_URL + "/donation", donation, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },
};
