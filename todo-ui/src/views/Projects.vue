<template>
    <div class="projects">
        <div class="text-center mt-10">
        <v-avatar color="indigo" size="100">
            <v-icon dark size="60">mdi-account-circle</v-icon>
        </v-avatar>
        </div>
        <v-container class="my-10">
           
           <v-layout row class="mb-3 ml-5">
               <v-btn small flat color="indigo lighten-5 ml-5 mt-3" @click="sortBy('name')">
                <v-icon left small>folder</v-icon>
                <span class="caption text-lowercase">Proje Adına Göre Sırala</span>
               </v-btn>
               <v-btn small flat color="indigo lighten-5 ml-5 mt-3" @click="sortBy('status')">
                <v-icon left small>person</v-icon>
                <span class="caption text-lowercase">Duruma Göre Sırala</span>
               </v-btn>
               <v-btn small flat color="indigo lighten-5 ml-5 mt-3" @click="sortBy('deadline')">
                <v-icon left small>person</v-icon>
                <span class="caption text-lowercase">Son Güne Göre Sırala</span>
               </v-btn>
           </v-layout>

            <v-text-field class="mx-4 mb-5" flat hide-details label="Search" prepend-inner-icon="search" solo-inverted></v-text-field>
            <v-spacer></v-spacer>
            <v-card flat ml-3 v-for="(project, index) in projects" :key="index">
                <v-layout row wrap ml-1 :class="`pa-3 project ${project.status}` ">
                    <v-flex xs12 md2>
                       <div class="caption grey--text">Proje Adı</div> 
                       <div>{{project.name}}</div>
                    </v-flex>
                    <v-flex xs6 md5>
                       <div class="caption grey--text">Proje Açıklaması</div> 
                       <div>{{project.description}}</div>
                    </v-flex>
                    
                    <v-flex xs3 md2>
                       <div class="caption grey--text">Son Günü</div> 
                       <div>{{project.deadline}}</div>
                    </v-flex>
                    
                    <v-flex xs3 md2>
                       <div v-if= "project.status">
                           <v-chip small style="background-color:#3cd1c2;" class="white--text caption my-2 mt-7">{{project.status}}</v-chip>
                       </div>
                       <div v-else>
                            <v-chip small style="background-color: tomato;" class="white--text caption my-2 mt-7">{{project.status}}</v-chip>
                       </div>
                    </v-flex>
                    <v-flex md1>
                               <v-btn class="ma-2" outlined small fab color="indigo">
                                   <router-link :to="{name: 'project-details', params: { project: project, id: project.id }}">
                                    <v-icon>mdi-pencil</v-icon>
                                    </router-link>
                                </v-btn>                    
                        <router-view @refreshData="refreshList"></router-view>
                    </v-flex>
                </v-layout>
                <v-divider></v-divider>    
            </v-card>
            <v-layout>
                <v-flex md4>
                </v-flex>
                <v-flex md4>
                    <AddProject />
                </v-flex>
                <v-flex md4>
                </v-flex>
            </v-layout>
        </v-container>
    </div>

</template>

<script>
import AddProject from "../components/AddProject";
import http from "../http-common";
export default {
    components: {AddProject},
    name: "projects",

    data() {
        return{
            projects: []
        };
    },
    methods: {
        retriveProjects() {
            http
                .get("/projects")
                .then(response => {
                    this.projects = response.data;
                    console.log(response.data);
                })
                .catch(e => {
                    console.log(e);
                });
        },
        refreshList() {
            this.retriveProjects();
        },
        sortBy(prop){
            this.projects.sort((a,b) => a[prop] < b[prop] ? -1 : 1)
        },
        
    },
    mounted() {
        this.retriveProjects();
    }
    
};
</script>

<style>

.project.true{
    border-left: 4px solid #3cd1c2;  
}
.project.false{
    border-left: 4px solid tomato;
}

.v-chip.true{
    background: #3cd1c2;
}
.v-chip.false{
    background: tomato;
}



</style>