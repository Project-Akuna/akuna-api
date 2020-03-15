<template>
  <section class="add-depot-view">
    <h3 class="d-block">Add Depot</h3>
    <v-breadcrumbs :items="breadcrumbItems" class="d-block pa-0">
        <template v-slot:divider>
            <v-icon>mdi-chevron-right</v-icon>
        </template>
    </v-breadcrumbs>
    <v-card class="views-container ma-0 mt-5">
      <h3 class="table-title">Enter Depot Information</h3>
      <v-form ref="addDepotForm" v-model="valid" lazy-validation>
        <v-row class="py-6">
          <v-col class="add-form__input-container" cols="12" md="6">
            <!-- User Select -->
            <v-select
              v-model="selectedUser"
              dense
              label="User Account"
              :items="userList"
              item-text="username"
              :rules="customRules('User Account',{ required: true })"
            ></v-select>

            <!-- Depot Code -->
            <!-- <v-text-field
              dense
              v-model="depotInfo.code"
              clearable
              :counter="100"
              label="Depot Code"
              :rules="customRules('Depot Code',{ required: true, counter: 100 })"
            ></v-text-field> -->

            <!-- Registration Code -->
            <!-- <v-text-field
              dense
              v-model="depotInfo.regCode"
              clearable
              :counter="100"
              label="Registration Code"
              :rules="customRules('Registration Code',{ counter: 100 })"
            ></v-text-field> -->

            <!-- Office Address -->
            <v-text-field
              dense
              v-model="depotInfo.officeAddress"
              clearable
              :counter="100"
              label="Office Address"
              :rules="customRules('Office Address',{ required: true, counter: 100 })"
            ></v-text-field>
          </v-col>

          <!-- Auto Complete upon user selection -->
          <v-col class="add-form__input-container" cols="12" md="6">
            <!-- Name -->
            <v-text-field
              disabled
              dense
              v-model="user.name"
              clearable
              :counter="100"
              label="Name"
            ></v-text-field>

            <!-- Landline Number 1  -->
            <v-text-field
              disabled
              type="number"
              dense
              v-model="user.landLineNumber1"
              clearable
              :counter="100"
              label="Landline Number 1"
            ></v-text-field>

            <!-- Landline Number 2  -->
            <v-text-field
              disabled
              type="number"
              dense
              v-model="user.landLineNumber2"
              clearable
              :counter="100"
              label="Landline Number 2"
            ></v-text-field>

            <!-- Mobile Number 1 -->
            <v-text-field
              disabled
              type="number"
              dense
              v-model="user.mobileNumber1"
              clearable
              :counter="100"
              label="Mobile Number 1"
            ></v-text-field>

            <!-- Mobile Number 2 -->
            <v-text-field
              disabled
              type="number"
              dense
              v-model="user.mobileNumber2"
              clearable
              :counter="100"
              label="Mobile Number 2"
            ></v-text-field>

            <!-- Email -->
            <v-text-field
              disabled
              dense
              v-model="user.email"
              clearable
              :counter="100"
              label="Email"
            ></v-text-field>

            <!-- City -->
            <v-text-field
              disabled
              dense
              v-model="user.city"
              clearable
              :counter="100"
              label="City"
            ></v-text-field>
          </v-col>

          <!-- Form Buttons -->
          <v-col class="add-form__input-container pt-5 text-right" cols="12">
            <v-btn class="mr-4" depressed color="white">Cancel</v-btn>
            <v-btn class="primary" @click="addDepot">Add ADC</v-btn>
          </v-col>
        </v-row>
      </v-form>
    </v-card>
  </section>
</template>

<script>
import FormMixin from '../../../mixins/formMixin'

import { mapState } from 'vuex'

export default {
  mixins: [ FormMixin ],
  computed: mapState({
    axiosURL: 'axiosURL'
  }),
  data() {
    return {
      breadcrumbItems: [
        {
          text: "Member's Files",
          disabled: true,
          href: '',
        },
        {
          text: 'Depot',
          disabled: false,
          href: '/depot',
        },
        {
          text: 'Add',
          disabled: false,
          href: '',
        },
      ],
      valid: false,
      depotInfo: {},
      userList: [],
      selectedUser: '',
      user: {}
    }
  },
  methods: {
    getUsers() {
      let self = this;
      this.axios.post(this.axiosURL + 'api/user/get-users', {
        auth: self.$session.get('auth')
      })
      .then(response => {
        self.userList = response.data.payload;
      })
      .catch(function (error) { self.$swal('Opssss! Something went wrong', error.data, 'error'); });
    },
    addDepot() {
      let self = this;
      if (this.$refs.addDepotForm.validate()) {
        console.log(self.user)

      }
    },
    getUserByUsername() {
      let self = this
      // Axios get request for getting user by username
      this.axios.get(self.axiosURL+'api/user/get-user/'+self.selectedUser, {
        auth: self.$session.get('auth'),
      })
      .then( response => {
        self.user = response.data.payload;
      })
      .catch( response => {
        this.$swal('Opssss! Something went wrong', response.data, 'error');
      })
    },
  },
  watch: {
    selectedUser: function(newUser, oldUser) {
      this.getUserByUsername();
    },
  },
  mounted() {
    this.getUsers();
  }
}
</script>

<style lang="scss">
.add-depot-view {
  .add-form__input-container {
    margin: 0;
  }
}
</style>