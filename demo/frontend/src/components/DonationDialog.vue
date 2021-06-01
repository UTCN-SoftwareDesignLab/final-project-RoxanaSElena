<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create Donation" : "Edit" }}
        </v-toolbar>
        <v-form>
          <v-text-field
            v-model="donation.expirationDate"
            label="ExpirationDate"
          />
          <v-text-field v-model="donation.donorId" label="DonorId" />
          <v-text-field v-model="donation.donorEmail" label="DonorEmail" />
          <v-text-field
            v-model="donation.institutionId"
            label="InstitutionId"
          />
          <v-text-field v-model="donation.donation" label="Donation" />
          <v-text-field v-model="donation.quantity" label="Quantity" />
          <v-text-field
            v-model="donation.needTransportation"
            label="Need Transport"
          />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
          <v-btn @click="deletedonation">Delete</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "donationDialog",
  props: {
    donation: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.donations
          .create({
            expirationDate: this.donation.expirationDate,
            donation: this.donation.text,
            donorId: this.donation.donorId,
            donorEmail: this.donation.donorEmail,
            institutionId: this.donation.institutionId,
            quantity: this.donation.quantity,
            needTransportation: this.donation.needTransportation,
          })
          .then(() => this.$emit("refresh"));
      } else {
        api.donations
          .edit({
            id: this.donation.id,
            expirationDate: this.donation.expirationDate,
            donation: this.donation.text,
            donorId: this.donation.donorId,
            institutionId: this.donation.institutionId,
            quantity: this.donation.quantity,
            needTransportation: this.donation.needTransportation,
          })
          .then(() => this.$emit("refresh"));
      }
    },
    deletedonation() {
      api.donations.delete(this.donation.id).then(() => this.$emit("refresh"));
    },
  },
  computed: {
    isNew: function () {
      return !this.donation.id;
    },
  },
};
</script>

<style scoped></style>
