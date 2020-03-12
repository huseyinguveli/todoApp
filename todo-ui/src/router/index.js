import Vue from 'vue'
import VueRouter from 'vue-router'
import Projects from '../views/Projects.vue'
import Project from '../views/Project.vue'

Vue.use(VueRouter)

export default new VueRouter({
  mode: "history",
  routes: [
    {
      path: "/",
      name: "projects",
      alias: "/projects",
      component: Projects,
      children: [
        {
          path: "/project/:id",
          name: "project-details",
          component: Project,
          props:true
        }
      ]
      }

  ]
});
