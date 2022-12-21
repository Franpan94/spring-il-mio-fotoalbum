<template>
  <main>
    
    <div class="container">
        <div class="row text-center ">
            <h1>Post</h1>
            <div class="col-12 p-4" v-for="photo in photos" :key="photo.id">
                <h3>{{ photo.title }}</h3>
                <img :src="photo.url" alt="img" class="w-50">
                <h4 class="pt-2">Descrizione: {{ photo.description }}</h4>
                <h4 class="pt-2">Tag: {{ photo.tag }}</h4>
                
                <div v-if="photo.categories" class="fs-4">
                    <div v-if="photo.categories.length < 1">
                        <span>Categorie non presenti</span>
                    </div>
                    <div v-else>
                        <span class="pt-2">Categorie: </span>
                        <span v-for="category in photo.categories" :key="category.id">
                           {{ category.name }},
                        </span>
                    </div>
                </div>
           

                <div class="pt-2" v-if="photo.visible">
                    <h4>Disponibile</h4>
                </div>
                <div v-else>
                    <h4 class="pt-2">Non disponibile</h4>
                </div>
            </div>
        </div>
    </div>
  </main>
</template>

<script>

import axios from 'axios';

const API_URL_PHOTO = 'http://localhost:8080/api/foto';

export default {

  name: 'MainComponent',

  data(){

    return {

        photos: [],
        query: ''
    }
  },

  methods: {

    getPhotos(){
        axios.get(API_URL_PHOTO + '/all').then(result => {
            
            const photos = result.data;
            
            if(photos == null) return

            this.photos = photos;
        })
    }
  },

  created(){
    this.getPhotos();
  }
}
</script>

<style>

</style>