import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(
    protected http: HttpClient
  ) { }

  apiUrl = '/api/v1/order';


  getAllOrders(){
    return this.http.get(this.apiUrl + "/getAllOrders")
  }

  addNewOrder(order : any){
    return this.http.post(this.apiUrl + "/createOrder",order)
  }

  findByCode(code:String){
    return this.http.get(this.apiUrl + "/findByCode/"+code)
  }

  updateOrder(updateInfo: any){
    return this.http.put(this.apiUrl + "/updateOrder",updateInfo)
  }

  deleteOrder(orderCode:string){
    return this.http.delete(this.apiUrl + "/deleteOrder/" + orderCode)
  }
}
