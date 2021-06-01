<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create Institution" : "Edit Institution" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="institution.name" label="Name" />
          <v-text-field
            v-model="institution.needDonation"
            label="need Donation"
          />
          <v-text-field v-model="institution.address" label="Address" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "InstitutionDialog",
  props: {
    institution: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.institutions
          .create({
            name: this.institution.name,
            address: this.institution.address,
            needDonation: this.institution.needDonation,
          })
          .then(() => this.$emit("refresh"));
      } else {
        api.institutions
          .edit({
            name: this.institution.name,
            address: this.institution.address,
            needDonation: this.institution.needDonation,
          })
          .then(() => this.$emit("refresh"));
      }
    },
  },
  computed: {
    isNew: function () {
      return !this.institution.id;
    },
  },
};
</script>
<style scoped></style>
