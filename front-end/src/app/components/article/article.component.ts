import { Component, OnInit} from '@angular/core';
import { MessageService } from 'primeng/api';
import { Article } from 'src/app/interfaces/article.interface';
import { ArticleService } from 'src/app/services/article.service';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.css']
})
export class ArticleComponent implements OnInit{


  constructor(
    private articleService: ArticleService,
    private messageService: MessageService
  ){}

  articleList = []
  articleName = ''
  articlePrice = ''

  ngOnInit(): void {
    this.getAllArticles()
  }

  getAllArticles(){
    this.articleService.getAllArticles().subscribe({
      next: (res: any) => {
        this.articleList = res;
      },
      complete: () => console.log('Consulta de articulos finalizada')
    })
  }

  addNewArticle(){

    if(this.articleName === '' || this.articlePrice === ''){
      this.messageService.add({ severity: 'warning', summary: 'Warning', detail: 'Complete the data, please' })
      return
    }

    let article : Article = {
      cod_article: null,
      name: this.articleName,
      price: parseFloat(this.articlePrice)
    }
    this.articleService.addNewArticle(article).subscribe({
      next: res => {
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Article added' })
      },
      error: err => {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'We cant add article' })
      },
      complete: () => {
        this.articleName = ''
        this.articlePrice = ''
        this.getAllArticles()
      }
    })
  }
  

}
