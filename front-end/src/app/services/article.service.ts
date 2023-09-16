import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Article } from '../interfaces/article.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  constructor(
    protected http: HttpClient
  ) { }

  apiUrl = '/api/v1/article';


  getAllArticles(){
    return this.http.get(this.apiUrl + "/getAllArticles")
  }

  addNewArticle(article : Article){
    return this.http.post(this.apiUrl + "/createArticle",article)
  }

}
