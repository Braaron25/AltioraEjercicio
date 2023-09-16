import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { ArticleService } from 'src/app/services/article.service';
import { ClientService } from 'src/app/services/client.service';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit{

  constructor(
    private orderService: OrderService,
    private messageService: MessageService,
    private clientService: ClientService,
    private articleService: ArticleService
  ){}

  articleList = []
  clientList = []
  orderList = []
  selectedClient : any
  selectedArticles : any

  ngOnInit(): void {
    this.getAllArticles()
    this.getAllClients()
    this.getAllOrders()
    this.selectedArticles = null
    this.selectedClient = null
  }

  getAllArticles(){
    this.articleService.getAllArticles().subscribe({
      next: (res: any) => {
        this.articleList = res;
      },
      complete: () => console.log('Consulta de articulos finalizada')
    })
  }
  
  getAllClients(){
    this.clientService.getAllClients().subscribe({
      next: (res: any) => {
        res.forEach((client : any) => {
          client.nombreCompleto = client.firstName + ' ' + client.lastName
        });
        this.clientList = res;
      },
      complete: () => console.log('Consulta de articulos finalizada')
    })
  }

  getAllOrders(){
    this.orderService.getAllOrders().subscribe({
      next: (res: any) => {
        this.orderList = res;
      },
      complete: () => console.log('Consulta de articulos finalizada')
    })
  }

  addNewOrder(){

    if(this.selectedClient === null || this.selectedArticles === null){
      this.messageService.add({ severity: 'warning', summary: 'Warning', detail: 'Complete the data, please' })
      return
    }

    let addArticleList: any[] = []
    this.selectedArticles.forEach((article: any) => {
      addArticleList.push(article.code)
    });

    let order = {
      clientCode: this.selectedClient.code,
      articleList: addArticleList
    }
    this.orderService.addNewOrder(order).subscribe({
      next: res => {
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Order added' })
      },
      error: err => {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'We cant add order' })
      },
      complete: () => {
        this.selectedClient = null
        this.selectedArticles = null
        this.getAllOrders()
      }
    })
  }

  deleteOrder(orderCode: string){
    this.orderService.deleteOrder(orderCode).subscribe({
      next: res => {
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Order deleted' })
        this.getAllOrders()
      },
      error: err => {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'We cant delete the order' })
      }
    })
  }
}
