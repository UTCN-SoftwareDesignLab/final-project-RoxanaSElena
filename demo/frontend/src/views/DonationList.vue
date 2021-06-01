<template>
  <v-card>
    <v-card-title>
      Donations
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
      <v-btn @click="addDonation">Add Donation</v-btn>
      <v-btn @click="switchView">Institutions</v-btn>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="donation"
      :search="search"
      @click:row="editDonation"
    ></v-data-table>
    <DonationDialog
      :opened="dialogVisible"
      :donation="selectedDonation"
      @refresh="refreshList"
    ></DonationDialog>
  </v-card>
</template>

<script>
import DonationDialog from "../components/DonationDialog";
import api from "../api";
import router from "../router";

export default {
  name: "DonationList",
  components: { DonationDialog },
  data() {
    return {
      donation: [],
      search: "",
      headers: [
        {
          text: "Donation",
          align: "start",
          value: "text",
        },
        { text: "Expiration Date", value: "expirationDate" },
        { text: "Donor", value: "donorId" },
        { text: "Email", value: "donorEmail" },
        { text: "Address", value: "donorAddress"},
        { text: "Institution", value: "institutionId" },
        { text: "Quatity", value: "quantity" },
        { text: "Transportation", value: "needTransportation" },
      ],
      dialogVisible: false,
      selectedDonation: {},
    };
  },
  methods: {
    editDonation(donation) {
      this.selectedDonation = donation;
      this.dialogVisible = true;
    },
    addDonation() {
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedDonation = {};
      this.donation = await api.donations.allDonations();
    },
    switchView() {
      router.push("/institution");
    },
  },
  created() {
    this.refreshList();
    api.websockets.connectAndSubscribe();
  },
};
</script>

<style scoped></style>
