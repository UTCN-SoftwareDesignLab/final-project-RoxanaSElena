<template>
  <v-card>
    <v-card-title>
      Institutions
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
      <v-btn @click="addInstitution">Add Instituion</v-btn>
      <v-btn @click="switchView">Back</v-btn>
      <v-btn @click="institutionPDF">Institutions List</v-btn>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="institution"
      :search="search"
      @click:row="editInstitution"
    ></v-data-table>
    <div>
      <iframe
        width="450"
        height="250"
        frameborder="0"
        style="border: 0"
        src="https://www.google.com/maps/embed/v1/place?key=I&q=Fagaras-Brasov+Romania"
        allowfullscreen
      >
      </iframe>
    </div>
    <InstitutionDialog
      :opened="dialogVisible"
      :institution="selectedInstitution"
      @refresh="refreshList"
    ></InstitutionDialog>
  </v-card>
</template>

<script>
import api from "../api";
import router from "../router";
import InstitutionDialog from "../components/InstitutionDialog";
export default {
  name: "InstitutionList",
  components: { InstitutionDialog },
  data() {
    return {
      institution: [],
      search: "",
      headers: [
        {
          text: "Name",
          align: "start",
          sortable: false,
          value: "name",
        },
        { text: "Address", value: "address" },
        { text: "inNeed", value: "needDonation" },
      ],
      dialogVisible: false,
      selectedInstitution: {},
    };
  },
  methods: {
    editInstitution(instituion) {
      this.selectedInstitution = instituion;
      this.dialogVisible = true;
    },
    institutionPDF() {
      api.report.getPDF();
    },
    addInstitution() {
      this.dialogVisible = true;
    },
    switchView() {
      router.back();
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedUser = {};
      this.institution = await api.institutions.allInstitutions();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>
<style scoped></style>
