<template>
    <v-layout>
        <v-flex>
           <v-btn icon color="red" v-on:click="deleteProject">
              <v-icon>mdi-delete</v-icon>
            </v-btn>
        </v-flex>
        <v-flex>
            <v-btn icon color="green" v-if="this.project.status" v-on:click="updateActive(false)">
              <v-icon>mdi-thumb-up</v-icon>
            </v-btn>
            <v-btn icon color="green"  v-else v-on:click="updateActive(true)">
              <v-icon>mdi-thumb-up</v-icon>
            </v-btn>
          
        </v-flex>
    </v-layout>
</template>


<script>
import http from "../http-common";
 
export default {
  name: "project",
  props: ["project"],
  methods: {
    /* eslint-disable no-console */
    updateActive(a) {
      var data = {
        id: this.project.id,
        name: this.project.name,
        description: this.project.description,
        deadline: this.project.deadline,
        status: a
      };
 
      http
        .put("/projects/" + this.project.id, data)
        .then(response => {
          this.project.status = response.data.status;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    deleteProject() {
      http
        .delete("/projects/" + this.project.id)
        .then(response => {
          console.log(response.data);
          this.$emit("refreshData");
          this.$router.push('/');
        })
        .catch(e => {
          console.log(e);
        });
    }
    /* eslint-enable no-console */
  }
};
</script>