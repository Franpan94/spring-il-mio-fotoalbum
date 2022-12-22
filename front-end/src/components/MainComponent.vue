<template>
  <main>
    
    <div class="container">
        <div class="row text-center pt-4">
            <h1>Post</h1>
            <div class="d-flex">
                <input type="text" v-model.trim="query" @keyup.enter="search()" 
                    class="form-control" placeholder="Ricerca il tuo post">
                <button class="btn btn-primary" @click="search()">Cerca</button>
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

                    <div class="pt-2" v-if="photo.visible">
                        <h4>Post: Disponibile</h4>
                    </div>
                    <div v-else>
                        <h4 class="pt-2">Post: Non disponibile</h4>
                    </div>

                    <div v-if="photo.comments" class="fs-4">
                        <div v-if="photo.comments.length < 1">
                            <span>Commenti non presenti</span>
                        </div>
                        <div v-else>
                            <h4 class="pt-2" v-for="comment in photo.comments" :key="comment.id">
                               Commenti: {{ comment.text }}
                            </h4>
                        </div>
                    </div>
                    
                    <div class="pt-2">
                        <button @click="commentPhoto(photo.id)" v-if="photo.id !== photo_id" class="btn btn-primary">Commenta</button>
                        <div v-else>
                            <input type="text" @keyup.enter="createNewComment(photo.id)" class="form-control"
                               placeholder="Inserisci commento" name="text" v-model.trim="comment_create.text"
                            >
                            <div class="pt-3 d-flex justify-content-around">
                               <button class="btn btn-success" @click="createNewComment(photo.id)">Aggiungi nuovo commento</button>
                               <button class="btn btn-danger" @click="photo_id = -1">Annulla</button>
                            </div>
                        </div>
                    </div>
                    
                </div>
            </div>
            <div v-else class="pt-3">
                <h4>La ricerca non ha prodotto risultati</h4>
            </div>
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
        comment_create: {text: ''},
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

             this.comment_create.text = '';
        })
    },

    search(){
      if(this.query === null) return this.getPhotos
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