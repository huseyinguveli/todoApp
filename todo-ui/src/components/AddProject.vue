<template>
  <div class="text-center mt-5">
    <v-dialog v-model="dialog" width="500" height="400">    
        
        <template v-slot:activator="{ on }">
          <v-btn class="success" dark v-on="on">Yeni Proje Ekle</v-btn>
        </template>
        
        <v-card>
          
          <v-card-title class="headline grey lighten-2" primary-title>Yeni Bir Proje Ekle</v-card-title>
          <v-card-text>
            <div v-if="!submitted">
              <v-form class="px-3">
                  <v-text-field label="Name" prepend-icon="folder" id="name" required v-model="project.name" name="name"></v-text-field>
                  <v-textarea no-resize rows="2" label="Description" prepend-icon="edit" id="description" required v-model="project.description" name="description"></v-textarea>
                  <v-menu max-width="290">
                      <template v-slot:activator="{ on }">
                          <v-text-field :value="due" label="Due date" prepend-icon="mdi-calendar-range" v-on="on"></v-text-field>
                      </template>
                      <v-date-picker v-model="due"  id="deadline" required  name="deadline"></v-date-picker>
                  </v-menu>
                  <v-btn flat class="success mx-0 mt-3" v-on:click="saveProject">Projeyi Ekle</v-btn>
              </v-form>
              </div>
               <div v-if="submitted">
            <h4>Proje Eklemesi Başarılı!</h4>
            </div>
          
          </v-card-text>
          
         
        </v-card>
        
    </v-dialog>
  </div>
</template>

<script>
import http from "../http-common";

export default {
  name: "add-project",
  data() {
    return {
      dialog: false,
      title: '',
      description: '',
      due: null,
      project: {
        id:0,
        name: "",
        description: "",
        deadline: "",
        status: false
      },
      submitted: false
    };
  },
  methods: {
    saveProject() {
      var data = {
        name: this.project.name,
        description: this.project.description,
        deadline: this.due,
      };
      http
        .post("/projects", data)
        .then(response => {
          this.project.id = response.data.id;
          console.log(response.data);
          this.$router.push('/projects');
        })
        .catch(e => {
          console.log(e);
        });
        
      this.submitted = true;
    },
    mounted() {
        this.retriveProjects();
    },
     newProject() {
      this.submitted = false;
      this.project = {};
    }
  }
};
</script>