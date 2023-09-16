import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MessageService } from 'primeng/api';
import { ArticleService } from 'src/app/services/article.service';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-edit-order',
  templateUrl: './edit-order.component.html',
  styleUrls: ['./edit-order.component.css']
})
export class EditOrderComponent implements OnInit{

  constructor(
    private route: ActivatedRoute,
    private orderService: OrderService,
    private articleService: ArticleService,
    private messageService: MessageService
  ){}

  orderInfo : any
  articleList:any
  selectedArticle: any


  ngOnInit(): void {
    this.selectedArticle = null
    this.orderInfo = null
    this.articleList = []
    this.getAllArticles()
    this.route.params.subscribe(p => {
      console.log('code: ', p['code']);
      const code = p['code'];
      this.findByCode(code)
    })
  }

  getAllArticles(){
    this.articleService.getAllArticles().subscribe({
      next: (res: any) => {
        this.articleList = res;
      },
      complete: () => console.log('Consulta de articulos finalizada')
    })
  }

  findByCode(code: String){
    this.orderService.findByCode(code).subscribe({
      next: (res:any) => {
        this.orderInfo = res
      }
    })
  }

  updateOrder(){
    let updateInfo = {
      orderCode: this.orderInfo.code,
      updateArticleRQList: this.orderArticleList
    }
    this.orderService.updateOrder(updateInfo).subscribe({
      next: (res) => {
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Order updated' })
        this.findByCode(this.orderInfo.code)
      },
      error: (err) => {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'We cant update the order' })
      }
    })
  }

  orderArticleList: any[] = []
  addArticle(article:any){
    if(this.orderInfo.orderArticleList.filter((a:any) => a.article.code === article.code).length > 0){
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'This article is also added' })
      return
    }

    this.orderInfo.orderArticleList.push({code: null, article: article})
    this.orderArticleList.push({articleCode: article.code})

  }

  deleteArticle(orderArticle:any){
    if(orderArticle.code === null){
      let index = this.orderInfo.orderArticleList.findIndex((a:any) => a.article.code === orderArticle.article.code)
      if (index > -1) {
        this.orderInfo.orderArticleList.splice(index, 1);
      }
      index = this.orderArticleList.findIndex((a:any) => a === orderArticle.article.code)
      if (index > -1) {
        this.orderArticleList.splice(index, 1);
      }
    }else{
      let index = this.orderInfo.orderArticleList.findIndex((a:any) => a.code === orderArticle.code)
      if (index > -1) {
        this.orderInfo.orderArticleList.splice(index, 1);
      }
      this.orderArticleList.push({orderArticleCode: orderArticle.code})
    }
  }

}
