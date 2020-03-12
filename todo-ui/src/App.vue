<template>
  <v-app style="background-color:#E8EAF6;">
  <Navbar/>
    <v-content>
      <router-view/>
    </v-content>
  </v-app>
</template>

<script>
import Navbar from '@/components/Navbar'
export default {
  name: 'App',
  components: {Navbar},
  data () {
    return {

    }
    //
  }
}
</script>
