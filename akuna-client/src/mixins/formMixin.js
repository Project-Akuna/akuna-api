export default {
  methods: {
    customRules(name, rules) {
      let rulesArray = [];
      Object.keys(rules).map( (title) => {
        if (title == 'required' && rules['required']) rulesArray.push((v => !!v || name +' is required'))
        // if (title == 'counter') rulesArray.push((v => (v!= null && v.length <= rules[title]) || name+' must be less than '+rules[title]+' characters'))
        // if (title == 'counter') rulesArray.push((v => console.log(v)))
        if (title == 'email' && rules['email']) rulesArray.push((v => /.+@.+\..+/.test(v) || name +' must be valid'))
        if (title == 'match') rulesArray.push((v => rules['match'] == v || name+' must be match.'))
      })
      return rulesArray
    },
    capitalize(text) {
      if (text) {
        text = text.split(' ')
        .map((s) => s.charAt(0).toUpperCase() + s.substring(1))
        .join(' ');

        return text
      }
    }
  }
}