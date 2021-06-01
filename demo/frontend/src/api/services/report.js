import { BASE_URL, HTTP } from "../http";
import getToken, { saveFile } from "@/api/utils";
export default {
  getPDF() {
    console.log("ciao");
    return HTTP.get(BASE_URL + "/export", {
      responseType: "blob",
      headers: getToken(),
    }).then((response) => {
      saveFile("PDF", response);
      return response.data;
    });
  },
};
