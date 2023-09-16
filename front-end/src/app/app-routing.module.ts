import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './layout/layout/layout.component';
import { ArticleComponent } from './components/article/article.component';
import { ClientComponent } from './components/client/client.component';
import { OrderComponent } from './components/order/order.component';
import { EditOrderComponent } from './components/edit-order/edit-order.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    component: LayoutComponent,
    children:[
      {
        path: "articles",
        component:ArticleComponent
      },
      {
        path: "clients",
        component:ClientComponent
      },
      {
        path: "orders",
        component:OrderComponent
      },
      {
        path: "orders/:code",
        component:EditOrderComponent
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
