import authHeader, { BASE_URL, HTTP } from "../http";

export default {
  allInstitutions() {
    return HTTP.get(BASE_URL + "/institution", { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  create(institution) {
    return HTTP.post(BASE_URL + "/institution", institution, {
      headers: authHeader(),
    }).then((response) => {
      console.log(response.data);
      return response.data;
    });
  },
  edit(institution) {
    return HTTP.put(BASE_URL + "/institution", institution, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },
};
