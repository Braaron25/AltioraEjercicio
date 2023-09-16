import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { ClientService } from 'src/app/services/client.service';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit{

  constructor(
    private clientService: ClientService,
    private messageService: MessageService
  ){}

  clientList = []
  clientFirstName = ''
  clientLastName = ''

  ngOnInit(): void {
    this.getAllClients()
  }

  getAllClients(){
    this.clientService.getAllClients().subscribe({
      next: (res: any) => {
        this.clientList = res;
      },
      complete: () => console.log('Consulta de articulos finalizada')
    })
  }

  addNewClient(){

    if(this.clientFirstName === '' || this.clientLastName === ''){
      this.messageService.add({ severity: 'warning', summary: 'Warning', detail: 'Complete the data, please' })
      return
    }

    let client = {
      firstName: this.clientFirstName,
      lastName: this.clientLastName
    }
    this.clientService.addNewClient(client).subscribe({
      next: res => {
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Client added' })
      },
      error: err => {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'We cant add client' })
      },
      complete: () => {
        this.clientFirstName = ''
        this.clientLastName = ''
        this.getAllClients()
      }
    })
  }

}
