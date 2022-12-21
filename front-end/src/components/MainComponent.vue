<template>
  <main>
    
    <div class="container">
        <div class="row text-center ">
            <h1>Post</h1>
            <div>
                <input type="text" v-model.trim="query" @keyup="search()">
                <button @click="search()">Cerca</button>
            </div>
            <div v-if="photos.length > 0">
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
                    

                    <div v-if="photo.comments" class="fs-4">
                        <div v-if="photo.comments.length < 1">
                            <span>Commenti non presenti</span>
                        </div>
                        <div v-else>
                            <span class="pt-2">Commenti: </span>
                            <span v-for="comment in photo.comments" :key="comment.id">
                            {{ comment.text }},
                            </span>
                        </div>
                    </div>
                    
                    <div class="pt-2">
                        <button @click="commentPhoto(photo.id)" v-if="photo.id !== photo_id" class="btn btn-primary">Commenta</button>
                        <div v-else>
                        <input type="text" placeholder="Inserisci commento" name="text" v-model.trim="comment_create.text">
                            <div>
                            <button class="btn btn-success" @click="createNewComment(photo.id)">Aggiungi nuovo commento</button>
                            <button class="btn btn-danger" @click="photo_id = -1">Annulla</button>
                            </div>
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
            <div v-else>La ricerca non ha prodotto risultati</div>
        </div>
    </div>
  </main>
</template>

<script>

import axios from 'axios';

const API_URL_PHOTO = 'http://localhost:8080/api/foto';

const API_URL_COMMENT = 'http://localhost:8080/api/comments';

export default {

  name: 'MainComponent',

  data(){

    return {

        photos: [],
        query: '',
        comment_create: {},
        photo_id: -1
    }
  },

  methods: {

    getPhotos(){
        axios.get(API_URL_PHOTO + '/all').then(result => {
            
            const photos = result.data;
            
            if(photos === null) return

            this.photos = photos;
        })
    },

    getPhotoIndexById(id){
        for(let i=0; i< this.photos.length; i++){
            const photo = this.photos[i];
            if(photo.id === id) return i;
        }
    },

    

    commentPhoto(id){
        this.photo_id = id;
        console.log(this.photo_id);
    },

    createNewComment(photoId){
        axios.post(API_URL_COMMENT + '/add/byPhoto/' + photoId, this.comment_create).then(result => {
            const comment = result.data;

            if(comment === null) return;
          
            const index = this.getPhotoIndexById(photoId);

            const photo = this.photos[index];

             photo.comments.push(comment);
             console.log(photo.comments);

             this.comment_create = '';
        })
    },

    search(){
      if(this.query === null) this.getPhotos;
      axios.get(API_URL_PHOTO + '/search/' + this.query).then(result => {
        const photos = result.data;

        if(photos === null) return;

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