<template lang="pug">
  section.sell-registration-code
    <v-btn class="float-right primary" to="sell-registration-code/add"><v-icon class="pr-1" small>mdi-plus</v-icon>Sell</v-btn>
    h3.d-block Sell Registration Code
    v-breadcrumbs.d-block.pa-0(:items="breadcrumbItems")
      template(v-slot:divider)
        v-icon mdi-chevron-right
    v-card.views-container.sell-registration-code.ma-0.mt-5
      h3.table-title Registration Codes
      v-data-table(
        :headers="headers"
        :items="regCodeList"
        multi-sort
      )
        template(v-slot:item.code="{ item }")
          v-tooltip(right v-if="!item.isUsed")
            template(v-slot:activator="{ on }")
              span.clickable(@click="copyTextToClipboard(item.code)" v-on="on") {{ item.code }}
            span Click to copy
          
          span.unclickable(v-if="item.isUsed") {{ item.code }}
        template(v-slot:item.dtimeCreated="{ item }") {{ item.dtimeCreated | moment("dddd, MMMM Do YYYY") }}
        template(v-slot:item.isUsed="{ item }") 
          v-chip(:color="item.isUsed==true ? 'primary': 'orange'" dark) {{ item.isUsed==true ? 'Used': 'Unused'}}

    v-snackbar(v-model="copySnackbar" :timeout="3000" color="success") {{ copySnackbarText }} copied!
      v-btn(text @click="copySnackbar = false") Close
          
</template>
<script>
import {mapState} from 'vuex'

export default {
  computed: mapState({
    axiosURL: 'axiosURL'
  }),
  data() {
    return {
      copySnackbar: false,
      copySnackbarText: '',
      breadcrumbItems: [
        {
          text: "Transaction",
          disabled: true,
          href: '',
        },
        {
          text: 'Sell Registration Code',
          disabled: false,
          href: '',
        },
      ],
      regCodeList: [],
      headers: [
        {
          text: 'Code',
          value: 'code'
        },
        {
          text: 'Is Used',
          value: 'isUsed'
        },
          {
              text: 'Date Created',
              value: 'dtimeCreated'
          },
      ],

    }
  },
  methods: {
    getRegCodeList() {
      this.axios.get(this.axiosURL+ 'api/registration/all', {
        auth: this.$session.get('auth')
      })
      .then(response => {
        this.regCodeList = response.data.payload;
      })
      .catch(response => {
        this.$swal('Opssss! Something went wrong', response.data, 'error');
      })
    },
    copyTextToClipboard(text) {
      let self = this;
      this.$copyText(text).then(function (e) {
          self.copySnackbar = true;
          self.copySnackbarText = text;
        }, function (e) {
          self.$swal('Opssss! Something went wrong', 'Unable to copy Registration Code.', 'error');
        })
    }
  },
  mounted() {
    this.getRegCodeList();
  }
}
</script>
<style lang="scss">

</style>