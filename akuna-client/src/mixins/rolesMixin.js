export default {
  methods: {
    checkRoleExists(roles) {
      let hasRole = false
      roles.forEach( (role) => {
        if (this.$session.get('account').roles.some((v)=>{ return v.id == role })) hasRole = true
      })
      return hasRole
    },
  }
}