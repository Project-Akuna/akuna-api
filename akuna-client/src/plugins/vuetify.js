import Vue from 'vue';
import Vuetify from 'vuetify/lib';

import colors from 'vuetify/lib/util/colors'

Vue.use(Vuetify);

export default new Vuetify({
  theme: {
    themes: {
      light: {
        primary: '#339a2a', // #a8de85 (Light Green)
        secondary: colors.green.lighten4, // #FFCDD2
      },
    },
  },
});
