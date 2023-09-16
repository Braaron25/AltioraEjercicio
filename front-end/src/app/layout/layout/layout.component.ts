import { Component } from '@angular/core';
import { Router } from '@angular/router';

declare interface RouteInfo {
  path: string;
  option: string | undefined;
  title: string;
  icon: string;
  class: string;
}

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent {

  constructor(private route: Router) { }


  items : any = []

  userInfo: any

  ngOnInit() {
    this.items = [
      {
        label: 'Article',
        icon: 'pi pi-fw pi-shopping-cart',
        routerLink: ['/home/articles']
      },
      {
        label: 'Client',
        icon: 'pi pi-fw pi-user',
        routerLink: ['/home/clients']
      },
      {
        label: 'Orders',
        icon: 'pi pi-fw pi-th-large',
        routerLink: ['/home/orders']
      }
    ];
  }

}
