import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(
    protected http: HttpClient
  ) { }

  apiUrl = '/api/v1/client';


  getAllClients(){
    return this.http.get(this.apiUrl + "/getAllClients")
  }

  addNewClient(client : any){
    return this.http.post(this.apiUrl + "/createClient",client)
  }
}
